package model.services;

import java.util.ArrayList;
import java.util.List;
import model.dao.DepartmentDao;
import model.dao.impl.DaoFactory;
import model.entities.Department;

public class DepartmentService {

    public List<Department> findAll() {
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
        
        return departmentDao.findAll();
    }
}
