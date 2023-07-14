package com.tiago.despedidasolteirolda.entities;

import com.tiago.despedidasolteirolda.data.FileManager;

import java.io.Serializable;

public class Provider extends Person implements Serializable {

    public Provider() {
    }

    public void create(){
        FileManager.getFileManager().getProviders().put(this.getNIF(), this);
        System.out.println("Administrador criado com sucesso!!!");
        FileManager.getFileManager().serialize("src\\main\\resources\\data\\info.repo");
    }
}
