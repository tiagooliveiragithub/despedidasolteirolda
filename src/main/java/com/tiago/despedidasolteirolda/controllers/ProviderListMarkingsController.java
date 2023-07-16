package com.tiago.despedidasolteirolda.controllers;

import com.tiago.despedidasolteirolda.data.FileManager;
import com.tiago.despedidasolteirolda.entities.Marking;
import com.tiago.despedidasolteirolda.entities.Session;
import com.tiago.despedidasolteirolda.entities.enums.MarkingStates;
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

public class ProviderListMarkingsController implements Initializable {

    @FXML
    private TableView<Marking> tableView;
    @FXML private ComboBox<MarkingStates> states;
    private Set<Marking> markings = new HashSet<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        search();

        MenuItem editMarking = new MenuItem("Editar");
        editMarking.setOnAction(event -> {
            Marking marking = tableView.getSelectionModel().getSelectedItem();
            if (marking != null) {

            }
        });

        MenuItem changeStateMarking = new MenuItem("Avançar estado");
        changeStateMarking.setOnAction(event -> {
            Marking marking = tableView.getSelectionModel().getSelectedItem();

            Alert alertStateChange = new Alert(Alert.AlertType.CONFIRMATION);
            alertStateChange.setTitle("Confirmação");
            alertStateChange.setHeaderText("Queres mesmo avançar o estado da marcação?");
            Optional<ButtonType> result = alertStateChange.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                marking.setCurrentState();
                marking.update();
                tableView.refresh();
            }
        });
        tableView.setContextMenu(new ContextMenu(editMarking, changeStateMarking));
    }

    void search() {
        states.getItems().setAll(MarkingStates.values());
        Collection<Marking> markings = FileManager.getFileManager().getMarkings().values();
        HashSet<Marking> markingsUser = new HashSet<>();
        for(Marking marking : markings) {
            if(marking.getProviders().contains(Session.user)) markingsUser.add(marking);
        }
        tableView.getItems().setAll(markingsUser);
        this.markings = markingsUser;
    }

    @FXML
    void filterClear(MouseEvent event) {
        tableView.getItems().setAll(markings);
    }

    @FXML
    void filterMarkings(MouseEvent event) {
        MarkingStates selectedState = states.getValue();

        if (selectedState != null) {
            Set<Marking> filteredMarkings = new HashSet<>();
            for (Marking marking : this.markings) {
                if (marking.getCurrentState() == selectedState) {
                    filteredMarkings.add(marking);
                }
            }
            tableView.getItems().setAll(filteredMarkings);
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
