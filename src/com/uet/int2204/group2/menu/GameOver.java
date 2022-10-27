package com.uet.int2204.group2.menu;

import com.uet.int2204.group2.utils.ResourceManager;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameOver extends StackPane {
    private Text text;
    private Font font;
    private Image image;
    public Pane pane;
    public GameOver(String name) {
        text = new Text(name);
        text.setFont(font);
        image = ResourceManager.gameOver;
        ImageView imageView = new ImageView(image);
        imageView.setScaleY(1.6);
        imageView.setScaleX(1.2);
        getChildren().addAll(imageView,text);
    }
}
