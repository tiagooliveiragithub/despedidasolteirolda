package com.tiago.despedidasolteirolda.controllers;

import com.tiago.despedidasolteirolda.entities.Marking;
import com.tiago.despedidasolteirolda.entities.Session;
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
        if (dateField.getValue() != null) {
            newMarking.setMarkingDay(dateField.getValue());
            newMarking.setClient(Session.user);
            newMarking.create();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void backScene(ActionEvent event) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
    }

}
