package com.tiago.despedidasolteirolda.entities;

import com.tiago.despedidasolteirolda.data.FileManager;

import java.io.Serializable;

public class Client extends Person implements Serializable {

    public Client() {
    }

    public void create(){
        FileManager.getFileManager().getClients().put(this.getNIF(), this);
        System.out.println("Cliente criado com sucesso!!!");
        FileManager.getFileManager().serialize("src\\main\\resources\\data\\info.repo");
    }

    @Override
    public String toString() {
        return super.getName();
    }
}
