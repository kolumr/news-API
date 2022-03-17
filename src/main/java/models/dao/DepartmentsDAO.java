package models.dao;

import models.pojos.Departments;

import java.util.List;

public interface DepartmentsDAO {
    //add department to db
    void addDepartment(Departments department);

    //Return all departments
    List<Departments> allDepartments();
}
