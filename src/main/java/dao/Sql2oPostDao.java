package dao;

import models.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;


public class Sql2oPostDao implements PostDao {

    private final Sql2o sql2o;
    public Sql2oPostDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Post post){
        String sql = "INSERT INTO posts (content, authorId) VALUES (:content, :authorId)";
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

    @Override
    public List<Post> getAll() {
        String sql = "SELECT * FROM posts";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Post.class);
        }
    }

    @Override
    public Post findPostById(int id) {
        String sql = "SELECT * FROM posts WHERE id = :id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Post.class);
        }
    }

    @Override
    public List<Post> getAllPostsByAuthor(int authorId) {
        String sql = "SELECT * FROM posts WHERE authorId = :authorId";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("authorId", authorId)
                    .executeAndFetch(Post.class);


        }
    }

    @Override
    public void update(int id, String newContent, int authorId){
        String sql ="UPDATE posts SET content = :content WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("content", newContent)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById (int id) {
        String sql = "DELETE from posts WHERE id = :id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("id",id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE from posts";
        try (Connection con = sql2o.open()){
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

}
