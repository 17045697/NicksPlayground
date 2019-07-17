package com.example.nicksplayground;

import java.io.Serializable;

public class Scene implements Serializable {
    private int id;
    private String imgPath;
    private String name;

    public Scene(int id, String imgPath, String name){
        this.id = id;
        this.imgPath = imgPath;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


