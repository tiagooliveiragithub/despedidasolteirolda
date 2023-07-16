package com.tiago.despedidasolteirolda.controllers.user;

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
        String username = usernameField.getText();
        String password = passwordField.getText();

        try {
            FileManager fl = FileManager.getFileManager();
            Person user = null;

            user = fl.getAdmins().values().stream()
                    .filter(admin -> username.equalsIgnoreCase(admin.getUsername()) && password.equals(admin.getPassword()))
                    .findFirst().orElse(null);

            if (user == null) {
                user = fl.getManagers().values().stream()
                        .filter(manager -> username.equalsIgnoreCase(manager.getUsername())
                                && password.equals(manager.getPassword()) && manager.getUserActive())
                        .findFirst().orElse(null);
            }

            if (user == null) {
                user = fl.getEmployees().values().stream()
                        .filter(employee -> username.equalsIgnoreCase(employee.getUsername())
                                && password.equals(employee.getPassword()) && employee.getUserActive())
                        .findFirst().orElse(null);
            }

            if (user == null) {
                user = fl.getProviders().values().stream()
                        .filter(provider -> username.equalsIgnoreCase(provider.getUsername())
                                && password.equals(provider.getPassword()) && provider.getUserActive())
                        .findFirst().orElse(null);
            }

            if (user == null) {
                user = fl.getClients().values().stream()
                        .filter(client -> username.equalsIgnoreCase(client.getUsername())
                                && password.equals(client.getPassword()) && client.getUserActive())
                        .findFirst().orElse(null);
            }

            // Handle login
            if (user != null) {
                Session.setUser(user);
                System.out.println("Login com Sucesso!");
                Parent root = user.loadMenu();

                if (root != null) {
                    Scene menuScene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(menuScene);
                    stage.show();
                }
            } else {
                Alert alertDatInv = new Alert(Alert.AlertType.ERROR);
                alertDatInv.setTitle("Erro");
                alertDatInv.setHeaderText("O login falhou!");
                alertDatInv.show();
            }
        } catch (Exception e) {
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

