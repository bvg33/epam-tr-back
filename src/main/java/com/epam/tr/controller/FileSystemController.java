package com.epam.tr.controller;

import com.epam.tr.dao.entities.FileEntity;
import com.epam.tr.dao.entities.FileList;
import com.epam.tr.exceptions.WrongFileException;
import com.epam.tr.exceptions.WrongUserCredentials;
import com.epam.tr.service.logic.PathBuilder;
import com.epam.tr.service.FileService;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.JsonViewResponseBodyAdvice;

import java.io.IOException;

import static com.epam.tr.dao.entities.FileType.FILE;
import static com.epam.tr.dao.entities.FileType.FOLDER;
import static org.springframework.http.HttpStatus.*;


@RestController
@RequestMapping("/files")
public class FileSystemController {
    @Autowired
    private FileService service;
    @Autowired
    private PathBuilder builder;
    private static final String TYPE = "type";
    private static final String FILE_STRING = "file";
    private static final String FOLDER_STRING = "folder";
    private static final String NAME = "name";
    private static final String NEW_NAME = "newName";

    @GetMapping(value = {"/getFile", "/getFile/{drive}"})
    public ResponseEntity getFiles(@PathVariable(required = false) String drive, @RequestParam MultiValueMap<String, String> allRequestParams) throws JsonProcessingException {
        String path = builder.createPath(drive, allRequestParams);
        FileList fileList = new FileList(service.readFileByPath(path));
        return ResponseEntity.status(OK).body(new ObjectMapper().writeValueAsString(fileList));
    }

    @PostMapping("/createFile/{drive}")
    public ResponseEntity create(@PathVariable String drive, @RequestParam MultiValueMap<String, String> allRequestParams) throws WrongFileException, WrongUserCredentials {
        String path = builder.createPath(drive, allRequestParams);
        if (allRequestParams.getFirst(TYPE).equals(FILE_STRING)) {
            service.create(new FileEntity(FILE, allRequestParams.getFirst(NAME), path));
        }
        if (allRequestParams.getFirst(TYPE).equals(FOLDER_STRING)) {
            service.create(new FileEntity(FOLDER, allRequestParams.getFirst(NAME), path));
        }
        return new ResponseEntity<>(CREATED);
    }

    @DeleteMapping("/deleteFile/{drive}")
    public ResponseEntity delete(@PathVariable String drive, @RequestParam MultiValueMap<String, String> allRequestParams) throws WrongFileException, WrongUserCredentials {
        String path = builder.createPath(drive, allRequestParams);
        if (allRequestParams.getFirst(TYPE).equals(FILE_STRING)) {
            service.delete(new FileEntity(FILE, allRequestParams.getFirst(NAME), path));
        }
        if (allRequestParams.getFirst(TYPE).equals(FOLDER_STRING)) {
            service.delete(new FileEntity(FOLDER, allRequestParams.getFirst(NAME), path));
        }
        return new ResponseEntity<>(NO_CONTENT);
    }

    @PatchMapping("/update/{drive}")
    public ResponseEntity update(@PathVariable String drive, @RequestParam MultiValueMap<String, String> allRequestParams) throws WrongFileException, WrongUserCredentials {
        String path = builder.createPath(drive, allRequestParams);
        if (allRequestParams.getFirst(TYPE).equals(FILE_STRING)) {
            service.update(new FileEntity(FILE, allRequestParams.getFirst(NAME), path),
                    new FileEntity(FILE, allRequestParams.getFirst(NEW_NAME), path));
        }
        if (allRequestParams.getFirst(TYPE).equals(FOLDER_STRING)) {
            service.update(new FileEntity(FOLDER, allRequestParams.getFirst(NAME), path),
                    new FileEntity(FOLDER, allRequestParams.getFirst(NEW_NAME), path));
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
