package ar.edu.davinci.naves.models.naves;

import ar.edu.davinci.naves.models.misiones.Mision;
import ar.edu.davinci.naves.models.planeta.Planeta;
import ar.edu.davinci.naves.service.exception.CantidadInvalidaException;
import ar.edu.davinci.naves.service.exception.CombustibleInsuficienteException;
import ar.edu.davinci.naves.service.exception.EscudoInsuficienteException;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class NaveDeAtaque extends ClaseNave {

    private int danioOfensivo;
    private int tripulantes;
    private int misiles;
    private int combustible;
    private int escudo;
    private int energia;
    @Id
    private Long id;

    // Constructor con parámetros
    public NaveDeAtaque(int danioOfensivo, int tripulantes, int misiles) {
        super(); // Llama al constructor de ClaseNave
        this.algo(tripulantes);
        this.danioOfensivo = danioOfensivo;
        this.tripulantes = tripulantes;
        this.misiles = misiles;
        this.combustible = 100;
        this.escudo = 100;
        this.energia = 100;
    }

    // Constructor sin parámetros para JPA
    public NaveDeAtaque() {
    }

    private void algo(int tripulantes) {
        if (tripulantes <= 0) {
            throw new CantidadInvalidaException("La cantidad de tripulantes debe ser mayor a 0.");
        }
    }

    @Override
    public int getDanio() {
        return danioOfensivo * tripulantes;
    }

    @Override
    public void atacar(Nave objetivo) throws EscudoInsuficienteException {
        if (escudo <= 0) {
            throw new EscudoInsuficienteException("El escudo es insuficiente para atacar.");
        }
        int danioTotal = getDanio();
        objetivo.recibirDanio(danioTotal);
    }

    @Override
    public boolean puedeViajar(Planeta destino) throws CombustibleInsuficienteException {
        double combustibleRequerido = destino.getDistanciaALaTierra() * 1.1;
        if (combustible < combustibleRequerido) {
            throw new CombustibleInsuficienteException("Combustible insuficiente para viajar.");
        }
        return true;
    }

    @Override
    public String toString() {
        return "NaveDeAtaque{" +
                "danioOfensivo=" + danioOfensivo +
                ", tripulantes=" + tripulantes +
                ", misiles=" + misiles +
                ", combustible=" + combustible +
                ", escudo=" + escudo +
                ", energia=" + energia +
                '}';
    }

    @Override
    public void viajar(Planeta destino) {
        double combustibleRequerido = destino.getDistanciaALaTierra() * 1.1;
        if (puedeViajar(destino)) {
            combustible -= combustibleRequerido;
            System.out.println("La nave ha viajado al planeta " + destino.getNombre());
        } else {
            throw new CombustibleInsuficienteException("No hay suficiente combustible para viajar a " + destino.getNombre());
        }
    }

    public void recibirDanio(int cantidad) {
        if (escudo > 0) {
            escudo -= cantidad;
            if (escudo < 0) {
                escudo = 0;
            }
        }
    }

    @Override
    public void atender(Mision unaMision) {
        unaMision.ejecutar(this);
    }

    @Override
    public void incrementarEscudo(int i) {
        escudo += i;
        System.out.println("El escudo ha aumentado a: " + escudo);
    }

    @Override
    public void setEnergiaEscudos(double v) {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
