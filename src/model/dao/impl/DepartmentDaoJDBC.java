
package model.dao.impl;

import com.mysql.jdbc.Statement;
import db.DB;
import db.DbException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.entities.Department;
import model.dao.DepartmentDao;


public class DepartmentDaoJDBC implements DepartmentDao{

    private Connection conn;
    
    public DepartmentDaoJDBC(Connection conn) {
        this.conn=conn;
    }
    
    @Override
    public void insert(Department obj) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                    "INSERT INTO department "
                    + "(Name) "
                    + "VALUES (?)",
                    Statement.RETURN_GENERATED_KEYS);
            
            
            st.setString(1, obj.getName());
            int rows = st.executeUpdate();

            if (rows > 0) {
                rs = st.getGeneratedKeys();

                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
            } else {
                throw new DbException("Erro inesperado, nenhuma linha foi inserida!");
            }


        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }
    

    @Override
    public void update(Department obj) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                    "UPDATE department SET Name = ? WHERE Id = ?");

            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());

            int rows = st.executeUpdate();

            if (rows > 0) {
                System.out.println("Department id:" + obj.getId() + " alterado com sucess!");

            } else {
                throw new DbException("Erro inesperado, nenhuma linha foi alterada!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                    "DELETE FROM department WHERE Id = ?");

            st.setInt(1, id);
            int rows = st.executeUpdate();

            if (rows > 0) {
                System.out.println("Department id:" + id + " deletado com sucess!");

            } else {
                throw new DbException("Erro inesperado, nenhuma linha foi deletada!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
        
    }

    @Override
    public Department fildById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs=null;

        try {
            st = conn.prepareStatement(
                    "SELECT * FROM department WHERE Id = ?");

            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                return new Department(rs.getInt("Id"),rs.getString("Name"));

            } else {
                throw new DbException("Nenhum ID encontrado!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                    "SELECT * FROM department");

            rs = st.executeQuery();
            List<Department> list = new ArrayList<>();

            while (rs.next()) {
                Department dep = new Department();
                dep.setId(rs.getInt("Id"));
                dep.setName(rs.getString("Name"));
                list.add(dep);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
    
}
