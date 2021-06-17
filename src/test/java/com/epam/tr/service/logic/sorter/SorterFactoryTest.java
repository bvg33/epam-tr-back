package com.epam.tr.service.logic.sorter;

import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SorterFactoryTest {
    @Test
    public void testCreateSorter() {
        FileNameSorter fileNameSorter = (FileNameSorter) SorterFactory.createSorter("sortByName");
        FileSizeSorter fileSizeSorter = (FileSizeSorter) SorterFactory.createSorter("sortBySize");
        FileTypeSorter fileTypeSorter = (FileTypeSorter) SorterFactory.createSorter("sortByType");
        assertEquals(FileNameSorter.class,fileNameSorter.getClass());
        assertEquals(FileSizeSorter.class,fileSizeSorter.getClass());
        assertEquals(FileTypeSorter.class,fileTypeSorter.getClass());
    }
}
