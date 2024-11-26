package ar.edu.davinci.naves.models;

import ar.edu.davinci.naves.models.misiones.Mision;
import ar.edu.davinci.naves.models.misiones.MisionBelica;
import ar.edu.davinci.naves.models.misiones.MisionDiplomatica;
import ar.edu.davinci.naves.models.naves.*;
import ar.edu.davinci.naves.models.planeta.Planeta;

public class Main {
    public static void main(String[] args) {
/*
        NaveNodriza flota1Nodriza = new NaveNodriza(5, 15, 1000);
        NaveDeAtaque flota1NaveAtaque = new NaveDeAtaque(10, 5, 50);
        NaveEmbajadora flota1NaveEmbajadora = new NaveEmbajadora(3);
        flota1Nodriza.agregarNave(flota1NaveAtaque);
        flota1Nodriza.agregarNave(flota1NaveEmbajadora);


        NaveNodriza flota2Nodriza = new NaveNodriza(5, 12, 1200);
        NaveDeAtaque flota2NaveAtaque = new NaveDeAtaque(8, 4, 40);
        NaveEmbajadora flota2NaveEmbajadora = new NaveEmbajadora(3);
        flota2Nodriza.agregarNave(flota2NaveAtaque);
        flota2Nodriza.agregarNave(flota2NaveEmbajadora);


        Nave enemigoFlota2 = new NaveDeAtaque(6, 3, 20);
        Planeta destinoFlota1 = new Planeta("Planeta Rojo", 150);
        Planeta destinoFlota2 = new Planeta("Planeta Azul", 200);


        System.out.println("Ejecutando misiones de Flota 1...");
        Mision misionDiplomaticaFlota1 = new MisionDiplomatica(destinoFlota1);
        Mision misionBelicaFlota1 = new MisionBelica((ClaseNave) enemigoFlota2);
        flota1Nodriza.ejecutarMision(misionDiplomaticaFlota1);
        flota1Nodriza.ejecutarMision(misionBelicaFlota1);


        System.out.println("Ejecutando misiones de Flota 2...");
        Mision misionDiplomaticaFlota2 = new MisionDiplomatica(destinoFlota2);
        Mision misionBelicaFlota2 = new MisionBelica(flota1NaveAtaque);  // Flota 2 ve a una nave de Flota 1 como su enemigo
        flota2Nodriza.ejecutarMision(misionDiplomaticaFlota2);
        flota2Nodriza.ejecutarMision(misionBelicaFlota2);


        System.out.println("\nComienza la batalla entre Flota 1 y Flota 2...");
        for (int i = 0; i < 12; i++) {
            System.out.println("\nTurno de ataque #" + (i + 1));


            System.out.println("Flota 1 ataca a Flota 2");
            flota1Nodriza.atacar(flota2Nodriza);


            System.out.println("Flota 2 ataca a Flota 1");
            flota2Nodriza.atacar(flota1Nodriza);


            if ((i + 1) % 10 == 0) {
                System.out.println("\nSe ha alcanzado el límite de ataques, reiniciando modos de ataque...");
            }
        }


        System.out.println("\nEstado final de Flota 1:");
        System.out.println(flota1Nodriza);

        System.out.println("\nEstado final de Flota 2:");
        System.out.println(flota2Nodriza);
    }

 */
}
}
