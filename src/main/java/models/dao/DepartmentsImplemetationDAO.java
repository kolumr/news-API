package models.dao;

import models.pojos.Departments;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class DepartmentsImplemetationDAO implements DepartmentsDAO{
    private final Sql2o sql2o;

    public DepartmentsImplemetationDAO ( Sql2o sql2o){
        this.sql2o = sql2o;
    }
    @Override
    public void addDepartment(Departments department) {
        String sql = "INSERT INTO departments (name, description) VALUES (:departmentName, :description)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(department)
                    .executeUpdate()
                    .getKey();
            department.setDepartmentId(id);
        }
        catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public List<Departments> allDepartments() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM departments;")
                    .executeAndFetch(Departments.class);
        }
    }
}
