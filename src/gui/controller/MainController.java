/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import Application.Program;
import gui.util.Alerts;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.DepartmentService;

public class MainController implements Initializable {
    
    @FXML
    private MenuItem menuItemSeller;
    
    @FXML
    private MenuItem menuItemDepartment;
    
    @FXML
    private MenuItem menuItemAbout;
    
    @FXML
    public void onMenuItemSellerAction() {
        System.out.println("onMenuItemSellerAction");
    }
    
    @FXML
    public void onMenuItemDepartmentAction() {
        loadView("/gui/view/FXMLDepartmentList.fxml", (DepartmentListController controller) -> {
            controller.setDepartmentservice(new DepartmentService());
            controller.updateTableView();
        });
        
    }
    
    @FXML
    public void onMenuItemAboutAction() {
        loadView("/gui/view/FXMLAbout.fxml", x -> {
        });
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    private synchronized <T> void loadView(String absoluteName, Consumer<T> initializeAction) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            VBox newVBox = loader.load();
            
            Scene mainScene = Program.getMainScene();
            VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
            
            Node mainMenu = mainVBox.getChildren().get(0);
            mainVBox.getChildren().clear();
            mainVBox.getChildren().add(mainMenu);
            mainVBox.getChildren().addAll(newVBox.getChildren());
            
            T controller = loader.getController();
            initializeAction.accept(controller);
            
        } catch (IOException ex) {
            Alerts.showAlert("IO Exception", null, ex.getMessage(), Alert.AlertType.ERROR);
        }
        
    }
    
}
