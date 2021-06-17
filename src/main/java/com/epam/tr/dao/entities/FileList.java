package com.epam.tr.dao.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileList {

    private ArrayList list;

    public List<FileEntity> getList() {
        return list;
    }

    public FileList(List<FileEntity> list) {
        this.list = new ArrayList(list);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileList fileList = (FileList) o;
        return Objects.equals(list, fileList.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }

    @Override
    public String toString() {
        return "FileList{" +
                "list=" + list +
                '}';
    }
}
