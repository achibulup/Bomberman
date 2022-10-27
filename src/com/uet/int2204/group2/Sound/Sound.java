package com.uet.int2204.group2.sound;


import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound {
    private Media media;
    private MediaPlayer mediaPlayer;
    public static boolean playCheck = true;
    public void playMusic(String path, boolean playCheck) {
        if(playCheck) {
            media = new Media(new File(path).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        }
    }
    public void stopMusic() {
        mediaPlayer.stop();
    }
    public void loopMusic() {
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }
    public void changeVolume(double volume) {
        mediaPlayer.setVolume(volume);
    }

    public static boolean isPlayCheck() {
        return playCheck;
    }

    public static void setPlayCheck(boolean playCheck) {
        Sound.playCheck = playCheck;
    }
}