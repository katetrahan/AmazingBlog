package dao;

import models.*;

import java.util.List;

public interface PostDao {

    //create
    void add (Post post);

    //read
    List<Post> getAll();

    Post findPostById(int id);

    //update
    void update(int id, String content);

    //delete
    void deleteById(int id);
    void clearAllTracks();

}
