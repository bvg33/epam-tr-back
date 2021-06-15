package com.epam.tr.service;

import com.epam.tr.dao.entities.FileEntity;
import com.epam.tr.exceptions.WrongFileException;
import com.epam.tr.service.logic.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.tr.dao.entities.FileType.*;
import static java.util.Objects.requireNonNull;

@Service
public class FileSystemService implements FileService {
    @Autowired
    private Validator<FileEntity> validator;
    private static final String NOT_VALID = "Not valid file parameters";
    private static final String CREATE_EXCEPTION = "create file exception";

    @Override
    public void create(FileEntity fileEntity) throws WrongFileException {
        checkFile(fileEntity);
        try {
            if (fileEntity.getType() == FOLDER) {
                createFolder(fileEntity);
            }
            if (fileEntity.getType() == FILE) {
                createFile(fileEntity);
            }
        } catch (IOException e) {
            throw new WrongFileException(CREATE_EXCEPTION);
        }
    }

    @Override
    public void update(FileEntity oldFile, FileEntity newFile) throws WrongFileException {
        checkFile(oldFile);
        checkFile(newFile);
        new File(createAbsolutePath(oldFile)).renameTo(new File(createAbsolutePath(newFile)));
    }

    @Override
    public void delete(FileEntity fileEntity) throws WrongFileException {
        checkFile(fileEntity);
        String absolutePath = createAbsolutePath(fileEntity);
        new File(absolutePath).delete();
    }
    private void checkFile(FileEntity fileEntity) throws WrongFileException {
        if (!validator.isValid(fileEntity)) {
            throw new WrongFileException(NOT_VALID);
        }
    }
    private void createFile(FileEntity fileEntity) throws IOException {

        String absolutePath = createAbsolutePath(fileEntity);
        new File(absolutePath).createNewFile();
    }

    private void createFolder(FileEntity fileEntity) {
        new File(fileEntity.getPath(), fileEntity.getName()).mkdir();
    }

    private String createAbsolutePath(FileEntity fileEntity) {
        String path = fileEntity.getPath();
        String name = fileEntity.getName();
        String absolutePath = path + "\\" + name;
        if (fileEntity.getType() == FILE) {
            absolutePath += ".txt";
        }
        return absolutePath;
    }

    @Override
    public List<FileEntity> readFileByPath(String path) {
        List<FileEntity> files;
        if (path.isEmpty()) {
            files = getRoots();
        } else {
            File dir = new File(path);
            files = scanDir(dir);
        }
        return files;
    }

    private List<FileEntity> getRoots() {
        List<FileEntity> files = new ArrayList<>();
        for (File file : File.listRoots()) {
            files.add(new FileEntity(DRIVE, file.getPath(), file.getAbsolutePath()));
        }
        return files;
    }

    private List<FileEntity> scanDir(File dir) {
        List<FileEntity> files = new ArrayList<>();
        if (dir.isDirectory()) {
            for (File item : requireNonNull(dir.listFiles())) {
                if (item.isDirectory()) {
                    files.add(new FileEntity(FOLDER, item.getName(), item.getAbsolutePath()));
                } else {
                    files.add(new FileEntity(FILE, item.getName(), item.getAbsolutePath()));
                }
            }
        }
        return files;
    }
}
