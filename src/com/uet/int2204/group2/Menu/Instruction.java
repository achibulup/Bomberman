package com.uet.int2204.group2.Menu;

import com.uet.int2204.group2.utils.ResourceManager;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Instruction extends StackPane {
    private Text text;
    private Text text1;
    private Font font;
    private Image image;
    public Pane pane;
    public Instruction(String name) {
        text = new Text(name);
        font = Font.loadFont("file:target/classes/font/ARCADE.TTF", 28);
        text.setFont(font);
        image = ResourceManager.imgOption;
        ImageView imageView = new ImageView(image);
        imageView.setTranslateX(-80);
        text.setTranslateX(-80);
        setAlignment(Pos.CENTER);
        getChildren().addAll(imageView,text);
    }
}
