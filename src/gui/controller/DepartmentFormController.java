package gui.controller;

import db.DbException;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

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
    public void onBtCancelAction(ActionEvent event) {
        Utils.currentStage(event).close();
    }
    
    @FXML
    public void onBtSaveAction(ActionEvent event) {
        if (department == null) {
            throw new IllegalArgumentException("Department was null");
        }
        if (departmentService == null) {
            throw new IllegalArgumentException("DepartmentService was null");
        }
        try {
            department = getFormData();
            departmentService.saveOrUpdate(department);
            Utils.currentStage(event).close();

        } catch (DbException e) {
            Alerts.showAlert("Error saving object", null, e.getMessage(), Alert.AlertType.ERROR);

        }

    }
    
    private Department department;

    private DepartmentService departmentService;

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    

    private Department getFormData() {
        Department obj = new Department();
        obj.setId(Utils.tryParseToInt(tfId.getText()));
        obj.setName(tfName.getText());
        return obj;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeNodes();
    }

    private void initializeNodes() {

        Constraints.setTextFieldInteger(tfId);
        Constraints.setTextFieldMaxLength(tfName, 30);

    }

    //responsavel acessar o instaciar um novo department
    public void updateFormData() {
        if (department == null) {
            throw new IllegalArgumentException("Department was null");
        }

        tfId.setText(String.valueOf(department.getId()));
        tfName.setText(department.getName());

    }

}
