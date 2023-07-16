package com.tiago.despedidasolteirolda.controllers;

import com.tiago.despedidasolteirolda.entities.Marking;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

public class ClientCreateMarkingController {
    public Marking newMarking = new Marking();

    @FXML DatePicker dateField;

    @FXML
    void createMarking(ActionEvent event) {

        if(dateField.getValue() != null) {
            newMarking.setMarkingDay(dateField.getValue());
            newMarking.create();

            try {
                Parent root = FXMLLoader.load(getClass().getResource("clientMenu.fxml"));
                Scene listScene = new Scene (root);
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setScene(listScene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    void backScene(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("clientListServices.fxml"));
            Scene listScene = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(listScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
