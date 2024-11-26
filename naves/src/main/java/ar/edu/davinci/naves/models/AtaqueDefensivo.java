package ar.edu.davinci.naves.models;

import ar.edu.davinci.naves.models.naves.Nave;

public class AtaqueDefensivo implements ModoAtaque {
    @Override
    public void ejecutarAtaque(Nave atacante, Nave objetivo) {
        int danioModificado = (int) (atacante.getDanio() * 1.1);
        atacante.incrementarEscudo(10); // incrementa el escudo en 10 puntos
        objetivo.recibirDanio(danioModificado);
    }
}
