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

    @Test
    public void getAll_returnsAllAuthors() throws Exception {
        Author author =setupNewAuthor();
        Author nextAuthor =setupNewAuthor();
        Author notAddedAuthor =setupNewAuthor();
        authorDao.add(author);
        authorDao.add(nextAuthor);
        assertEquals(2,authorDao.getAll().size());
    }

    @Test
    public void findById_existingArtistsCanBeFoundById() throws Exception {
        Author author =setupNewAuthor();
        int originalAuthorId = author.getId();
        authorDao.add(author);
        assertNotEquals(originalAuthorId, author.getId());
    }


    @Test
    public void updateChangesAuthorName() throws Exception {
        String initialName = "The Author";
        Author author = new Author(initialName);
        authorDao.add(author);

        authorDao.update(author.getId(), "Oprah");
        Author updatedAuthor = authorDao.findById(author.getId());
        assertNotEquals(initialName, updatedAuthor.getName());
    }

    @Test
    public void authorCanBeDeleted() throws Exception {
        Author author =setupNewAuthor();
        authorDao.add(author);
        authorDao.deleteById(author.getId());
        assertEquals(0,authorDao.getAll().size());
    }

    @Test
    public void clearAll_ClearAllDeletesAll() throws Exception {
        Author author =setupNewAuthor();
        Author otherAuthor =setupNewAuthor();
        authorDao.add(author);
        authorDao.add(otherAuthor);
        int daoSize = authorDao.getAll().size();
        authorDao.clearAllAuthors();
        assertEquals(0,authorDao.getAll().size());
    }



    @Test
    public void getAllPostsByAuthorsReturnsCorrectly() {
        Author author =setupNewAuthor();
        authorDao.add(author);
        int authorId = author.getId();
        Post newPost =new Post("post1", authorId);
        Post secondPost = new Post ("post2", authorId);
        postDao.add(newPost);
        postDao.add(secondPost);


        assertTrue(authorDao.getAllPostsByAuthor(authorId).size()==2);


    }


}
