package com.tiago.despedidasolteirolda.controllers;

import com.tiago.despedidasolteirolda.data.FileManager;
import com.tiago.despedidasolteirolda.entities.Admin;
import com.tiago.despedidasolteirolda.entities.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    void login(ActionEvent event) {
        boolean found = false;

        try{
            FileManager fl;
            fl = FileManager.getFileManager();

            for (Admin admin : fl.getAdmins().values()) {
                if (usernameField.getText().equalsIgnoreCase(admin.getUsername()) && passwordField.getText().equals(admin.getPassword())) {
                    found = true;
                    Session.setUser(admin);
                    System.out.println("Login com Sucesso!");
                    Session.setUser(admin);
                    Parent root = FXMLLoader.load(getClass().getResource("menuProvider.fxml"));
                    Scene menuScene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(menuScene);
                    stage.show();
                }
            }
            if(!found){
                Alert alertDatInv = new Alert(Alert.AlertType.ERROR);
                alertDatInv.setTitle("Erro");
                alertDatInv.setHeaderText("O login falhou!");
                alertDatInv.show();
            }
        }
        catch(Exception e)
        {
            System.out.println("Erro ao verificar login: " + e);
        }
    }

    @FXML
    void register(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
            Scene regCena = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

