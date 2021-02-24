package gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FXMLDepartmentFormController implements Initializable {

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfName;

    @FXML
    private Label labelTextId;

    @FXML
    private Label labelTextName;

    @FXML
    private Button btSave;

    @FXML
    private Button btCancel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
