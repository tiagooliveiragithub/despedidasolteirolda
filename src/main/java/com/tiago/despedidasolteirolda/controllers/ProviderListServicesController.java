package com.tiago.despedidasolteirolda.controllers;

import com.tiago.despedidasolteirolda.data.FileManager;
import com.tiago.despedidasolteirolda.entities.Provider;
import com.tiago.despedidasolteirolda.entities.Service;
import com.tiago.despedidasolteirolda.entities.Session;
import com.tiago.despedidasolteirolda.entities.enums.Localidades;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class ProviderListServicesController implements Initializable {

    @FXML private TableView<Service> tableView;
    @FXML private ComboBox<Localidades> local;
    private HashSet<Service> services = new HashSet<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        search();
    }

    void search() {
        local.getItems().setAll(Localidades.values());
        FileManager fl = FileManager.getFileManager();
        Provider provider = (Provider) Session.user;
        HashSet<Service> services = provider.getListServices();
        tableView.getItems().setAll(services);
        this.services = services;

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

            for (Service service : services) {
                if (service.getLocal() == selectedLocation) {
                    filteredServices.add(service);
                }
            }
            tableView.getItems().setAll(filteredServices);
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
}
