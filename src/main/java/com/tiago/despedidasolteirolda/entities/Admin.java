package com.tiago.despedidasolteirolda.entities;

import com.tiago.despedidasolteirolda.data.FileManager;

import java.io.Serializable;

public class Admin extends Person implements Serializable {

    public Admin() {
        super();
    }

    public void createAdmin(){
        FileManager.getFileManager().getAdmins().put(this.getNIF(), this);
        System.out.println("Administrador criado com sucesso!!!");
        FileManager.getFileManager().serialize("src\\main\\resources\\data\\info.repo");
    }


}
