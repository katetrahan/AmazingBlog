package models;

import dao.Sql2oAuthorDao;
import dao.Sql2oPostDao;
import models.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Sql2o;

import org.sql2o.Connection;
import static org.junit.Assert.*;



public class Sql2oAuthorDaoTest {

    private Sql2oAuthorDao authorDao;
    private Sql2oPostDao postDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        authorDao = new Sql2oAuthorDao(sql2o);
        postDao = new Sql2oPostDao(sql2o);

        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    public Author setupNewAuthor() {
        return new Author("The Author");
    }

    @Test
    public void newAuthor_generatesUniqueId_1() throws Exception {
        Author author =setupNewAuthor();
        int originalAuthorId = author.getId();
        authorDao.add(author);
        assertNotEquals(originalAuthorId, author.getId());
    }
}
