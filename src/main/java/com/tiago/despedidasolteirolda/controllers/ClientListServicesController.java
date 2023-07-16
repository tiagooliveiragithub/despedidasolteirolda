package com.tiago.despedidasolteirolda.controllers;

import com.tiago.despedidasolteirolda.data.FileManager;
import com.tiago.despedidasolteirolda.entities.Marking;
import com.tiago.despedidasolteirolda.entities.Provider;
import com.tiago.despedidasolteirolda.entities.Service;
import com.tiago.despedidasolteirolda.entities.Session;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

public class ClientListServicesController implements Initializable {

    @FXML
    private TableView<Service> tableView;
    @FXML
    private Button createButton;

    // Data structure to store selected services temporarily
    private ObservableList<Service> selectedServices = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Collection<Service> services = FileManager.getFileManager().getServices().values();
        tableView.getItems().setAll(services);

        // Enable multiple selections in the TableView
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        tableView.getSelectionModel().getSelectedItems().addListener((ListChangeListener.Change<? extends Service> change) -> {
            // Update the visibility of the createButton based on the number of selected items
            createButton.setVisible(tableView.getSelectionModel().getSelectedItems().size() > 0);
        });
    }

    @FXML
    void createEntity() {
        ObservableList<Service> selectedItems = tableView.getSelectionModel().getSelectedItems();
        if (!selectedItems.isEmpty()) {
            Marking newMarking = new Marking();
            // Process the selected entities here
            for (Service service : selectedItems) {
                newMarking.getServicesApplied().add(service);
            }

            Alert alertMarkingCreation = new Alert(Alert.AlertType.CONFIRMATION);
            alertMarkingCreation.setTitle("Confirmação");
            alertMarkingCreation.setHeaderText("Pretendes mesmo criar uma marcação?");
            Optional<ButtonType> result = alertMarkingCreation.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                newMarking.create();
                System.out.println("Marcação criada: " + newMarking.getServicesApplied().toString());
            }

        }
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
