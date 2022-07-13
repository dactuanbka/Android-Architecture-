package com.example.baseproject.thread;

public class FinishedQuery {
    public void add() throws InterruptedException {
        synchronized (this) {
            wait();
        }
    }

    public void confirm() {
        synchronized (this) {
            notify();
        }
    }
}
