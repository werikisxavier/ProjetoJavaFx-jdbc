/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 *
 * @author W-E-R
 */
public class FXMLAboutController implements Initializable {

    @FXML
    private Button btVoltar;
    
    @FXML 
    private void onButtonBtVoltarAction(){
        System.out.println("Voltou");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }
    
}
