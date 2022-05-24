package com.example.demo.View;

public enum Menus {
    REGISTER_MENU("loginPage.fxml"),
    GAME_MENU("gamePage.fxml"),
    MAIN_MENU("mainMenuPage.fxml"),
    PROFILE_MENU("profilePage.fxml"),
    RANKING_PAGE("rankingPage.fxml"),
    AVATAR_SELECTION("avatarSelection.fxml"),
    SCORE_PAGE("scorePage.fxml"),
    ;

    private final String name;

    Menus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
