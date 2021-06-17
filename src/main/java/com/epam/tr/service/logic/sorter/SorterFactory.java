package com.epam.tr.service.logic.sorter;

import com.epam.tr.dao.entities.FileEntity;

import java.util.Comparator;

public class SorterFactory {
    private static final String SORT_BY_NAME = "sortByName";
    private static final String SORT_BY_SIZE = "sortBySize";
    private static final String SORT_BY_TYPE = "sortByType";
    private static final String ILLEGAL_ARGUMENTS = "Illegal arguments";

    public static Comparator<FileEntity> createSorter(String sorterType) {
        switch (sorterType) {
            case SORT_BY_NAME:
                return new FileNameSorter();
            case SORT_BY_SIZE:
                return new FileSizeSorter();
            case SORT_BY_TYPE:
                return new FileTypeSorter();
            default:
                throw new IllegalArgumentException(ILLEGAL_ARGUMENTS);
        }
    }
}
