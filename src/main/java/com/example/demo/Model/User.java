package com.example.demo.Model;

import com.google.gson.annotations.Expose;

import java.net.URL;

public class User {
    private String username;
    private String password;
    private int avatar;

//    @Expose(serialize = false, deserialize = false)
//    private URL image;

    private int highScore = 0;

    public User(String username, String password, int avatar) {
        setPassword(password);
        setUsername(username);
        setAvatar(avatar);
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

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

//    public URL getImage() {
//        return image;
//    }
//
//    public void setImage(URL image) {
//        this.image = image;
//    }
}
