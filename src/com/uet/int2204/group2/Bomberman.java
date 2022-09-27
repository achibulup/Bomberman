package com.uet.int2204.group2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import com.uet.int2204.group2.utils.Constants;
import com.uet.int2204.group2.utils.ResourceManager;

public class Bomberman extends Application {
    private static Scene scene;

    public static void main(String[] args) {
      ResourceManager.load();
      launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), Constants.TILE_SIZE * 12, Constants.TILE_SIZE * 12);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    static void setRoot(Parent node) {
      scene.setRoot(node);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Bomberman.class.getResource("/fxml/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }
}