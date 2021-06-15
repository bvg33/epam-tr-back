package com.epam.tr.dao.entities;

import java.util.ArrayList;
import java.util.List;
public class FileList {

    private ArrayList list;

    public List<FileEntity> getList() {
        return list;
    }

    public FileList(List<FileEntity> list) {
        this.list = new ArrayList(list);
    }
}
