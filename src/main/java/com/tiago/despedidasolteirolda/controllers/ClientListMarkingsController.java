package com.tiago.despedidasolteirolda.controllers;

import com.tiago.despedidasolteirolda.data.FileManager;
import com.tiago.despedidasolteirolda.entities.Marking;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

public class ClientListMarkingsController implements Initializable {

    @FXML
    private TableView<Marking> tableView;
    @FXML
    private Button createButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Collection<Marking> markings = FileManager.getFileManager().getMarkings().values();
        tableView.getItems().setAll(markings);
        // Enable multiple selections in the TableView
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    void createEntity() {
    }

    @FXML
    void backScene(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("clientMenu.fxml"));
            Scene menuScene = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(menuScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
