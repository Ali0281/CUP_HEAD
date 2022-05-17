package com.example.demo.Controller;

import com.example.demo.Model.MiniBoss;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MiniBossAnimation extends Transition {
    private ArrayList<MiniBoss> miniBosses = MiniBoss.getMiniBosses();

    public MiniBossAnimation() {
        this.setCycleDuration(Duration.millis(7000)); // random
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double frac) {
        int frame = (int) (frac * 60) % 4 + 1;
        for (MiniBoss miniBoss : miniBosses) {
            if (miniBoss.getFill() == Color.TRANSPARENT) continue;
            try {
                miniBoss.setFill(new ImagePattern(new Image(new FileInputStream("src/main/resources/com/example/demo/airplaneImages/miniBoss/" + frame + ".png"))));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            miniBoss.move();
        }

    }
}
