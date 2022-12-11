package com.example.onehub.models;

public class User {

    private int id ;
    private String first_name;
    private String last_name;
    private String username;
    private String password;
    private String email;
    private String pic;
    private String position;
    private String role;
    private int blocked;
    private int attempts;

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(int id, String first_name, String last_name, String username, String password, String email, String pic, String position, String role , int blocked , int attempts) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.pic = pic;
        this.position = position;
        this.role = role;
        this.blocked = blocked;
        this.attempts = attempts;
    }

    public User(String first_name,String last_name,String username, String password, String email, String pic, String position, String role , int blocked, int attempts) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.pic = pic;
        this.position = position;
        this.role = role;
        this.blocked=blocked;
        this.attempts=attempts;
    }

    public User(int id, String first_name, String last_name, String username, String password, String role) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(String first_name, String last_name, String username, String password, String role) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(String first_name, String last_name, String username, String password, String role, int blocked) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.password = password;
        this.role = role;
        this.blocked = blocked;
    }

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(String username, String password, String role, int blocked) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.blocked = blocked;
    }

    public User(String username, int blocked, int attempts) {
        this.username = username;
        this.blocked = blocked;
        this.attempts = attempts;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getBlocked() {
        return blocked;
    }

    public void setBlocked(int blocked) {
        this.blocked = blocked;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", pic='" + pic + '\'' +
                ", position='" + position + '\'' +
                ", role='" + role + '\'' +
                ", blocked=" + blocked +
                ", attempts=" + attempts +
                '}';
    }
}
