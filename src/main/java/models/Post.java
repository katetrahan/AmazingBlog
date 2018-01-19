package models;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;



public class Post {
    private String content;
    //    private static ArrayList<Post> instances = new ArrayList<>();
    private boolean published;
    private LocalDateTime createdAt;
    private int id;



    public Post (String content, Boolean published){
        this.content = content;
        this.published = false;
        this.createdAt = LocalDateTime.now();
//        instances.add(this);
//        this.id = instances.size();
//        id = instances.size(); //I’m never null of zero. How come?
    }

    public String getContent() {
        return content;
    }

//    public static ArrayList<Post> getAll(){
//        return instances;
//    }

//    public static void clearAllPosts(){
//        instances.clear();
//    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (published != post.published) return false;
        if (id != post.id) return false;
        if (content != null ? !content.equals(post.content) : post.content != null) return false;
        return createdAt != null ? createdAt.equals(post.createdAt) : post.createdAt == null;
    }

    @Override
    public int hashCode() {
        int result = content != null ? content.hashCode() : 0;
        result = 31 * result + (published ? 1 : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }



}
