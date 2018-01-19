package dao;

import models.*;

import java.util.List;


public interface AuthorDao {

    //create
    void add(Author author);

    //read
    List<Author> getAll();
   List<Post> getAllPostsByAuthor(int authorId);

    Author findById(int id);

    //update
    //delete
}
