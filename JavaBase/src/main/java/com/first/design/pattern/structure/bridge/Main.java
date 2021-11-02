package com.first.design.pattern.structure.bridge;

public class Main {

    public static void main(String[] args) {
        MysqlImpl mysql = new MysqlImpl();
        RefinedAbstraction refinedAbstraction = new RefinedAbstraction(mysql);
        refinedAbstraction.operaAbstract();
    }
}
