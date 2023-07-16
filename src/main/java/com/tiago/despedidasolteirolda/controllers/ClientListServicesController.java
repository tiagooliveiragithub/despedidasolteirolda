package com.tiago.despedidasolteirolda.controllers;

import com.tiago.despedidasolteirolda.data.FileManager;
import com.tiago.despedidasolteirolda.entities.Marking;
import com.tiago.despedidasolteirolda.entities.Provider;
import com.tiago.despedidasolteirolda.entities.Service;
import com.tiago.despedidasolteirolda.entities.Session;
import com.tiago.despedidasolteirolda.entities.enums.Localidades;
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
import java.util.*;

public class ClientListServicesController implements Initializable {

    @FXML private TableView<Service> tableView;
    @FXML private Button createButton;
    @FXML private ComboBox<Localidades> local;
    private HashSet<Service> services = new HashSet<>();


    // Data structure to store selected services temporarily
    private ObservableList<Service> selectedServices = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        search();

        // Enable multiple selections in the TableView
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        tableView.getSelectionModel().getSelectedItems().addListener((ListChangeListener.Change<? extends Service> change) -> {
            // Update the visibility of the createButton based on the number of selected items
            createButton.setVisible(tableView.getSelectionModel().getSelectedItems().size() > 0);
        });
    }

    void search() {
        local.getItems().setAll(Localidades.values());
        Collection<Service> services = FileManager.getFileManager().getServices().values();
        HashSet<Service> servicesFilter = new HashSet<>();
        for(Service service : services) {
            if(service.getActive()) {
                service.setPrice(service.getPrice() * 1.35);
                servicesFilter.add(service);

            }
        }
        tableView.getItems().setAll(servicesFilter);
        this.services = servicesFilter;
    }

    @FXML
    void filterClear(MouseEvent event) {
        tableView.getItems().setAll(services);
    }

    @FXML
    void filterServices(MouseEvent event) {
        Localidades selectedLocation = local.getValue();

        if (selectedLocation != null) {
            Set<Service> filteredServices = new HashSet<>();
            for (Service service : this.services) {
                if (service.getLocal() == selectedLocation) {
                    filteredServices.add(service);
                }
            }
            tableView.getItems().setAll(filteredServices);
        }
    }

    @FXML
    void createEntity() {
        ObservableList<Service> selectedItems = tableView.getSelectionModel().getSelectedItems();
        if (!selectedItems.isEmpty()) {
            Marking newMarking = new Marking();
            // Process the selected entities here
            for (Service service : selectedItems) {
                newMarking.addServiceApplied(service);
            }

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("clientCreateMarking.fxml"));
                Parent root = fxmlLoader.load();
                Scene createScene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(createScene);

                // Get the controller instance and set the newMarking
                ClientCreateMarkingController markingController = fxmlLoader.getController();
                markingController.newMarking = newMarking;

                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
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
