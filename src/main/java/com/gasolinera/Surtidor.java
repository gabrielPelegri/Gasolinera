package com.gasolinera;

import sun.rmi.runtime.Log;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Surtidor {

    private ReentrantLock lock;

    public Surtidor() {
        this.lock = new ReentrantLock();
    }

    public void take() {
        lock.lock();
    }

    public void drop() {
        if (!isHeld())
            return;
        lock.unlock();
    }

    public boolean isHeld() {
        return lock.isHeldByCurrentThread();
    }

}
