package com.tiago.despedidasolteirolda.controllers;

import com.tiago.despedidasolteirolda.data.FileManager;
import com.tiago.despedidasolteirolda.entities.Client;
import com.tiago.despedidasolteirolda.entities.Person;
import com.tiago.despedidasolteirolda.entities.Provider;
import com.tiago.despedidasolteirolda.entities.Service;
import com.tiago.despedidasolteirolda.entities.enums.Localidades;
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
import java.time.LocalDate;
import java.util.*;

public class ManagerListRegisterController implements Initializable {

    @FXML private TableView<Person> tableView;
    private Collection<Person> people = new HashSet<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        search();

        MenuItem editPerson= new MenuItem("Editar");
        editPerson.setOnAction(event -> {
            Person person = tableView.getSelectionModel().getSelectedItem();
            if (person != null) {

            }
        });


        MenuItem activePerson = new MenuItem("Ativar");
        activePerson.setOnAction(event -> {
            Person person = tableView.getSelectionModel().getSelectedItem();

            Alert alertStateChange = new Alert(Alert.AlertType.CONFIRMATION);
            alertStateChange.setTitle("Confirmação");
            alertStateChange.setHeaderText("Desejas mesmo ativar o utilizador?");
            Optional<ButtonType> result = alertStateChange.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                person.setUserActive(true);
                person.update();
                tableView.refresh();
            }
        });
        tableView.setContextMenu(new ContextMenu(editPerson, activePerson));

    }

    void search() {
        FileManager fl = FileManager.getFileManager();
        HashSet<Person> people = new HashSet<>();
        people.addAll(fl.getProviders().values());
        people.addAll(fl.getClients().values());
        HashSet<Person> peopleFiltered = new HashSet<>();
        for(Person p : people) {
            if(!p.getUserActive()) peopleFiltered.add(p);
        }
        tableView.getItems().setAll(peopleFiltered);
        this.people = peopleFiltered;
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
