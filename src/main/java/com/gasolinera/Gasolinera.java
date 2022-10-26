package com.gasolinera;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Gasolinera implements Runnable {
    private List<Surtidor> surtidores;
    private List<Coche> coches;
    private Iterator<Long> times;

    public Gasolinera(int numCoches) {
        if (numCoches < 2) {
            throw new IllegalArgumentException("Introduce más de un coche");
        }

        this.surtidores = new ArrayList<>(4);
        this.coches = new LinkedList<>();
        this.times = new Random().longs(5000, 10000).iterator();


        for (int i = 0; i < 5; ++i) {
            Surtidor f = new Surtidor();
            surtidores.add(f);
        }
        for (int i = 0; i < numCoches; ++i) {

            Coche p = new Coche(this);
            coches.add(p);
        }
    }

    public Surtidor buscarSurtidorLibre() {
        for (Surtidor surtidor : surtidores) {
            if (!surtidor.isHeld()) {
                return surtidor;
            }
        }
        return null;
    }

    public synchronized long getTime() {
        return times.next();
    }

    public void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(coches.size());
        for (Coche c : coches) {
            executorService.submit(c);
        }
    }
}
