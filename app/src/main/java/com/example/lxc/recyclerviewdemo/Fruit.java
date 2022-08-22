package com.example.lxc.recyclerviewdemo;

/**
 * Created by lxc on 17-9-8.
 */

public class Fruit {
    private String name;
    private int imgid;

    public Fruit(String name, int imgid) {
        this.name = name;
        this.imgid = imgid;
    }

    public String getName() {
        return name;
    }

    public int getImgid() {
        return imgid;
    }
}
