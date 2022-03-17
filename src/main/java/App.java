import exceptions.ApiException;
import models.dao.DepartmentsImplemetationDAO;
import models.dao.NewsImplementationDAO;
import models.dao.UsersImplementationDAO;
import models.pojos.Departments;
import models.pojos.News;
import models.pojos.Users;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static spark.Spark.*;
import java.util.HashMap;
import java.util.List;
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
            response.status(201);
            return gson.toJson(department);
        });

        //getting all departments
        get("/departments", "application/json", (request, response) -> {
            return gson.toJson(departmentsImplemetationDAO.allDepartments());
        });

        //adding new news item
        post("/news/new", "application/json", (request, response) -> {
            News news = gson.fromJson(request.body(), News.class);
            newsImplementationDAO.addNews(news);
            response.status(201);
            return gson.toJson(news);
        });

        //adding a new user
        post("/users/new", "application/json", (request, response) -> {
            Users user = gson.fromJson(request.body(), Users.class);
            usersImplementationDAO.add(user);
            response.status(201);
            return gson.toJson(user);
        });

        //fetching a specific user
        get("users/:userId", "application/json", (request, response) -> {
            int userId = Integer.parseInt(request.params("userId"));
            Users user = usersImplementationDAO.getUserInfo(userId);

            if (user == null){
                throw new ApiException(404, String.format("No User with the id: \"%s\" exists", request.params("userId")));
            }

            return  gson.toJson(user);

        });

        //fetching all users in a department
        get("departments/:departmentId/users", "application/json", (request, response) -> {
            int departmentId = Integer.parseInt(request.params("departmentId"));
            List<Users> departmentUsers = usersImplementationDAO.getAllUsersInDepartment(departmentId);

            if (departmentUsers == null){
                throw new ApiException(404, String.format("No department with the id: \"%s\" exists", request.params("departmentId")));
            }

            return  gson.toJson(departmentUsers);

        });

        exception(ApiException.class, (exc, req, res) -> {
            ApiException err = (ApiException) exc;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            res.type("application/json"); //after does not run in case of an exception.
            res.status(err.getStatusCode()); //set the status
            res.body(gson.toJson(jsonMap));  //set the output.
        });


        after((req, res) ->{
            res.type("application/json");
        });
    }
}
