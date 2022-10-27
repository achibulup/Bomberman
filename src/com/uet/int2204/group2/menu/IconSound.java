package com.uet.int2204.group2.menu;

import com.uet.int2204.group2.utils.ResourceManager;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class IconSound extends StackPane {
    private Text text;
    private Font font;
    private Image image;

    public IconSound(String name) {
        text = new Text(name);
        text.setFont(font);
        image = ResourceManager.imgIconSound;
        ImageView imageView = new ImageView(image);
        imageView.setTranslateX(-80);
        setAlignment(Pos.CENTER);
        getChildren().addAll(imageView,text);
    }
}
