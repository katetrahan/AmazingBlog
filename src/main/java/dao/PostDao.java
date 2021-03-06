package dao;

import models.*;

import java.util.List;

public interface PostDao {

    //create
    void add (Post post);

    //read
    List<Post> getAll();
    List<Post> getAllPostsByAuthor(int authorId);

    Post findPostById(int id);

    //update
    void update(int id, String content, int authorId);

    //delete
    void deleteById(int id);
    void clearAll();

}
