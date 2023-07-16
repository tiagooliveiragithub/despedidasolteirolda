package com.tiago.despedidasolteirolda;

import com.tiago.despedidasolteirolda.data.FileManager;
import com.tiago.despedidasolteirolda.entities.Admin;
import com.tiago.despedidasolteirolda.entities.Client;
import com.tiago.despedidasolteirolda.entities.Person;
import com.tiago.despedidasolteirolda.entities.Provider;
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
        stage.setTitle("DespedidasSolteiroLda (BETA 0.1)");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        FileManager fl;
        FileManager.deserialize("src\\main\\resources\\data\\info.repo");
        fl = FileManager.getFileManager();
        if(fl.getAdmins().size() == 0){
            Person person = new Admin();
            person.setName("Administrador");
            person.setNIF("274247264");
            person.setUsername("admin");
            person.setPassword("admin");
            person.create();
        }
        if(fl.getClients().size() == 0) {
            Person person = new Client();
            person.setName("Ricardo");
            person.setNIF("274247264");
            person.setUsername("ricardo");
            person.setPassword("ricardo");
            person.create();
        }

        if(fl.getProviders().size() == 0) {
            Person person = new Provider();
            person.setName("Tiago");
            person.setNIF("274247262");
            person.setUsername("tiago");
            person.setPassword("tiago");
            person.create();
        }

        launch();
    }
}