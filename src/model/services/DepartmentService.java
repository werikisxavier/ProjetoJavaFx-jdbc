package model.services;


import java.util.List;
import model.dao.DepartmentDao;
import model.dao.impl.DaoFactory;
import model.entities.Department;

public class DepartmentService {

    DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
    
    public List<Department> findAll() {
        return departmentDao.findAll();
    }
    
    public void saveOrUpdate(Department department){
        if(department.getId()==null){
            departmentDao.insert(department);
        }else{
            departmentDao.update(department);
        }
    }
    
}
