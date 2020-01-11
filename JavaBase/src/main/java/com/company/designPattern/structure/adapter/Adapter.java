package com.company.designPattern.structure.adapter;

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
