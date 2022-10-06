package com.uet.int2204.group2.Menu;

import com.uet.int2204.group2.component.GameState;
import com.uet.int2204.group2.Bomberman;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;

public class GameMenu extends Parent {

    public GameMenu() {
        VBox menu0 = new VBox(10);
        VBox menu1 = new VBox(10);
        menu0.setTranslateX(200);
        menu0.setTranslateY(200);
        menu1.setTranslateX(200);
        menu1.setTranslateY(200);
        final int offset = 400;
        MenuButton btnStart = new MenuButton("START");
        MenuButton btnSound = new MenuButton("SOUND");
        MenuButton btnBack = new MenuButton("BACK");
        btnStart.setOnMouseClicked(event -> {
            GameState game = new GameState();
            Bomberman.setRoot(game.getRoot());
            Bomberman.setInputHandlers(game.getInputHandlers());
            game.start();
        });
        MenuButton btnOptions = new MenuButton("OPTIONS");
        btnOptions.setOnMouseClicked(event -> {
            getChildren().add(menu1);
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5), menu0);
            tt.setToX(menu0.getTranslateX() - offset);
            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu1);
            tt1.setToX(menu0.getTranslateX());
            tt.play();
            tt1.play();
            tt.setOnFinished(evt -> {
                getChildren().remove(menu0);
            });
        });

        btnBack.setOnMouseClicked(event -> {
            getChildren().add(menu0);
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu1);
            tt.setToX(menu0.getTranslateX() + offset);
            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu0);
            tt1.setToX(menu1.getTranslateX());
            tt.play();
            tt1.play();
            tt.setOnFinished(evt -> {
                getChildren().remove(menu1);
            });
        });
        MenuButton btnExit = new MenuButton("Exit");
        btnExit.setOnMouseClicked(event -> {
            System.exit(0);
        });
        menu0.getChildren().addAll(btnStart, btnOptions, btnExit);
        menu1.getChildren().addAll(btnSound, btnBack);
//        Rectangle bg = new Rectangle(800,600);
//        bg.setFill(Color.GRAY);
//        bg.setOpacity(0.4);
        getChildren().addAll(menu0);
    }
}
