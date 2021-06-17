package com.epam.tr.service.logic.validator;

public interface Validator <T>{
    boolean isValid(T entity);
}
