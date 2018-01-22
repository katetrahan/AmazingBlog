package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;


public class Post {
    private String content;
    private int id;
    private int authorId;



    public Post (String content, int authorId){
        this.content = content;
        this.authorId = authorId;

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id &&
                authorId == post.authorId &&
                Objects.equals(content, post.content);
    }

    @Override
    public int hashCode() {

        return Objects.hash(content, id, authorId);
    }
}
