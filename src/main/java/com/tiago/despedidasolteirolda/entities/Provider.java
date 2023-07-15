package com.tiago.despedidasolteirolda.entities;

import com.tiago.despedidasolteirolda.data.FileManager;

import java.io.Serializable;
import java.util.HashSet;

public class Provider extends Person implements Serializable {

    private HashSet<Service> listServices;
    public Provider() {
        listServices = new HashSet<>();
    }

    public void create(){
        FileManager.getFileManager().getProviders().put(this.getNIF(), this);
        System.out.println("Administrador criado com sucesso!!!");
        FileManager.getFileManager().serialize("src\\main\\resources\\data\\info.repo");
    }

    public HashSet<Service> getListServices() {
        return listServices;
    }

    public void setListServices(HashSet<Service> listServices) {
        this.listServices = listServices;
    }
}
