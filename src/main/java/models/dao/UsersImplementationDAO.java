package models.dao;

import models.pojos.Users;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class UsersImplementationDAO implements UsersDAO{
    private final Sql2o sql2o;

    public UsersImplementationDAO(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Users user) {
        String sql = "INSERT INTO users (name, position, role, department_id) VALUES (:username, :position, :role, :department_id)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(user)
                    .executeUpdate()
                    .getKey();
            user.setId(id);
        }
        catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Users> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM users")
                    .executeAndFetch(Users.class);
        }
    }

    @Override
    public List<Users> getAllUsersInDepartment(int departmentId) {
        List<Users> allDeptUsers = new ArrayList<>();
        try (Connection con = sql2o.open()) {
            allDeptUsers.add(con.createQuery("SELECT * FROM users WHERE department_id = :id")
                    .addParameter("id", departmentId)
                    .executeAndFetchFirst(Users.class));
        }
        return allDeptUsers;
    }

    @Override
    public Users getUserInfo(int id) {
        String sql = "SELECT * FROM users WHERE id = :id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Users.class);
        }
    }
}
