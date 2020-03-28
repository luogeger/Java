package com.first.design.pattern.structure.adapter;

public class AviPlayer  implements MediaPlayer {
    @Override
    public void playAVI(String fileName) {
        System.out.println("avi格式："+ fileName);
    }

    @Override
    public void playMP4(String fileName) {

    }
}
