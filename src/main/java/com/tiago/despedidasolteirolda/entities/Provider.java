package com.tiago.despedidasolteirolda.entities;

import com.tiago.despedidasolteirolda.data.FileManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;

public class Provider extends Person implements Serializable {

    private HashSet<Service> listServices;

    public Provider() {
        super.setUserActive(false);
        listServices = new HashSet<>();
    }

    public void create(){
        FileManager.getFileManager().getProviders().put(this.getNIF(), this);
        System.out.println("Fornecedor criado com sucesso!!!");
        FileManager.getFileManager().serialize("src\\main\\resources\\data\\info.repo");
    }

    @Override
    public Parent loadMenu() throws IOException {
        return FXMLLoader.load(getClass().getResource("/com/tiago/despedidasolteirolda/controllers/providerMenu.fxml"));
    }

    public HashSet<Service> getListServices() {
        return listServices;
    }

    public void setListServices(HashSet<Service> listServices) {
        this.listServices = listServices;
    }

}
