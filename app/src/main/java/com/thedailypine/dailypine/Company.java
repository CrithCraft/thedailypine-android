package com.thedailypine.dailypine;

import java.util.HashMap;

public class Company {

    private String id;
    private String name;
    private String date;
    private String author;
    private String desc;

    public void setData(HashMap<String, String> data) {
        this.id = data.get("id");
        this.name = data.get("name");
        this.date = data.get("date");
        this.author = data.get("author");
        this.desc = data.get("description");
    }

    public String getName() {
        return this.name;
    }

    public String getDate() {
        return this.date;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getAuthor() {
        return this.author;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id:" + this.id);
        sb.append("\n"+"name:" + this.name);
        sb.append("\n"+"date:" + this.date);
        sb.append("\n"+"author:" + this.author);
        sb.append("\n"+"description:" + this.desc);
        return sb.toString();
    }
}
