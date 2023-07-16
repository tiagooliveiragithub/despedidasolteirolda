package com.tiago.despedidasolteirolda.entities;

import com.tiago.despedidasolteirolda.data.FileManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.io.Serializable;

public class Admin extends Person implements Serializable {

    public Admin() {
        super();
    }

    @Override
    public Parent loadMenu() throws IOException {
        return FXMLLoader.load(getClass().getResource("/com/tiago/despedidasolteirolda/controllers/adminMenu.fxml"));
    }

    public void create(){
        FileManager.getFileManager().getAdmins().put(this.getNIF(), this);
        System.out.println("Administrador criado com sucesso!!!");
        FileManager.getFileManager().serialize("src\\main\\resources\\data\\info.repo");
    }




}
