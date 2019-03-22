package com.example.weeklistviewapp;

public class DayBean {
    public DayBean(String name, String desc, int resourcePictureID) {
        this.name = name;
        this.desc = desc;
        this.resourcePictureID = resourcePictureID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getResourcePictureID() {
        return resourcePictureID;
    }

    public void setResourcePictureID(int resourcePictureID) {
        this.resourcePictureID = resourcePictureID;
    }

    private String name;
    private String desc;
    private int resourcePictureID;
}
