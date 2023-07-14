package com.tiago.despedidasolteirolda.entities;

import com.tiago.despedidasolteirolda.data.FileManager;

import java.io.Serializable;

public class Employee extends Person implements Serializable {

    public Employee() {
    }

    @Override
    public void create() {
        FileManager.getFileManager().getEmployees().put(this.getNIF(), this);
        System.out.println("Cliente criado com sucesso!!!");
        FileManager.getFileManager().serialize("src\\main\\resources\\data\\info.repo");
    }
}
