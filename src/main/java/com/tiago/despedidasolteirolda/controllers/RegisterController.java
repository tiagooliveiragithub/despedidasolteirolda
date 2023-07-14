package com.tiago.despedidasolteirolda.controllers;

import com.tiago.despedidasolteirolda.entities.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    private boolean phoneStatus = false;
    private boolean nifStatus = false;

    @FXML
    private ChoiceBox<String> accountType;

    @FXML
    private TextField addressField;

    @FXML
    private DatePicker birthDateField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField nifField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField usernameField;

    @FXML
    private Text phoneError;

    @FXML
    private Text nifError;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        accountType.getItems().addAll("Cliente", "Gestor", "Fornecedor", "Funcionário");
    }

    @FXML
    void register(ActionEvent event) {
        if(phoneStatus == true && nifStatus == true) {

            Person person = new Client();
            if(accountType.getValue() == "Gestor") {
                person = new Manager();
            } else if (accountType.getValue() == "Fornecedor") {
                person = new Provider();
            } else if (accountType.getValue() == "Funcionário") {
                person = new Employee();
            }

            person.setAddress(addressField.getText().toString());
            person.setBirthDate(birthDateField.getValue().toString());
            person.setName(nameField.getText());
            person.setNIF(nifField.getText());
            person.setPhoneNumber(phoneNumberField.getText().toString());
            person.setUsername(usernameField.getText());
            person.setPassword(passwordField.getText());
            person.create();

            try {
                Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
                Scene loginScene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(loginScene);
                stage.setTitle("Login");
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        else{
            Alert alertError = new Alert(Alert.AlertType.ERROR);
            alertError.setTitle("Erro");
            alertError.setHeaderText("Verifique os dados inseridos!");
            alertError.show();
        }
    }

    @FXML
    void backScene(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene loginScene = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(loginScene);
            stage.setTitle("Login");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void verifyPhone(KeyEvent event) {
        String text = phoneNumberField.getText();
        if (text.matches("[0-9]*")) {
            phoneStatus = true;
            phoneError.setVisible(false);
            return;
        }
        else{
            phoneStatus = false;
            phoneError.setVisible(true);
            return;
        }
    }

    @FXML
    void verifyNif(KeyEvent event) {
        String text = nifField.getText();
        if (text.matches("[0-9]*")) {
            nifStatus = true;
            nifError.setVisible(false);
            if(text.length() < 9 || text.length() > 9){
                nifStatus = false;
                nifError.setVisible(true);
                return;
            }
            return;
        }
        else{
            nifStatus = false;
            nifError.setVisible(true);
            return;
        }
    }

}
