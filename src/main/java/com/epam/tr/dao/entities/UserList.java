package com.epam.tr.dao.entities;

import java.util.ArrayList;
import java.util.List;

public class UserList {
    private ArrayList list;

    public List<AppUser> getList() {
        return list;
    }

    public UserList(List<AppUser> list) {
        this.list = new ArrayList(list);
    }
}
