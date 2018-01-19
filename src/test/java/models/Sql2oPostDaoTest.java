package models;
import dao.Sql2oPostDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

import org.sql2o.Connection;
import java.time.LocalDateTime;



public class Sql2oPostDaoTest {

    private Sql2oPostDao postDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        postDao = new Sql2oPostDao(sql2o);

        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    public Post setupNewPost() {
        return new Post("Hello", true);
    }


    @Test
    public void addPost_generatesUniqueId() throws Exception {
        Post post =setupNewPost();
        int originalPostId =post.getId();
        postDao.add(post);
        assertNotEquals(originalPostId, post.getId());
    }






}