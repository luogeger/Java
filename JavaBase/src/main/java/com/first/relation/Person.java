package com.first.relation;

public class Person {

    private Head head = new Head();

    private static Body body;

    private static Hand hand = new Hand();

    static {
        System.out.println("Person static code block start!");
        body = new Body();
        System.out.println("Person static code block end!");
    }

    public Person() {
        System.out.println("Person constructor method!");
    }
}
