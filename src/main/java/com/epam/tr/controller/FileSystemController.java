package com.epam.tr.controller;

import com.epam.tr.dao.entities.FileEntity;
import com.epam.tr.exceptions.WrongFileException;
import com.epam.tr.exceptions.WrongUserCredentials;
import com.epam.tr.service.FileService;
import com.epam.tr.service.logic.builder.PathBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

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
    private static final String NAME = "name";
    private static final String NEW_NAME = "newName";
    private static final int FILE_SIZE = 0;
    public static final String PARAMETER = "parameter";
    public static final String SORT_TYPE = "sortType";
    public static final String MASK = "mask";

    @GetMapping(value = {"/getFile", "/getFile/{drive}"})
    public ResponseEntity getFiles(@PathVariable(required = false) String drive, @RequestParam MultiValueMap<String, String> allRequestParams) throws JsonProcessingException {
        String path = builder.createPath(drive, allRequestParams);
        return ResponseEntity.status(OK).body(new ObjectMapper().writeValueAsString(service.readFileByPath(path)));
    }

    @PostMapping("/createFile/{drive}")
    public ResponseEntity create(@PathVariable String drive, @RequestParam MultiValueMap<String, String> allRequestParams) throws WrongFileException, WrongUserCredentials {
        String path = builder.createPath(drive, allRequestParams);
        if (allRequestParams.getFirst(TYPE).equals(FILE_STRING)) {
            service.create(new FileEntity(FILE, allRequestParams.getFirst(NAME), path, FILE_SIZE));
        } else {
            service.create(new FileEntity(FOLDER, allRequestParams.getFirst(NAME), path, FILE_SIZE));
        }
        return new ResponseEntity<>(CREATED);
    }

    @DeleteMapping("/deleteFile/{drive}")
    public ResponseEntity delete(@PathVariable String drive, @RequestParam MultiValueMap<String, String> allRequestParams) throws WrongFileException, WrongUserCredentials {
        String path = builder.createPath(drive, allRequestParams);
        if (allRequestParams.getFirst(TYPE).equals(FILE_STRING)) {
            service.delete(new FileEntity(FILE, allRequestParams.getFirst(NAME), path, FILE_SIZE));
        } else {
            service.delete(new FileEntity(FOLDER, allRequestParams.getFirst(NAME), path, FILE_SIZE));
        }
        return new ResponseEntity<>(NO_CONTENT);
    }

    @PatchMapping("/update/{drive}")
    public ResponseEntity update(@PathVariable String drive, @RequestParam MultiValueMap<String, String> allRequestParams) throws WrongFileException, WrongUserCredentials {
        String path = builder.createPath(drive, allRequestParams);
        if (allRequestParams.getFirst(TYPE).equals(FILE_STRING)) {
            service.update(new FileEntity(FILE, allRequestParams.getFirst(NAME), path, FILE_SIZE),
                    new FileEntity(FILE, allRequestParams.getFirst(NEW_NAME), path, FILE_SIZE));
        } else {
            service.update(new FileEntity(FOLDER, allRequestParams.getFirst(NAME), path, FILE_SIZE),
                    new FileEntity(FOLDER, allRequestParams.getFirst(NEW_NAME), path, FILE_SIZE));
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/filter/{drive}")
    public ResponseEntity filterFiles(@PathVariable String drive, @RequestParam MultiValueMap<String, String> allRequestParams) throws JsonProcessingException {
        String path = builder.createPath(drive, allRequestParams);
        String parameter = allRequestParams.getFirst(PARAMETER);
        String sortType = allRequestParams.getFirst(SORT_TYPE);
        return ResponseEntity.status(OK).body(new ObjectMapper().writeValueAsString(service.doFilter(path, parameter, sortType)));
    }

    @GetMapping("/search/{drive}")
    public ResponseEntity findFilesByMask(@PathVariable String drive, @RequestParam MultiValueMap<String, String> allRequestParams) throws JsonProcessingException {
        String path = builder.createPath(drive, allRequestParams);
        String mask = allRequestParams.getFirst(MASK);
        return ResponseEntity.status(OK).body(new ObjectMapper().writeValueAsString(service.search(path, mask)));
    }
}
