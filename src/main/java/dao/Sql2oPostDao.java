package dao;

import models.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;


public class Sql2oPostDao implements PostDao {

    private final Sql2o sql2o;
    public Sql2oPostDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Post post){
        String sql = "INSERT INTO posts (content, published) VALUES (:content, :published)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .bind(post)
                    .executeUpdate()
                    .getKey();
            post.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
