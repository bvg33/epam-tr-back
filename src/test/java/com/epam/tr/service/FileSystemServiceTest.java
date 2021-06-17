package com.epam.tr.service;

import com.epam.tr.dao.entities.FileEntity;
import com.epam.tr.dao.entities.FileList;
import com.epam.tr.dao.entities.FileType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class FileSystemServiceTest {
    private final FileSystemService service = new FileSystemService();

    @Test
    public void testReadFileByPathWhenPathExist() {
        String path = "C:\\Users\\Dzmitry_Saukou\\IdeaProjects\\epam\\epam-tr\\src\\main\\webapp\\WEB-INF";
        FileList actual = service.readFileByPath(path);
        FileList expected = new FileList(Arrays.asList(new FileEntity(FileType.FILE, "web.xml", path + "\\web.xml",0)));
        assertEquals(expected, actual);
    }

    @Test
    public void testReadFileByPathWhenPathDontExist() {
        String path = "C:\\Users\\D";
        FileList actual = service.readFileByPath(path);
        FileList expected = new FileList(new ArrayList<>());
        assertEquals(expected, actual);
    }
}
