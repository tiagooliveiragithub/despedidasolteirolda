package com.tiago.despedidasolteirolda.controllers;

import com.tiago.despedidasolteirolda.data.FileManager;
import com.tiago.despedidasolteirolda.entities.*;
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
                    Parent root = FXMLLoader.load(getClass().getResource("adminMenu.fxml"));
                    Scene menuScene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(menuScene);
                    stage.show();
                }
            }

            for (Manager manager : fl.getManagers().values()) {
                if (usernameField.getText().equalsIgnoreCase(manager.getUsername()) && passwordField.getText().equals(manager.getPassword())) {
                    found = true;
                    Session.setUser(manager);
                    System.out.println("Login com Sucesso!");
                    Parent root = FXMLLoader.load(getClass().getResource("managerMenu.fxml"));
                    Scene menuScene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(menuScene);
                    stage.show();
                }
            }

            for (Provider provider: fl.getProviders().values()) {
                if (usernameField.getText().equalsIgnoreCase(provider.getUsername()) && passwordField.getText().equals(provider.getPassword())) {
                    found = true;
                    Session.setUser(provider);
                    System.out.println("Login com Sucesso!");
                    Parent root = FXMLLoader.load(getClass().getResource("providerMenu.fxml"));
                    Scene menuScene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(menuScene);
                    stage.show();
                }
            }

            for (Client client: fl.getClients().values()) {
                if (usernameField.getText().equalsIgnoreCase(client.getUsername()) && passwordField.getText().equals(client.getPassword())) {
                    found = true;
                    Session.setUser(client);
                    System.out.println("Login com Sucesso!");
                    Parent root = FXMLLoader.load(getClass().getResource("clientMenu.fxml"));
                    Scene menuScene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(menuScene);
                    stage.show();
                }
            }

            for (Employee employee: fl.getEmployees().values()) {
                if (usernameField.getText().equalsIgnoreCase(employee.getUsername()) && passwordField.getText().equals(employee.getPassword())) {
                    found = true;
                    Session.setUser(employee);
                    System.out.println("Login com Sucesso!");
                    Parent root = FXMLLoader.load(getClass().getResource("employeeMenu.fxml"));
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

