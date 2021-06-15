package com.epam.tr.service.logic;

import com.epam.tr.dao.entities.AppUser;
import com.epam.tr.dao.entities.FileEntity;
import com.epam.tr.dao.entities.FileType;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserValidatorTest {
    private final UserValidator validator = new UserValidator();

    @Test
    public void testIsValidWhenValid() {
        boolean actual = validator.isValid(new AppUser("name","password"));
        assertTrue(actual);
    }

    @Test
    public void testIsValidWhenNotValid(){
        boolean actual = validator.isValid(new AppUser(null,null));
        assertFalse(actual);
    }
}
