package models.dao;

import models.pojos.Users;

import java.util.List;

public interface UsersDAO {
    //add user to DB
    void add(Users user);

    //return list of all users
    List<Users> getAll();

    //return list of all users in a department
    List<Users> getAllUsersInDepartment(int departmentId);

    //return specific information about a user
    Users getUserInfo(int userId);

}
