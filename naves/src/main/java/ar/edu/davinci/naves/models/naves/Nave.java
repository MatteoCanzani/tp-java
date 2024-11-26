package ar.edu.davinci.naves.models.naves;

import ar.edu.davinci.naves.models.misiones.Mision;
import ar.edu.davinci.naves.models.planeta.Planeta;

public interface Nave {
    int getDanio();
    void atacar(Nave objetivo);
    boolean puedeViajar(Planeta destino);
    void viajar(Planeta destino);

    void recibirDanio(int cantidad);

    void atender(Mision unaMision);

    void incrementarEscudo(int i);

    void setEnergiaEscudos(double v);
}
