package ar.edu.davinci.naves.models.naves;

import ar.edu.davinci.naves.models.misiones.Mision;
import ar.edu.davinci.naves.models.planeta.Planeta;
import ar.edu.davinci.naves.service.exception.ExcesoDeCapacidadException;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.ArrayList;
@Entity
public class NaveNodriza extends ClaseNave implements INaveNodriza {
    private int capacidadMaxima;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ClaseNave> flota;

    private int danio;
    private int combustible;
    private int escudo;
    private int energia;

    @Id
    private Long id;

    public NaveNodriza(int capacidadMaxima, int danio, int combustible) {
        this.capacidadMaxima = capacidadMaxima;
        this.danio = danio > 0 ? danio : 1;
        this.combustible = combustible;
        this.escudo = 100;
        this.energia = 100;
        this.flota = new ArrayList<>();
    }

    public NaveNodriza() {

    }

    @Override
    public void agregarNave(Nave nave) {
        if (flota.size() < capacidadMaxima) {
            flota.add((ClaseNave) nave);
        } else {
            throw new ExcesoDeCapacidadException("Capacidad máxima alcanzada.");
        }
    }

    @Override
    public void eliminarNave(Nave nave) {
        if (flota.contains(nave)) {
            flota.remove(nave);
        } else {
            System.out.println("La nave no está en la flota.");
        }
    }

    @Override
    public List<ClaseNave> getFlota() {
        return flota;
    }

    @Override
    public void ejecutarMision(Mision unaMision) {
        for (Nave nave : flota) {
            nave.atender(unaMision);
        }
    }

    // Métodos heredados de ClaseNave que no son específicos de NaveNodriza

    @Override
    public void atender(Mision unaMision) {
        System.out.println("La Nave Nodriza está atendiendo una misión.");
        unaMision.ejecutar(this);

        for (Nave nave : flota) {
            nave.atender(unaMision);
        }
    }

    @Override
    public void incrementarEscudo(int i) {

    }

    @Override
    public void setEnergiaEscudos(double v) {
    }

    @Override
    public int getDanio() {
        int danioTotal = danio;
        for (Nave nave : flota) {
            danioTotal += nave.getDanio();
        }
        return danioTotal;
    }

    @Override
    public void atacar(Nave objetivo) {
        flota.forEach(nave -> nave.atacar(objetivo));
    }


    @Override
    public boolean puedeViajar(Planeta destino) {
        double combustibleRequerido = destino.getDistanciaALaTierra() * 1.1;
        return combustible >= combustibleRequerido;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NaveNodriza{")
                .append("capacidadMaxima=").append(capacidadMaxima)
                .append(", danio=").append(danio)
                .append(", escudo=").append(escudo)
                .append(", combustible=").append(combustible)
                .append(", flota=[");

        for (Nave nave : flota) {
            sb.append("\n\t").append(nave.toString());
        }
        sb.append("\n]}");
        return sb.toString();
    }

    @Override
    public void viajar(Planeta destino) {
        double combustibleRequerido = destino.getDistanciaALaTierra() * 1.1;
        if (puedeViajar(destino)) {
            combustible -= combustibleRequerido;
            System.out.println("Viajando a " + destino.getNombre() + "...");
        } else {
            throw new IllegalArgumentException("No hay suficiente combustible para viajar.");
        }
    }

    @Override
    public void recibirDanio(int cantidad) {
        if (escudo > 0) {
            escudo -= cantidad;
            if (escudo < 0) {
                escudo = 0;
            }
            System.out.println("Escudo reducido a " + escudo);
        } else {
            System.out.println("La nave ha recibido " + cantidad + " puntos de daño.");
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
