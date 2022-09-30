package com.uet.int2204.group2;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collections;

import com.uet.int2204.group2.utils.Constants;
import com.uet.int2204.group2.utils.ResourceManager;

public class Bomberman extends Application {
    private static Scene scene;
    private static Iterable<EventHandler<KeyEvent>> inputHandlers = Collections.emptyList();

    public static void main(String[] args) {
      ResourceManager.load();
      launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("menu"), Constants.TILE_SIZE * 12, Constants.TILE_SIZE * 12);
        scene.setOnKeyPressed((keyEvent) -> {
          for (var handler : inputHandlers) {
            handler.handle(keyEvent);
          }
        });
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    static void setRoot(Parent node) {
      scene.setRoot(node);
    }

    static void setInputHandlers(Iterable<EventHandler<KeyEvent>> handlers) {
      inputHandlers = handlers;
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Bomberman.class.getResource("/fxml/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }
}