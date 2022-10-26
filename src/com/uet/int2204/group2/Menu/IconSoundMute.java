package com.uet.int2204.group2.Menu;

import com.uet.int2204.group2.utils.ResourceManager;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class IconSoundMute extends StackPane {
    private Text text;
    private Font font;
    private Image image;

    public IconSoundMute(String name) {
        text = new Text(name);
        font = Font.loadFont("file:target/classes/font/ARCADE.TTF", 28);
        text.setFont(font);
        image = ResourceManager.imgIconSoundMute;
        ImageView imageView = new ImageView(image);
        imageView.setTranslateX(-80);
        setAlignment(Pos.CENTER);
        getChildren().addAll(imageView,text);
    }
}
