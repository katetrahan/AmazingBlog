package models;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;



public class Post {
    private String content;
//    private boolean published;
    private LocalDateTime createdAt;
    private int id;
    private int authorId;



    public Post (String content, int authorId){
        this.content = content;
//        this.published = false;
        this.createdAt = LocalDateTime.now();
        this.authorId = authorId;

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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

        if (id != post.id) return false;
        if (authorId != post.authorId) return false;
        if (content != null ? !content.equals(post.content) : post.content != null) return false;
        return createdAt != null ? createdAt.equals(post.createdAt) : post.createdAt == null;
    }

    @Override
    public int hashCode() {
        int result = content != null ? content.hashCode() : 0;
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + id;
        result = 31 * result + authorId;
        return result;
    }
}
