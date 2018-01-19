import java.util.HashMap;
import java.util.Map;

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
        String connectionString = "jdbc:h2:~/todolist.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oPostDao postDao = new Sql2oPostDao(sql2o);
        Sql2oAuthorDao authorDao = new Sql2oAuthorDao(sql2o);


        //homepage
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Author> allAuthors = authorDao.getAll();
            model.put("authors", allAuthors);
            List<Post> allPosts = postDao.getAll();
            model.put("posts", allPosts);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());



        // /post/delete all
        // /author/ delete all
        // ======= posts ========== //
        // /posts = all posts
        // /posts/new
        // ======= authors ===== //
        // /author = show all authors
        // /authors/new

    }

}
