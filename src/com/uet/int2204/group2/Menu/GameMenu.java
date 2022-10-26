package com.uet.int2204.group2.Menu;

import com.uet.int2204.group2.Bomberman;
import com.uet.int2204.group2.component.GameState;
import com.uet.int2204.group2.utils.ResourceManager;
import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;


public class GameMenu extends Parent {
    GameState game = new GameState();

    public GameMenu() {
        VBox menuStart = new VBox(30);
        VBox instruction = new VBox(30);
        VBox pause = new VBox(30);
        menuStart.setTranslateX(170);
        menuStart.setTranslateY(220);
        instruction.setTranslateX(0);
        instruction.setTranslateY(200);
        pause.setTranslateX(170);
        pause.setTranslateY(220);
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
        MenuButton btnQuit = new MenuButton("Quit");
        //MenuButton btnBackStart = new MenuButton("BACK");

        /**
         * Menu option.
         */
        Instruction btnInstruction = new Instruction(" Use the direction of key" + "\n"
                + " to move the kernel, the" + "\n"
                + " key space to set boom");

        btnStart.setOnMouseClicked(event -> {
            Bomberman.start.stopMusic();
            Bomberman.start.playMusic(ResourceManager.sound[1]);
            Bomberman.start.changeVolume(0.5);
            Bomberman.start.loopMusic();
            switchToGame();
        });

        btnNewGame.setOnMouseClicked(event -> {
            Bomberman.start.stopMusic();
            Bomberman.start.playMusic(ResourceManager.sound[1]);
            Bomberman.start.changeVolume(0.5);
            Bomberman.start.loopMusic();
            getChildren().remove(Bomberman.root);
            getChildren().remove(pause);
            game.reload();
            switchToGame();
        });

        btnOptions.setOnMouseClicked(event -> {
            getChildren().add(instruction);
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5), menuStart);
            tt.setToX(menuStart.getTranslateX() - 400);
            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), instruction);
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
            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), instruction);
            tt1.setToX(instruction.getTranslateX());
            tt.play();
            tt1.play();
            tt.setOnFinished(evt -> {
                getChildren().remove(instruction);
            });
        });

        Bomberman.scene.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                game.stop();
                getChildren().remove(menuStart);
                getChildren().add(pause);
                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), instruction);
                tt1.setToX(menuStart.getTranslateX());
                Bomberman.setRoot(Bomberman.root);
            }
        });

        btnExit.setOnMouseClicked(event -> {
            Bomberman.closeApp();
        });
        btnQuit.setOnMouseClicked(event -> {
            Bomberman.closeApp();
        });
        btnResume.setOnMouseClicked(mouseEvent -> {
            getChildren().remove(pause);
            switchToGame();
        });

        menuStart.getChildren().addAll(btnStart, btnOptions, btnExit);
        instruction.getChildren().addAll(btnInstruction, btnBack);
        pause.getChildren().addAll(btnResume, btnNewGame,btnQuit);
        getChildren().addAll(menuStart);
    }

    public void switchToGame() {
        game.start();
        Bomberman.setRoot(game.getRoot());
        Bomberman.setInputHandlers(game.getInputHandlers());
    }

    public void ressume() {
        game.stop();
    }
}
