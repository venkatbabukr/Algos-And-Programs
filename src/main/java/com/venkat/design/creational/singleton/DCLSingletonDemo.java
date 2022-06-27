package com.venkat.design.creational.singleton;

import java.io.Serializable;

class DCLSingleton implements Serializable {

    /** Generated serialVersionUID */
	private static final long serialVersionUID = -5409802674986398648L;

	private static volatile DCLSingleton S_INSTANCE;

    private String strMember;

    private DCLSingleton() {
        String currThreadName = Thread.currentThread().getName();
        System.out.format("[Thread %s]: Inside constructor %s()!%n", currThreadName, DCLSingleton.class.getName());
        strMember = String.format("strVal with thread name suffix %s", currThreadName);
    }

    public String getStrMember() {
    	return strMember;
    }

    /**
     * Required only if you have absolute necessity of making the {@link DCLSingleton} instance {@link java.io.Serializable}. Avoid
     * this anti-pattern totally. Singletons better not be Serliaized!
     *
     * @return The same singleton instance that we get through {@link DCLSingleton#getInstance()}
     */
    protected Object readResolve() {
        return getInstance();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public static DCLSingleton getInstance() {
        if (S_INSTANCE == null) {
        	synchronized (DCLSingleton.class) {
                if (S_INSTANCE == null) {
                	S_INSTANCE = new DCLSingleton();
                }
			}
        }
        return S_INSTANCE;
    }

	public static void printInstanceState(String execMessage) {
        System.out.format("[Thread %s]: %s : Singleton instance=%s, strMember=%s%n", Thread.currentThread().getName(),
            execMessage, S_INSTANCE, (S_INSTANCE != null) ? S_INSTANCE.getStrMember() : null);
	}

}

public class DCLSingletonDemo {

    public static void main(String[] args) {
        Runnable r = () -> {
        	String threadName = Thread.currentThread().getName();
            System.out.format("[Thread %s]: running!%n", threadName);
            DCLSingleton.printInstanceState("Before getInstance()");
            DCLSingleton S = DCLSingleton.getInstance();
            DCLSingleton.printInstanceState("After getInstance()");
            // System.out.format("[Thread %s]: Singleton instance=%s, strMember=%s%n", threadName, S, S.getStrMember());
        };

        DCLSingleton.printInstanceState("Before creating threads!");
        Thread t1 = new Thread(r, "T1");
        Thread t2 = new Thread(r, "T2");
        t1.start();
        t2.start();
    }
}
