package ar.edu.davinci.naves.models.naves;

import ar.edu.davinci.naves.models.misiones.Mision;
import ar.edu.davinci.naves.models.misiones.MisionDiplomatica;
import ar.edu.davinci.naves.models.planeta.Planeta;
import ar.edu.davinci.naves.service.exception.CantidadInvalidaException;
import ar.edu.davinci.naves.service.exception.CombustibleInsuficienteException;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class NaveEmbajadora extends ClaseNave {

    private int consules;
    private int energia;
    private int salud;
    @Id
    private Long id;

    // Constructor con parámetros
    public NaveEmbajadora(int consules) {
        if (consules <= 0) {
            throw new CantidadInvalidaException("La cantidad de cónsules debe ser mayor a 0.");
        }
        this.salud = 100;
        this.consules = consules;
        this.combustible = 100; // Inicializa el combustible
        this.escudo = 100;      // Inicializa el escudo
        this.energia = 100;     // Inicializa la energía
    }

    // Constructor sin parámetros para JPA
    public NaveEmbajadora() {
    }

    @Override
    public void atender(Mision unaMision) {
        if (unaMision instanceof MisionDiplomatica) {
            System.out.println("La Nave Embajadora está atendiendo una misión diplomática.");
            // Aquí implementas la lógica específica para una misión diplomática
        } else {
            System.out.println("La Nave Embajadora no puede atender esta misión.");
        }
    }

    @Override
    public void incrementarEscudo(int incremento) {
        escudo += incremento;
        System.out.println("El escudo de la nave embajadora ha aumentado a: " + escudo);
    }

    @Override
    public void setEnergiaEscudos(double v) {

    }

    @Override
    public int getDanio() {
        return 0; // La nave embajadora no causa daño
    }

    @Override
    public void atacar(Nave objetivo) {
        System.out.println("La Nave Embajadora no puede atacar.");
    }

    @Override
    public boolean puedeViajar(Planeta destino) {
        double combustibleRequerido = destino.getDistanciaALaTierra() * 1.1;
        return combustible >= combustibleRequerido;
    }

    @Override
    public void viajar(Planeta destino) {
        double combustibleRequerido = destino.getDistanciaALaTierra() * 1.1;
        if (puedeViajar(destino)) {
            combustible -= combustibleRequerido;
            System.out.println("La Nave Embajadora ha viajado al planeta " + destino.getNombre());
        } else {
            throw new CombustibleInsuficienteException("No hay suficiente combustible para viajar a " + destino.getNombre());
        }
    }

    @Override
    public void recibirDanio(int cantidad) {
        if (escudo > 0) {
            escudo -= cantidad;
            if (escudo < 0) {
                escudo = 0;
            }
        } else {
            salud -= cantidad;
            if (salud < 0) {
                salud = 0;
                System.out.println("La nave embajadora ha sido destruida.");
            } else {
                System.out.println("Escudo agotado. La nave embajadora recibe " + cantidad + " de daño. Salud restante: " + salud);
            }
        }
    }

    @Override
    public String toString() {
        return "NaveEmbajadora{" +
                "consules=" + consules +
                ", energia=" + energia +
                ", salud=" + salud +
                ", combustible=" + combustible +
                ", escudo=" + escudo +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
