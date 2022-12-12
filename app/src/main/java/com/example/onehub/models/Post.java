package com.example.onehub.models;

public class Post {
    private int id;
    private String titre;
    private String post_msg;
    private String date;
    private int user_id;
    private Post post;


    public Post() {
    }

    public Post(Post post) {
        this.post=post;
    }

    public Post(int id, int user_id , String titre, String post_msg, String date){
        this.id = id;
        this.user_id = user_id;
        this.titre = titre;
        this.post_msg = post_msg;
        this.date = date;
    }

    public Post(int user_id,String titre, String post_msg, String date) {
        this.user_id = user_id;
        this.titre = titre;
        this.post_msg = post_msg;
        this.date = date;
    }

    public Post(String titre, String post_msg, String date) {
        this.titre = titre;
        this.post_msg = post_msg;
        this.date = date;
    }

    public Post(String titre, String post_msg) {
        this.titre = titre;
        this.post_msg = post_msg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getPost_msg() {
        return post_msg;
    }

    public void setPost_msg(String post_msg) {
        this.post_msg = post_msg;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", post_msg='" + post_msg + '\'' +
                ", date='" + date + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}
