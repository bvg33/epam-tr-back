package com.epam.tr.service.logic;

import com.epam.tr.dao.entities.FileEntity;
import com.epam.tr.dao.entities.FileType;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileValidatorTest {
    private final FileValidator validator = new FileValidator();

    @Test
    public void testIsValidWhenValid() {
        boolean actual = validator.isValid(new FileEntity(FileType.FILE,"name","path"));
        assertTrue(actual);
    }

    @Test
    public void testIsValidWhenNotValid(){
        boolean actual = validator.isValid(new FileEntity(FileType.FILE,null,"path"));
        assertFalse(actual);
    }
}
