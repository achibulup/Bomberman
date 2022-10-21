package com.uet.int2204.group2.Menu;

import com.uet.int2204.group2.Sound.Sound;
import com.uet.int2204.group2.utils.ResourceManager;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MenuButton extends StackPane {
    private Text text;
    private Font font;
    private Image menuButton;

    public MenuButton(String name) {
        text = new Text(name);
        //font = Font.loadFont(new File("/res/font/Minecrafter.ttf").toURI().toString(), 20);
        font = Font.font(30);
        text.setFont(font);
        menuButton = ResourceManager.menuButton;
        ImageView imageView = new ImageView(menuButton);
        imageView.setScaleY(1.5);
        setAlignment(Pos.CENTER);
        getChildren().addAll(imageView, text);
        setOnMouseEntered(event -> {
            imageView.setTranslateX(20);
            text.setTranslateX(20);
            text.setFill(Color.GREEN);
        });

        setOnMouseExited(event -> {
            imageView.setTranslateX(0);
            text.setTranslateX(0);
            text.setFill(Color.BLACK);

        });

        DropShadow drop = new DropShadow(100, Color.BLUE);
        drop.setInput(new Glow());

        setOnMousePressed(event -> setEffect(drop));
        setOnMouseReleased(event -> setEffect(null));
    }
}
