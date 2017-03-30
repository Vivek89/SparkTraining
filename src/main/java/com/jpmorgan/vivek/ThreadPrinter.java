package com.jpmorgan.vivek;

public class ThreadPrinter {

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			new Thread(new Thread1("mutex")).start();
		}
		new Thread(new Thread2("mutex")).start();
	}
}

class Thread1 implements Runnable {

	String mutex;

	public Thread1(String mutex) {
		this.mutex = mutex;
	}

	@Override
	public void run() {
		for (int i = 1; i < 20; i++) {
			synchronized (mutex) {
				System.out.print(i);
				try {
					mutex.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

class Thread2 implements Runnable {

	String mutex;

	public Thread2(String mutex) {
		this.mutex = mutex;
	}

	@Override
	public void run() {

		for (int i = 1; i < 20; i++) {
			synchronized (mutex) {
				try {
					System.out.print("\n");
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				mutex.notifyAll();
			}
		}
	}
}
