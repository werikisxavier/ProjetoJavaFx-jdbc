/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import gui.util.Alerts;
import gui.util.Constraints;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author W-E-R
 */
public class FXMLPrincipalController implements Initializable {
    

    
      @FXML
    private Button btSoma;

    @FXML
    private TextField tfValorA;

    @FXML
    private TextField tfValorB;

    @FXML
    private TextField tfResult;

    @FXML
    void handleButtonSalvar(ActionEvent event) {
        
        try{
        Double valorA = Double.parseDouble(tfValorA.getText());
        Double valorB = Double.parseDouble(tfValorB.getText());
        Double soma = valorA+valorB;
        tfResult.setText(String.valueOf(soma));
        
        }catch(Exception e ){
            Alerts.showAlert("Erro", null, "404", Alert.AlertType.ERROR);
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Constraints.setTextFieldDouble(tfValorA);
        Constraints.setTextFieldDouble(tfValorB);
    }    
    
}
