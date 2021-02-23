package gui.controller;

import Application.Program;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

public class FXMLDepartmentListController implements Initializable {

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
    private void onBtNewAction() {
        System.out.println("onBtNewAction");
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

}
