import models.dao.DepartmentsImplemetationDAO;
import models.dao.NewsImplementationDAO;
import models.dao.UsersImplementationDAO;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class App {
    public static void main(String[] args) {
        //creating a connection to postgres
        String connectionString = "jdbc:postgresql://localhost:5432/news_portal";
        Sql2o sql2o = new Sql2o(connectionString, "postgres", "");
        Connection conn = sql2o.open();

        //Instantiating DAO implementations
        DepartmentsImplemetationDAO departmentsImplemetationDAO = new DepartmentsImplemetationDAO(sql2o);
        NewsImplementationDAO newsImplementationDAO = new NewsImplementationDAO(sql2o);
        UsersImplementationDAO usersImplementationDAO = new UsersImplementationDAO(sql2o);


    }
}
