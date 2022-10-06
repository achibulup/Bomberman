package com.uet.int2204.group2.Menu;

import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class MenuButton extends StackPane {
    private Text text;
    public MenuButton(String name) {
        text = new Text(name);
        text.setFont(text.getFont().font(50));
        Rectangle bg = new Rectangle(200,50);
        bg.setOpacity(0);
        //bg.setFill(Color.YELLOW);
        bg.setEffect(new GaussianBlur(3.5));
        setAlignment(Pos.CENTER_LEFT);
        //setRotate(-1);
        getChildren().addAll(bg,text);
        setOnMouseEntered(event -> {
            bg.setTranslateX(20);
            text.setTranslateX(20);
            bg.setFill(Color.BLACK);
            text.setFill(Color.WHITE);
        });
//
        setOnMouseExited(event -> {
            bg.setTranslateX(0);
            text.setTranslateX(0);
            bg.setFill(Color.BLACK);
            text.setFill(Color.BLACK);

        });

        DropShadow drop = new DropShadow(50, Color.BLUE);
        drop.setInput(new Glow());

        setOnMousePressed(event -> setEffect(drop));
        setOnMouseReleased(event -> setEffect(null));

    }
}
