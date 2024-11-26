package ar.edu.davinci.naves.models.misiones;

import ar.edu.davinci.naves.models.naves.Nave;
import ar.edu.davinci.naves.models.planeta.Planeta;
import ar.edu.davinci.naves.models.naves.ClaseNave;
import ar.edu.davinci.naves.models.naves.NaveDeAtaque;
import ar.edu.davinci.naves.models.naves.NaveEmbajadora;
import jakarta.persistence.*;

@Entity
public class MisionMixta extends MisionBase {
    @ManyToOne
    private ClaseNave objetivo;

    @ManyToOne
    private Planeta destino;

    public MisionMixta(ClaseNave objetivo, Planeta destino) {
        this.objetivo = objetivo;
        this.destino = destino;
    }

    public MisionMixta() {}

    @Override
    public void ejecutar(ClaseNave nave) {
        if (nave instanceof NaveDeAtaque) {
            nave.atacar(objetivo);
        }
        if (nave instanceof NaveEmbajadora) {
            nave.viajar(destino);
        }
        System.out.println("Misión mixta completada.");
    }

    public ClaseNave getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(ClaseNave objetivo) {
        this.objetivo = objetivo;
    }

    public Planeta getDestino() {
        return destino;
    }

    public void setDestino(Planeta destino) {
        this.destino = destino;
    }

    @Override
    public void ejecutar(Nave nave) {

    }
}