package com.uet.int2204.group2.Menu;

import com.uet.int2204.group2.Bomberman;
import com.uet.int2204.group2.Sound.Sound;
import com.uet.int2204.group2.component.GameState;

import com.uet.int2204.group2.utils.ResourceManager;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.util.Collection;

public class GameMenu extends Parent {
    GameState game = new GameState();
    private Image image;

    public GameMenu() {
        VBox menuStart = new VBox(30);
        VBox opsion = new VBox(30);
        menuStart.setTranslateX(170);
        menuStart.setTranslateY(220);
        opsion.setTranslateX(0);
        opsion.setTranslateY(200);
        final int offset = 400;
        /**
         * Menu start.
         */
        MenuButton btnStart = new MenuButton("START");
        MenuButton btnOptions = new MenuButton("INSTRUCTION");
        MenuButton btnExit = new MenuButton("EXIT");
        MenuButton btnBack = new MenuButton("BACK");
        btnBack.setTranslateX(-100);
        /**
         * Menu Pause.
         */
        MenuButton btnResume = new MenuButton("RESUME");
        MenuButton btnNewGame = new MenuButton("NEWGAME");

        /**
         * Menu option.
         */
        Instruction btnInstruction = new Instruction("");
        btnStart.setOnMouseClicked(event -> {
            switchToGame();
        });
        btnOptions.setOnMouseClicked(event -> {
            getChildren().add(opsion);
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5), menuStart);
            tt.setToX(menuStart.getTranslateX() - 400);
            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), opsion);
            tt1.setToX(menuStart.getTranslateX());
            tt.play();
            tt1.play();
            tt.setOnFinished(evt -> {
                getChildren().remove(menuStart);
            });
        });

        btnBack.setOnMouseClicked(event -> {
            getChildren().add(menuStart);
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menuStart);
            tt.setToX(menuStart.getTranslateX() + offset);
            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), opsion);
            tt1.setToX(opsion.getTranslateX());
            tt.play();
            tt1.play();
            tt.setOnFinished(evt -> {
                getChildren().remove(opsion);
            });
        });


        btnExit.setOnMouseClicked(event -> {
            Bomberman.closeApp();
        });
        menuStart.getChildren().addAll(btnStart, btnOptions, btnExit);
        opsion.getChildren().addAll(btnInstruction, btnBack);
        getChildren().addAll(menuStart);
    }

    public void switchToGame() {
        Bomberman.setRoot(game.getRoot());
        Bomberman.setInputHandlers(game.getInputHandlers());
        game.start();
    }
    public void ressume() {
        Bomberman.setRoot(game.getRoot());
        Bomberman.setInputHandlers(game.getInputHandlers());
        game.stop();
    }
}
