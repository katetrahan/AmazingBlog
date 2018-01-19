package dao;

import models.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;


public class Sql2oAuthorDao implements AuthorDao {
    private final Sql2o sql2o;
    public Sql2oAuthorDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Author author) {
        String sql ="INSERT INTO authors (name) VALUES (:name)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .bind(author)
                    .executeUpdate()
                    .getKey();
            author.setId(id);
        }
        catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Author> getAll() {
        String sql = "SELECT * FROM artists";
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM authors")
                    .executeAndFetch(Author.class);
        }

    }

}
