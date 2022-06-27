package com.venkat.design.creational.singleton;

enum EnumSingleton {
    S_INSTANCE;

    private String strMember;

    public String getStrMember() {
        return strMember;
    }

    private EnumSingleton() {
        String currThreadName = Thread.currentThread().getName();
        System.out.format("[Thread %s]: Inside constructor %s()!%n", currThreadName, EnumSingleton.class.getName());
        strMember = String.format("strVal with thread name suffix %s", currThreadName);
    }

}

public class EnumSingletonDemo {

    public static void main(String[] args) {

        Runnable r = () -> {
        	String threadName = Thread.currentThread().getName();
            System.out.format("[Thread %s]: running!%n", threadName);
            EnumSingleton S = EnumSingleton.S_INSTANCE;
            System.out.format("[Thread %s]: Singleton instance=%s, strMember=%s%n", threadName, S, S.getStrMember());
        };

        Thread t1 = new Thread(r, "T1");
        Thread t2 = new Thread(r, "T2");
        t1.start();
        t2.start();
    }

}
