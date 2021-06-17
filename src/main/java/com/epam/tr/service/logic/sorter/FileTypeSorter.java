package com.epam.tr.service.logic.sorter;

import com.epam.tr.dao.entities.FileEntity;

import java.util.Comparator;

public class FileTypeSorter implements Comparator<FileEntity> {
    @Override
    public int compare(FileEntity o1, FileEntity o2) {
        return o1.getType().compareTo(o2.getType());
    }
}
