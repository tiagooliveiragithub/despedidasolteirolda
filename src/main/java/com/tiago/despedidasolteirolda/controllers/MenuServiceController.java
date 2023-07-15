package com.tiago.despedidasolteirolda.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuServiceController {

    @FXML
    void createService(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("createService.fxml"));
            Scene createServiceScene = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(createServiceScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void listMarkings(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("createService.fxml"));
            Scene listMarkingsScene = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(listMarkingsScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
