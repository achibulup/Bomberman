package com.uet.int2204.group2.Sound;


import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound {
    private Media media;
    private MediaPlayer mediaPlayer;
    public void playMusic(String path) {
        media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        //mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }
    public void stopMusic() {
        mediaPlayer.stop();
    }
    public void loopMusic() {
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }
    public void changeVolume(double volume) {
        if (volume < 0 || volume > 1) {
            throw new IllegalArgumentException();
        }
        mediaPlayer.setVolume(volume);
    }
}