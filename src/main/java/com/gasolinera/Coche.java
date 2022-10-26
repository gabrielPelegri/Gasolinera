package com.gasolinera;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Coche implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(Coche.class);
    public static int id = 0;

    {
        id++;
    }

    private int nombre;
    private Gasolinera gasolinera;
    private Surtidor surtidor;
    private boolean haRepostado = false;

    public Coche(Gasolinera gasolinera) {
        this.nombre = Coche.id;
        this.gasolinera = gasolinera;


    }

    public void run() {
        while (true) {
            try {
                if (!haRepostado) {
                    repostar();
                    pagar();
                    salir();
                }

            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    private void repostar() throws InterruptedException {
        logger.info("El coche " + nombre + " está buscando un surtidor libre");
        surtidor = gasolinera.buscarSurtidorLibre();
        logger.info("El coche " + nombre + " ha encontrado un surtidor libre");
        surtidor.take();
        logger.info("El coche " + nombre + " está repostando durante {} minutos", gasolinera.getTime() / 1000);
        Thread.sleep(gasolinera.getTime());
        surtidor.drop();
        logger.info("El coche " + nombre + " ha terminado de repostar");
    }


    private void pagar() throws InterruptedException {
        long time;
        logger.info("El coche {} está pagando (3minutos)", nombre);
        spendTime(3);
        logger.info("El coche {} ha pagado", nombre);
        this.haRepostado = true;


    }

    private void salir() throws InterruptedException {

    }

    private void spendTime(long time) throws InterruptedException {
        Thread.sleep(time * 1000);
    }

    public int getNombre() {
        return nombre;
    }

}