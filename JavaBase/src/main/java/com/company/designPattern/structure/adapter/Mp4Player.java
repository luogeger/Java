package com.company.designPattern.structure.adapter;

public class Mp4Player implements MediaPlayer {
    @Override
    public void playAVI(String fileName) {

    }

    @Override
    public void playMP4(String fileName) {
        System.out.println("mp4格式："+ fileName);
    }
}
