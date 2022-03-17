package models.dao;

import models.pojos.News;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class NewsImplementationDAO implements NewsDAO{
    private final Sql2o sql2o;

    public NewsImplementationDAO( Sql2o sql2o){
        this.sql2o = sql2o;
    }


    @Override
    public void addNews(News news) {
        String sql = "INSERT INTO news (headline, content, department_id) VALUES (:headline, :content, :department_id)";
        try(Connection conn = sql2o.open()){
            int id = (int) conn.createQuery(sql, true)
                    .bind(news)
                    .executeUpdate()
                    .getKey();
            news.setNewsId(id);
        }
        catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public List<News> getGeneralNews() {
        return null;
    }

    @Override
    public List<News> getDepartmentNews(int departmentId) {
        List<News> allDeptNews = new ArrayList<>();
        try (Connection conn = sql2o.open()) {
            allDeptNews.add(conn.createQuery("SELECT * FROM news WHERE department_id = :id")
                    .addParameter("id", departmentId)
                    .executeAndFetchFirst(News.class));
        }
        return allDeptNews;
    }
}
