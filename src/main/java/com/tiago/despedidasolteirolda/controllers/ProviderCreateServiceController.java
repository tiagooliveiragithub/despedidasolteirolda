package com.tiago.despedidasolteirolda.controllers;

import com.tiago.despedidasolteirolda.entities.*;
import com.tiago.despedidasolteirolda.entities.enums.Localidades;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ProviderCreateServiceController implements Initializable {
    private boolean durationStatus = false;

    @FXML
    private Text nameError;
    @FXML
    private Text durationError;
    @FXML
    private Text priceError;
    @FXML
    private Text locationError;

    @FXML
    private TextField nameField;
    @FXML
    private TextField durationField;
    @FXML
    private TextField priceField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private ChoiceBox<Localidades> locationBox;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        locationBox.getItems().setAll(Localidades.values());
    }

    @FXML
    void createService(ActionEvent event) {
        if(verifyTextAreas()) {
            Service service = new Service();
            service.setName(nameField.getText());
            service.setPhoneNumber(Session.user.getPhoneNumber());
            service.setDuration(Integer.parseInt(durationField.getText()));
            service.setPrice(Float.parseFloat(priceField.getText()));
            service.setDescription(descriptionField.getText());
            service.setLocal(locationBox.getValue());
            Provider provider = (Provider) Session.user;
            provider.getListServices().add(service);
            service.setOwner(provider);
            service.create();

            try {
                Parent root = FXMLLoader.load(getClass().getResource("providerMenu.fxml"));
                Scene menuScene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(menuScene);
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
            Parent root = FXMLLoader.load(getClass().getResource("providerMenu.fxml"));
            Scene menuScene = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(menuScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    boolean verifyTextAreas() {
        boolean everythingOk = true;
        if ((!durationField.getText().matches("[0-9]*")) || durationField.getText().isEmpty()) {
            durationError.setVisible(true);
            everythingOk = false;
        } else {
            durationError.setVisible(false);
        }

        if (nameField.getText().isEmpty()) {
            nameError.setVisible(true);
            everythingOk = false;
        } else {
            nameError.setVisible(false);
        }

        if ((!priceField.getText().matches("[0-9]*")) || priceField.getText().isEmpty()) {
            priceError.setVisible(true);
            everythingOk = false;
        } else {
            priceError.setVisible(false);
        }

        if (locationBox.getValue() == null) {
            locationError.setVisible(true);
            everythingOk = false;
        } else {
            locationError.setVisible(false);
        }

        return everythingOk;
    }

}
