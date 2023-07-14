package com.tiago.despedidasolteirolda;

import com.tiago.despedidasolteirolda.data.FileManager;
import com.tiago.despedidasolteirolda.entities.Admin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("controllers/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 450);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        FileManager fl;
        FileManager.deserialize("src\\main\\resources\\data\\info.repo");
        fl = FileManager.getFileManager();
        if(fl.getAdmins().size() == 0){
            Admin c1 = new Admin();
            c1.setName("Administrador");
            c1.setNIF("274247264");
            c1.setUsername("admin");
            c1.setPassword("admin");
            c1.createAdmin();
        }
        launch();
    }
}