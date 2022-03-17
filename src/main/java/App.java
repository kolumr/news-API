import models.dao.DepartmentsImplemetationDAO;
import models.dao.NewsImplementationDAO;
import models.dao.UsersImplementationDAO;
import models.pojos.Departments;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static spark.Spark.*;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
public class App {
    public static void main(String[] args) {
        //creating a connection to postgres
        String connectionString = "jdbc:postgresql://localhost:5432/news_portal";
        Sql2o sql2o = new Sql2o(connectionString, "postgres", "");
        Connection conn = sql2o.open();

        //Instantiating DAO implementations and Gson
        DepartmentsImplemetationDAO departmentsImplemetationDAO = new DepartmentsImplemetationDAO(sql2o);
        NewsImplementationDAO newsImplementationDAO = new NewsImplementationDAO(sql2o);
        UsersImplementationDAO usersImplementationDAO = new UsersImplementationDAO(sql2o);
        Gson gson = new Gson();

        //adding a new department
        post("/departments/new", "application/json", (request, response) -> {
            Departments department = gson.fromJson(request.body(),Departments.class);
            departmentsImplemetationDAO.addDepartment(department);
            return gson.toJson(department);
        });

        //getting all departments
        get("/departments", "application/json", (request, response) -> {
            return gson.toJson(departmentsImplemetationDAO.allDepartments());
        });

        //adding new news item
        post("/news/new", "application/json", (request, response) -> );
    }
}
