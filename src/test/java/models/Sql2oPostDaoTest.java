package models;
import dao.Sql2oAuthorDao;
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
    private Sql2oAuthorDao authorDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        postDao = new Sql2oPostDao(sql2o);
        authorDao = new Sql2oAuthorDao(sql2o);

        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    public Post setupNewPost() {
        return new Post("Hello", 1);
    }

    public Author setupNewAuthor() {
        return new Author("The Author");
    }


    @Test
    public void addPost_generatesUniqueId() throws Exception {
        Post post =setupNewPost();
        int originalPostId =post.getId();
        postDao.add(post);
        assertNotEquals(originalPostId, post.getId());
    }

    @Test
    public void getAll_returnsAllPostObjectsAddedtoDB() throws Exception {
        Post post = setupNewPost();
        Post nextPost = setupNewPost();
        Post notAddedPost = setupNewPost();
        postDao.add(post);
        postDao.add(nextPost);
        assertEquals(2, postDao.getAll().size());
    }

    @Test
    public void getPostsById_returnsTrackWithCorrectId() throws Exception {
        Post post =setupNewPost();
        Post nextPost = setupNewPost();
        postDao.add(post);
        postDao.add(nextPost);
        assertEquals("Hello", postDao.findPostById(1).getContent());
    }

    @Test
    public void update_updateChangesContent() throws Exception {
        String initialContent = "Hello";
        Post post = new Post (initialContent, 1);
        postDao.add(post);
        postDao.update(post.getId(),"New Content",1);
        Post updatedPost =postDao.findPostById(post.getId());
        assertNotEquals(initialContent, updatedPost.getContent());
    }

    @Test
    public void delete_deleteByIdDeletesCorrectPost() throws Exception {
        Post post = setupNewPost();
        postDao.add(post);
        postDao.deleteById(post.getId());
        assertEquals(0,postDao.getAll().size());
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


        assertTrue(postDao.getAllPostsByAuthor(authorId).size()==2);



    }

    @Test
    public void clearAllClearsAllPosts() throws Exception {
        Post post = setupNewPost();
        Post otherPost = new Post("Post", 1);
        postDao.add(post);
        postDao.add(otherPost);
        int daoSize = postDao.getAll().size();
        postDao.clearAll();
        assertEquals(0,postDao.getAll().size());

    }






}