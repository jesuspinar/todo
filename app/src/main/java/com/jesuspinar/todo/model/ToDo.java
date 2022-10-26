package com.jesuspinar.todo.model;

import java.io.Serializable;

public class ToDo implements Serializable {
    private static int AUTOID = 0;
    private int id;
    private String content;

    public ToDo(String content) {
        id = ++AUTOID;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return content;
    }
}
