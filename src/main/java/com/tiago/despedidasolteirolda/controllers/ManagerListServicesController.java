package com.tiago.despedidasolteirolda.controllers;

import com.tiago.despedidasolteirolda.data.FileManager;
import com.tiago.despedidasolteirolda.entities.Provider;
import com.tiago.despedidasolteirolda.entities.Service;
import com.tiago.despedidasolteirolda.entities.Session;
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

public class ManagerListServicesController implements Initializable {

    @FXML private TableView<Service> tableView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FileManager fl = FileManager.getFileManager();
        Collection<Service> services = fl.getServices().values();
        tableView.getItems().setAll(services);

        MenuItem editService = new MenuItem("Editar");
        editService.setOnAction(event -> {
            Service service = tableView.getSelectionModel().getSelectedItem();
            if (service != null) {
               //fxWeaver.loadController(ProjectEditController.class).edit(project, this::save, clientService::findAll);
            }
        });


        MenuItem activeService = new MenuItem("Mudar Estado");
        activeService.setOnAction(event -> {
            Service service = tableView.getSelectionModel().getSelectedItem();

            Alert alertStateChange = new Alert(Alert.AlertType.CONFIRMATION);
            alertStateChange.setTitle("Confirmação");
            alertStateChange.setHeaderText("Queres alterar o estado do serviço?");
            Optional<ButtonType> result = alertStateChange.showAndWait();


            if (result.isPresent() && result.get() == ButtonType.OK) {
                boolean currentState = service.getActive();
                service.setActive(!currentState);
                service.update();
                tableView.refresh();
            }
        });
        tableView.setContextMenu(new ContextMenu(editService, activeService));

    }

    @FXML
    void backScene(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("managerMenu.fxml"));
            Scene menuScene = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(menuScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
