package gui.controller;


import gui.util.Constraints;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class DepartmentFormController implements Initializable {

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfName;

    @FXML
    private Label labelTextId;

    @FXML
    private Label labelErrorName;

    @FXML
    private Button btSave;

    @FXML
    private Button btCancel;
    
    @FXML
    public void onBtSaveAction(){
        System.out.println("onbtSaveAction");
    }
    
    @FXML
    public void onBtCancelAction(){
        System.out.println("onBtCancelAction");
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeNodes();
    }
    
    private void initializeNodes() {

        Constraints.setTextFieldInteger(tfId);
        Constraints.setTextFieldMaxLength(tfName, 30);

    }

}