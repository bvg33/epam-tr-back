package com.epam.tr.service.logic;

import com.epam.tr.dao.entities.AppUser;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;
@Component
public class UserValidator implements Validator<AppUser>{
    @Override
    public boolean isValid(AppUser entity) {
        return nonNull(entity.getLogin()) && nonNull(entity.getPassword());
    }
}
