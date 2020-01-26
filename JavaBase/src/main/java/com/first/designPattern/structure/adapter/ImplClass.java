package com.first.designPattern.structure.adapter;

public class ImplClass implements AudioPlayer, MediaPlayer {
    @Override
    public void play(String audioType, String fileName) {
        System.out.println("mp3格式："+ audioType +" :: "+ fileName);
    }

    @Override
    public void playAVI(String fileName) {

    }

    @Override
    public void playMP4(String fileName) {

    }

    public void player(String type, String fileName) {

    }
}
