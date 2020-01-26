package com.first.designPattern.structure.proxy;

public class ProxyImage implements Image {
    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        // 如果真实对象(realImage)不存在，就初始化真实对象
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        // 如果存在就直接调用
        realImage.display();
    }


}
