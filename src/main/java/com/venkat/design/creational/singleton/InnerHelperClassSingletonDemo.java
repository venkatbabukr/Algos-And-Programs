package com.venkat.design.creational.singleton;

import java.io.Serializable;

class Singleton implements Serializable {

    /** Generated serialVersionUID */
	private static final long serialVersionUID = -4097348424494609447L;

	private String strMember;

    private Singleton() {
        String currThreadName = Thread.currentThread().getName();
        System.out.format("[Thread %s]: Inside %s() constructor!%n", currThreadName, Singleton.class.getName());
        strMember = String.format("strVal with thread name suffix %s", currThreadName);
    }

    public String getStrMember() {
        return strMember;
    }

    /**
     * Required only if you have absolute necessity of making the {@link InnerHelperClassSingletonDemo} instance {@link java.io.Serializable}. Avoid
     * this anti-pattern totally. Singletons better not be Serliaized!
     *
     * @return The same singleton instance that we get through {@link SingletonCreateHelper#getInstance()}
     */
    protected Object readResolve() {
        return getInstance();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public static Singleton getInstance() {
        System.out.format("[Thread %s]: Inside %s#getInstance()!%n", Thread.currentThread().getName(), SingletonCreateHelper.class.getName());
        return SingletonCreateHelper.S_INSTANCE;
	}

    public static class SingletonCreateHelper {

        private static final Singleton S_INSTANCE = new Singleton();

	}
}

public class InnerHelperClassSingletonDemo {

    public static void main(String[] args) {

        Runnable r = () -> {
        	String threadName = Thread.currentThread().getName();
            System.out.format("[Thread %s]: running!%n", threadName);
            Singleton S = Singleton.getInstance();
            System.out.format("[Thread %s]: Singleton instance=%s, strMember=%s%n", threadName, S, S.getStrMember());
        };

        Thread t1 = new Thread(r, "T1");
        Thread t2 = new Thread(r, "T2");
        t1.start();
        t2.start();
    }

}
