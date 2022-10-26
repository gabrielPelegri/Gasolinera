package com.gasolinera;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;
import java.util.logging.Logger;

@SpringBootApplication
public class GasolineraApplication {

    private Logger logger = Logger.getLogger(GasolineraApplication.class.getName());
    public static void main(String[] args) {
        SpringApplication.run(GasolineraApplication.class, args);
        Gasolinera gasolinera = new Gasolinera(50);
        Thread servicio = new Thread(gasolinera);
        servicio.start();
    }
}
