/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import model.entities.Cliente;

public class FXMLVisualizarController implements Initializable {

    @FXML
    private ComboBox<Cliente> comboBoxCliente;

    private ObservableList<Cliente> obsList;
    
    @FXML
    public void onComboBoxClienteAction(){
        Cliente cliente = comboBoxCliente.getSelectionModel().getSelectedItem();
        System.out.println("Cliente selecionado: "+ cliente.getName());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Cliente> list = new ArrayList<>();

        list.add(new Cliente(1, "Werikis"));
        list.add(new Cliente(2, "Igor"));
        list.add(new Cliente(3, "Marcelo"));
        list.add(new Cliente(4, "Devair"));

        obsList = FXCollections.observableArrayList(list);
        comboBoxCliente.setItems(obsList);

        Callback<ListView<Cliente>, ListCell<Cliente>> factory = lv -> new ListCell<Cliente>() {
            @Override
            protected void updateItem(Cliente item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" :  item.getName());
            }
        };

        comboBoxCliente.setCellFactory(factory);
        comboBoxCliente.setButtonCell(factory.call(null));

    }

}
