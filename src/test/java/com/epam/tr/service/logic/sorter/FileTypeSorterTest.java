package com.epam.tr.service.logic.sorter;

import com.epam.tr.dao.entities.FileEntity;
import com.epam.tr.dao.entities.FileType;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileTypeSorterTest {
    private final FileTypeSorter sorter = new FileTypeSorter();

    @Test
    public void testCompare() {
        FileEntity firstEntity = new FileEntity(FileType.FOLDER, "name1", "path", 0);
        FileEntity secondEntity = new FileEntity(FileType.FILE, "name2", "path", 0);
        FileEntity thirdEntity = new FileEntity(FileType.FILE, "name2", "path", 0);
        FileEntity fourthEntity = new FileEntity(FileType.FILE,"name","path",0);
        assertEquals(-1,sorter.compare(secondEntity,firstEntity));
        assertEquals(0,sorter.compare(secondEntity,thirdEntity));
        assertEquals(1,sorter.compare(firstEntity,fourthEntity));
    }
}
