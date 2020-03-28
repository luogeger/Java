package com.first.design.pattern.structure.adapter;

public class Adapter implements AudioPlayer {
    MediaPlayer mediaPlayer;

    public Adapter(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("avi")) {

        }
    }
}
