package com.jesuspinar.todo.model;

import java.util.GregorianCalendar;

public class ToDo {
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
}
