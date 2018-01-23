import java.util.HashMap;
import java.util.Map;

import com.sun.org.apache.xpath.internal.operations.Mod;
import dao.Sql2oAuthorDao;
import dao.Sql2oPostDao;
import models.Author;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;
import models.Post;
import java.util.ArrayList;
import java.util.List;



public class App {

    public static void main(String[] args) {
        staticFileLocation("/public");
        String connectionString = "jdbc:h2:~/todolist.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'"; //todolist?
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oPostDao postDao = new Sql2oPostDao(sql2o);
        Sql2oAuthorDao authorDao = new Sql2oAuthorDao(sql2o);


        //homepage
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Author> allAuthors = authorDao.getAll();
            model.put("authors", allAuthors);
//            List<Post> allPosts = postDao.getAll();
//            model.put("posts", allPosts);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());



        // post/delete all
        get ("/posts/delete", (request,response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Post> allPosts = postDao.getAll();
            model.put("posts", allPosts);
            postDao.clearAll();

            return new ModelAndView(model, "delete.hbs");
        }, new HandlebarsTemplateEngine());



        // author/ delete all
        get("/authors/deleteAll", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Author> allAuthors = authorDao.getAll();
            model.put("authors", allAuthors);
            authorDao.clearAllAuthors();
            return new ModelAndView(model, "delete.hbs");
        }, new HandlebarsTemplateEngine());





        // ======= posts ========== //

        // posts = all posts
        get("/posts", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Post> allPosts = postDao.getAll();
            model.put("posts", allPosts);
            return new ModelAndView(model, "posts.hbs");
        }, new HandlebarsTemplateEngine());


        // posts/new

        //get form
        get("/authors/:id/posts/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfAuthor = Integer.parseInt(request.params("id"));
            Author author =authorDao.findById(idOfAuthor);
            model.put("authors", author);

            return new ModelAndView(model, "post-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post form
        post("/authors/:id/posts/new", (request, response) -> {
            Map<String, Object> model =new HashMap<>();
            String content = request.queryParams("content");
            int authorId =Integer.parseInt(request.params("id"));
            Post newPost = new Post(content, authorId);
            postDao.add(newPost);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        // ======= authors ===== //






        // authors/new
        //get form
        get("/authors/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Author> allAuthors = authorDao.getAll();
            model.put("authors", allAuthors);
            return new ModelAndView(model, "author-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post form
        post("/authors/new",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            Author newAuthor = new Author (name);
            authorDao.add(newAuthor);
            List<Author> allAuthors =authorDao.getAll();
            model.put("authors",allAuthors);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        // author = show all authors
        get("/authors/:id", (request, response) -> {
            Map<String,Object> model = new HashMap<>();
            int idOfAuthor = Integer.parseInt(request.params("id"));
            Author author =authorDao.findById(idOfAuthor);
            List <Post> postList = postDao.getAllPostsByAuthor(idOfAuthor);
            model.put("posts", postList);
            model.put("authors", author);
            return new ModelAndView(model, "authors.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show an individual post that is nested in an author
        get("/authors/:authorId/posts/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfPostToFind =Integer.parseInt(request.params("id"));
            Post foundPost = postDao.findPostById(idOfPostToFind);
            model.put("post", foundPost);
            return new ModelAndView(model, "post-detail.hbs");
        }, new HandlebarsTemplateEngine());



        //update - show a form to update
        get("/authors/:id/posts/:postId/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfPostToFind = Integer.parseInt(request.params("postId"));
            Post editPost = postDao.findPostById(idOfPostToFind);
            int idOfAuthor = Integer.parseInt(request.params("id"));
            Author author = authorDao.findById(idOfAuthor);
            model.put("authors", author);
            model.put("editPost", editPost);
            return new ModelAndView(model, "post-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post :process a form to update a task
        post("/authors/:id/posts/:postId/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String newContent = request.queryParams("editcontent");
            int postId = Integer.parseInt(request.params("postId"));
            Post editPost = postDao.findPostById(postId);
            int idOfAuthor = Integer.parseInt(request.params("id"));
            Author author = authorDao.findById(idOfAuthor);
            postDao.update(postId, newContent, idOfAuthor);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());



        //delete an individual post

        get("/authors/:authorId/posts/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfPostToDelete = Integer.parseInt(request.params("id"));
            Post deletePost = postDao.findPostById(idOfPostToDelete);
            postDao.deleteById(idOfPostToDelete);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());










    }

}
