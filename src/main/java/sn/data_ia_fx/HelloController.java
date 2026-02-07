package sn.data_ia_fx;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import sn.data_ia_fx.entity.Assurance;
import sn.data_ia_fx.repository.AssuranceRepository;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {


    @FXML
    private Button b_add;

    @FXML
    private Button b_clear;

    @FXML
    private Button b_delete;

    @FXML
    private Button b_update;

    @FXML
    private TextField c_montant;
    @FXML
    private TextField c_search;

    @FXML
    private TextField c_nom;
    @FXML
    private TextField c_id;

    @FXML
    private TableColumn<?, ?> t_id;

    @FXML
    private TableColumn<?, ?> t_montant;

    @FXML
    private TableColumn<?, ?> t_nom;

    @FXML
    private TableColumn<?, ?> t_numero;

    @FXML
    private TableView<Assurance> tab;

    private AssuranceRepository assuranceRepository;

    public HelloController() {
        assuranceRepository = new AssuranceRepository();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAllAssurance();
    }

    void getAllAssurance() {
        ObservableList<Assurance> assurances = assuranceRepository.findAll();
       t_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        t_montant.setCellValueFactory(new PropertyValueFactory<>("montant"));
        t_nom.setCellValueFactory(new PropertyValueFactory<>("nomClient"));
        t_numero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        tab.setItems(assurances);
    }



    @FXML
    void addAssurrance(ActionEvent event) {

        Assurance assurance = new Assurance(c_nom.getText(),Double.parseDouble(c_montant.getText()));
        assuranceRepository.insert(assurance);
        clear(event);
        getAllAssurance();

    }

    @FXML
    void clear(ActionEvent event) {
        c_montant.clear();
        c_nom.clear();
    }

    @FXML
    void deleteAssurance(ActionEvent event) {
       int id =  tab.getSelectionModel().getSelectedItem().getId();
       assuranceRepository.delete(id);
       getAllAssurance();
    }

    @FXML
    void updateAssurance(ActionEvent event) {
        Assurance assurance = new Assurance(c_nom.getText(),Double.parseDouble(c_montant.getText()));
        assurance.setId(Integer.parseInt(c_id.getText()));
        assuranceRepository.update(assurance);
        clear(event);
        getAllAssurance();
    }


    public void editAssurance(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount() == 2) {
          Assurance assurance =   tab.getSelectionModel().getSelectedItem();
          c_nom.setText(assurance.getNomClient());
          c_montant.setText(String.valueOf(assurance.getMontant()));
          c_id.setText(String.valueOf(assurance.getId()));
        }
    }

    public void search(KeyEvent keyEvent) {
        ObservableList<Assurance> assurances = assuranceRepository.findAllSearch(c_search.getText());
        t_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        t_montant.setCellValueFactory(new PropertyValueFactory<>("montant"));
        t_nom.setCellValueFactory(new PropertyValueFactory<>("nomClient"));
        t_numero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        tab.setItems(assurances);
    }
}