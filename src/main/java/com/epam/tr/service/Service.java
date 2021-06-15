package com.epam.tr.service;

import com.epam.tr.exceptions.WrongFileException;
import com.epam.tr.exceptions.WrongUserCredentials;

public interface Service <T>{
    void create(T Entity) throws WrongFileException, WrongUserCredentials;


    void update(T oldEntity,T newEntity) throws WrongFileException, WrongUserCredentials;

    void delete(T entity) throws WrongFileException, WrongUserCredentials;
}
