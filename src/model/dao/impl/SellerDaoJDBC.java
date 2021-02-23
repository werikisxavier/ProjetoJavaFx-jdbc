package model.dao.impl;

import com.mysql.jdbc.Statement;
import db.DB;
import db.DbException;
import db.DbIntegrityException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.entities.Seller;
import model.dao.SellerDao;
import model.entities.Department;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                    "INSERT INTO seller "
                    +"(Name, Email, BirthDate, BaseSalary, DepartmentId) "        
                    + "VALUES (?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
                    
            st.setString(1, obj.getName());
            st.setString(2, obj.getEmail());
            st.setDate(3, new Date(obj.getBirthDay().getTime()));
            st.setDouble(4, obj.getBaseSalary());
            st.setInt(5, obj.getDepartment().getId());
           
            int rows = st.executeUpdate();

            if (rows > 0) {
            rs= st.getGeneratedKeys();
            if(rs.next()){
                int id = rs.getInt(1);
                obj.setId(id);
                System.out.println("Complet, "+rows+" lines inseridas!");
            }else{
                throw new DbException("Erro inesperado, nenhuma linha foi inserida!");
            }

            }


        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Seller obj) {
        PreparedStatement st = null;
        ResultSet rs = null;

        
        try {
            conn.setAutoCommit(false);
            
            
            st= conn.prepareStatement(
            "UPDATE seller SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
            +"WHERE Id = ?");
            
            st.setString(1, obj.getName());
            st.setString(2, obj.getEmail());
            st.setDate(3, new java.sql.Date(obj.getBirthDay().getTime()));
            st.setDouble(4, obj.getBaseSalary());
            st.setInt(5, obj.getDepartment().getId());
            st.setInt(6, obj.getId());
            
            int rows = st.executeUpdate();
            
            if (rows > 0 ) {
                System.out.println("Complet, "+rows+" lines affecteds!");
            } else{
                System.out.println("Nenhuma linha foi alterada, Id não encontrado!");
            }
            conn.commit();
            
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new DbIntegrityException("Erro rollback! "+ex.getMessage());
            }
            throw new DbException(e.getMessage()); 
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
            
        }
        
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        
         try {
            st = conn.prepareStatement(
                    "DELETE FROM seller WHERE seller.Id = ?");

            st.setInt(1, id);
            int rows = st.executeUpdate();
            
             if (rows>0) {
                 System.out.println("Seller Id:"+id+" deleted com sucess!");
             } else {
                 System.out.println("Seller id not found!");
             }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
        
        
    }

    @Override
    public Seller fildById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE seller.Id = ?");

            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {

                Department dep = instantiateDepartment(rs);

                Seller obj = instantiateSeller(rs, dep);

                return obj;

            }
            return null;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Seller> findByDepartment(Department dep) {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Seller> list = new ArrayList<>();
        
        try {
            st = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE department.Id = ? "
                    + "ORDER BY seller.Name;");
            
            

            st.setInt(1, dep.getId());
            rs = st.executeQuery();

           
            while (rs.next()) {
                Seller obj = instantiateSeller(rs, dep);
                list.add(obj);
            }
            
            return list;


        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Seller> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Seller> list = new ArrayList<>();

        try {
            st = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "ORDER BY seller.Name;");

            rs = st.executeQuery();
            Map<Integer, Department> mapDep = new HashMap<>();

            while (rs.next()) {
                Department dep = mapDep.get(rs.getInt("DepartmentId"));
                if (dep == null) {
                    dep = instantiateDepartment(rs);
                    mapDep.put(rs.getInt("DepartmentId"), dep);
                }

                Seller obj = instantiateSeller(rs, dep);
                list.add(obj);
            }

            return list;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));
        return dep;
    }

    private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
        Seller obj = new Seller();
        obj.setId(rs.getInt("Id"));
        obj.setName(rs.getString("Name"));
        obj.setEmail(rs.getString("Email"));
        obj.setBirthDay(rs.getDate("BirthDate"));
        obj.setBaseSalary(rs.getDouble("BaseSalary"));
        obj.setDepartment(dep);
        return obj;

    }

}
