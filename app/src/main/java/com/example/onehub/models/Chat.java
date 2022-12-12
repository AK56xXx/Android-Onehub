package com.example.onehub.models;

public class Chat {

    private int id;
    private int id_user;
    private String msg;
    private String date;
    private Chat chat;


    public Chat() {
    }

    public Chat(Chat chat) {
        this.chat=chat;
    }

    public Chat(int id, int id_user, String msg, String date) {
        this.id = id;
        this.id_user = id_user;
        this.msg = msg;
        this.date = date;
    }

    public Chat(int id_user, String msg, String date) {
        this.id_user = id_user;
        this.msg = msg;
        this.date = date;
    }

    public Chat(int id_user, String msg) {
        this.id_user = id_user;
        this.msg = msg;
    }

    public Chat(String msg, String date) {
        this.msg = msg;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", id_user=" + id_user +
                ", msg='" + msg + '\'' +
                ", date='" + date + '\'' +
                ", chat=" + chat +
                '}';
    }
}
