package com.tiago.despedidasolteirolda.entities;

import com.tiago.despedidasolteirolda.data.FileManager;

import java.io.Serializable;

public class Manager extends Person implements Serializable {

    public Manager() {
    }

    @Override
    public void create() {
        FileManager.getFileManager().getManagers().put(this.getNIF(), this);
        System.out.println("Cliente criado com sucesso!!!");
        FileManager.getFileManager().serialize("src\\main\\resources\\data\\info.repo");
    }
}
