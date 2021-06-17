package com.epam.tr.service;

import com.epam.tr.dao.entities.FileEntity;
import com.epam.tr.dao.entities.FileList;

public interface FileService extends Service<FileEntity> {
    FileList readFileByPath(String path);
    FileList doFilter(String path,String parameter,String sortType);
    FileList search (String path,String mask);
}
