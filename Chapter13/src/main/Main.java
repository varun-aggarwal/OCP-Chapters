package main;

import events.Olympics;

public class Main {

    public static void main(String[] args) {
        olympicsDemo();
        demoSimpleDeadLock();
    }

    private static void olympicsDemo() {
        Olympics olympics2016 = new Olympics();
        System.out.println("=========Welcome to 2016 Olympics==================");
        olympics2016.begin();
        System.out.println("=========2016 Olympics Completed==================");
    }

    private static void demoSimpleDeadLock() {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("======Warning - Approaching Deadlock ==============");
        SimpleDeadLock simpleDeadLock = new SimpleDeadLock();
        simpleDeadLock.demoSimpleDeadLock();
    }

}
