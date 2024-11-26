package ar.edu.davinci.naves.models.misiones;

import ar.edu.davinci.naves.models.naves.ClaseNave;
import ar.edu.davinci.naves.models.naves.Nave;
import ar.edu.davinci.naves.models.naves.NaveDeAtaque;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class MisionBelica extends MisionBase {
    @ManyToOne
    private ClaseNave objetivo;

    public MisionBelica(ClaseNave objetivo) {
        this.objetivo = objetivo;
    }

    public MisionBelica() {}

    @Override
    public void ejecutar(ClaseNave nave) {
        if (nave instanceof NaveDeAtaque) {
            ((NaveDeAtaque) nave).atacar(objetivo);
            System.out.println("Nave de ataque completó misión bélica.");
        }
    }

    public ClaseNave getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(ClaseNave objetivo) {
        this.objetivo = objetivo;
    }

    @Override
    public void ejecutar(Nave nave) {

    }
}