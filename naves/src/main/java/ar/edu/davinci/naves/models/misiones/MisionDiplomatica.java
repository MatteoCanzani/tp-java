package ar.edu.davinci.naves.models.misiones;

import ar.edu.davinci.naves.models.naves.ClaseNave;
import ar.edu.davinci.naves.models.planeta.Planeta;
import ar.edu.davinci.naves.models.naves.Nave;
import ar.edu.davinci.naves.models.naves.NaveEmbajadora;
import jakarta.persistence.*;

@Entity
public class MisionDiplomatica extends MisionBase {
    @ManyToOne
    private Planeta destino;

    private String tipoMision;

    public MisionDiplomatica(Planeta destino, String tipoMision) {
        this.destino = destino;
        this.tipoMision = tipoMision;
    }

    public MisionDiplomatica() {}

    @Override
    public void ejecutar(ClaseNave nave) {
        if (nave instanceof NaveEmbajadora) {
            System.out.println("Ejecutando misión diplomática en nave embajadora.");
            ((NaveEmbajadora) nave).viajar(destino);
        }
    }

    public Planeta getDestino() {
        return destino;
    }

    public void setDestino(Planeta destino) {
        this.destino = destino;
    }

    public String getTipoMision() {
        return tipoMision;
    }

    public void setTipoMision(String tipoMision) {
        this.tipoMision = tipoMision;
    }

    @Override
    public void ejecutar(Nave nave) {

    }
}