package ar.edu.davinci.naves.models;


import ar.edu.davinci.naves.models.naves.Nave;
import ar.edu.davinci.naves.models.naves.NaveEmbajadora;

public class AtaquePoderoso implements ModoAtaque {
        @Override
        public void ejecutarAtaque(Nave atacante, Nave objetivo) {
            if (atacante instanceof NaveEmbajadora) {
                objetivo.recibirDanio(1);
            } else {
                objetivo.recibirDanio(atacante.getDanio() * 2);
            }
        }
    }

