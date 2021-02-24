package gui.controller;

import Application.Program;
import gui.util.Alerts;
import gui.util.Utils;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentListController implements Initializable {

    private DepartmentService departmentService;

    public void setDepartmentservice(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    private ObservableList<Department> obsList;
    
    @FXML
    private TableView<Department> tableViewDepartment;

    @FXML
    private TableColumn<Department, Integer> tableColumnId;

    @FXML
    private TableColumn<Department, String> tableColumnName;

    @FXML
    private Button btNew;

    @FXML
    private void onBtNewAction(ActionEvent event) {
        Stage parentStage = Utils.currentStage(event);
        Department department = new Department();
        createDialogForm(department,"/gui/view/FXMLDepartmentForm.fxml", parentStage);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeNodes();
    }

    private void initializeNodes() {
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));

        //macete para colocar a tableView ocupando todo o espaco da mainView;
        Stage stage = (Stage) Program.getMainScene().getWindow();
        tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());

    }
    
    //responsavel acessar o servico carregar os departamentos e colocar na tableView
    public void updateTableView(){
        if(departmentService == null){
            throw new IllegalArgumentException("Service was null");
        }
        
        List<Department> list = departmentService.findAll();
        obsList=FXCollections.observableArrayList(list);
        tableViewDepartment.setItems(obsList);
    }

    
    private void createDialogForm(Department department, String absolutName, Stage parentStage){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absolutName));
            Pane newPane = loader.load();
            
            DepartmentFormController controller = loader.getController();
            controller.setDepartment(department);
            controller.setDepartmentService(new DepartmentService());
            controller.updateFormData();
            
            //vou abrir um palco na frente do outro
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Enter Department data");
            dialogStage.setScene(new Scene(newPane));
            dialogStage.setResizable(false);
            dialogStage.initOwner(parentStage); //pai da minha nova Cena
            dialogStage.initModality(Modality.WINDOW_MODAL);//travar tela
            dialogStage.showAndWait();
            
        } catch (IOException e) {
            Alerts.showAlert("IO Exception", "Erro laoding view", e.getMessage(), Alert.AlertType.ERROR);
        }
        
    }
}
