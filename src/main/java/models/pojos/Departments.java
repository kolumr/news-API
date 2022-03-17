package models.pojos;

import java.util.Objects;

public class Departments {
    private int id;
    private String name;
    private String description;

    public Departments(String departmentName, String description){
        this.name = departmentName;
        this.description = description;
    }

    public int getDepartmentId() {
        return id;
    }

    public void setDepartmentId(int departmentId) {
        this.id = departmentId;
    }

    public String getDepartmentName() {
        return name;
    }

    public void setDepartmentName(String departmentName) {
        this.name = departmentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Departments that = (Departments) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }
}
