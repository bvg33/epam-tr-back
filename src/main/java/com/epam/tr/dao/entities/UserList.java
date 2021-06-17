package com.epam.tr.dao.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserList {
    private ArrayList list;

    public List<AppUser> getList() {
        return list;
    }

    public UserList(List<AppUser> list) {
        this.list = new ArrayList(list);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserList userList = (UserList) o;
        return Objects.equals(list, userList.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }
}
