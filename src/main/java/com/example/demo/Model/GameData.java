package com.example.demo.Model;

public class GameData {
    private long time;
    private int score;
    private boolean win;

    public void setTime(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }
}
