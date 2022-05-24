package com.example.demo.Model;

import java.util.ArrayList;

public class DataBase {
    private static DataBase instance;

    private ArrayList<User> users;
    private User loggedInUser;
    private int difficulty;

    private GameData lastGameData;

    private DataBase() {
        setUsers(new ArrayList<>());
        setLoggedInUser(null);
        setDifficulty(2);
    }

    public static DataBase getInstance() {
        if (instance == null) instance = new DataBase();
        return instance;
    }


    public void addUser(User user) {
        getUsers().add(user);
    }

    public User getUserByName(String name) {
        for (User user : getUsers()) {
            if (user.getUsername().equals(name)) return user;
        }
        return null;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void removeLoggedInUser() {
        getUsers().remove(getLoggedInUser());
        setLoggedInUser(null);
    }

    public GameData getLastGameData() {
        return lastGameData;
    }

    public void setLastGameData(GameData lastGameData) {
        this.lastGameData = lastGameData;
    }
}
