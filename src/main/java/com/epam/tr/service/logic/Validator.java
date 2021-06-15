package com.epam.tr.service.logic;

public interface Validator <T>{
    boolean isValid(T entity);
}
