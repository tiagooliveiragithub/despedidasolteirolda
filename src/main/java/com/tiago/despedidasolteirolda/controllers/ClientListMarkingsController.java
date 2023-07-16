package com.tiago.despedidasolteirolda.controllers;

import com.tiago.despedidasolteirolda.data.FileManager;
import com.tiago.despedidasolteirolda.entities.Marking;
import com.tiago.despedidasolteirolda.entities.MarkingHistoric;
import com.tiago.despedidasolteirolda.entities.Service;
import com.tiago.despedidasolteirolda.entities.Session;
import com.tiago.despedidasolteirolda.entities.enums.Localidades;
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

public class ClientListMarkingsController implements Initializable {

    @FXML private TableView<Marking> tableView;
    @FXML private ComboBox<MarkingStates> states;
    private HashSet<Marking> markings = new HashSet<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        search();

        MenuItem editMarking = new MenuItem("Editar");
        editMarking.setOnAction(event -> {
            Marking marking = tableView.getSelectionModel().getSelectedItem();
            if (marking != null) {

            }
        });

        MenuItem rateMarking = new MenuItem("Avaliar marcação");
        rateMarking.setOnAction(event -> {
            Marking marking = tableView.getSelectionModel().getSelectedItem();

            if (marking != null && marking.getCurrentState() == MarkingStates.FINISHED) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Avaliar marcação");
                dialog.setHeaderText("Por favor, insira a avaliação (0-5):");
                Optional<String> result = dialog.showAndWait();

                if (result.isPresent()) {
                    try {
                        int rating = Integer.parseInt(result.get());
                        if (rating >= 0 && rating <= 5) {
                            marking.setRate(rating);
                            marking.update();
                        } else {
                            Alert alertInvalidRating = new Alert(Alert.AlertType.ERROR);
                            alertInvalidRating.setTitle("Erro");
                            alertInvalidRating.setHeaderText("Avaliação inválida!");
                            alertInvalidRating.setContentText("A avaliação deve estar entre 0 e 5.");
                            alertInvalidRating.showAndWait();
                        }
                    } catch (NumberFormatException e) {
                        Alert alertInvalidInput = new Alert(Alert.AlertType.ERROR);
                        alertInvalidInput.setTitle("Erro");
                        alertInvalidInput.setHeaderText("Entrada inválida!");
                        alertInvalidInput.setContentText("Por favor, insira um valor numérico válido.");
                        alertInvalidInput.showAndWait();
                    }
                }
            } else {
                Alert alertInvalidState = new Alert(Alert.AlertType.ERROR);
                alertInvalidState.setTitle("Erro");
                alertInvalidState.setHeaderText("Estado inválido para avaliação!");
                alertInvalidState.setContentText("A avaliação só é permitida neste estado atual.");
                alertInvalidState.showAndWait();
            }
        });
        tableView.setContextMenu(new ContextMenu(editMarking, rateMarking));


    }

    void search() {
        states.getItems().setAll(MarkingStates.values());
        Collection<Marking> markings = FileManager.getFileManager().getMarkings().values();
        HashSet<Marking> clientMarkings = new HashSet<>();
        for(Marking marking : markings) {
            if(marking.getClient() == Session.user) clientMarkings.add(marking);
        }
        tableView.getItems().setAll(clientMarkings);
        this.markings = clientMarkings;
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
