/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import Application.Program;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author W-E-R
 */
public class AboutController implements Initializable {

    @FXML
    private VBox VBoxAbout;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Stage stage = (Stage) Program.getMainScene().getWindow();
        VBoxAbout.prefHeightProperty().bind(stage.heightProperty());

    }

}
