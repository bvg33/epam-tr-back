package com.epam.tr.service;

import com.epam.tr.dao.entities.AppUser;
import com.epam.tr.dao.entities.UserList;

import java.util.List;

public interface UserService extends Service<AppUser>{
    UserList getAllUsers();
    AppUser getUserById(int id);
}
