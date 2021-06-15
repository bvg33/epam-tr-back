package com.epam.tr.service;

import com.epam.tr.dao.entities.FileEntity;

import java.util.List;

public interface FileService extends Service<FileEntity> {
    List<FileEntity> readFileByPath(String path);
}
