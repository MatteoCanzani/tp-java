package ar.edu.davinci.naves.models.naves;

import ar.edu.davinci.naves.models.AtaqueDefensivo;
import ar.edu.davinci.naves.models.AtaquePoderoso;
import ar.edu.davinci.naves.models.ModoAtaque;
import ar.edu.davinci.naves.models.misiones.Mision;
import ar.edu.davinci.naves.models.planeta.Planeta;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clase_nave")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ClaseNave implements Nave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected int danio;
    protected int combustible;
    protected int escudo;
    protected int vida;

    @Transient
    protected List<ModoAtaque> modosAtaque;

    @Transient
    protected ModoAtaque modoActual;

    protected int contadorAtaques;


    public ClaseNave(int danio, int combustible) {
        this.danio = danio > 0 ? danio : 1;
        this.combustible = combustible;
        this.escudo = 100;
        this.vida = 1;
        this.modosAtaque = new ArrayList<>();

        modosAtaque.add(new AtaquePoderoso());
        modosAtaque.add(new AtaqueDefensivo());
        this.modoActual = modosAtaque.get(0);
    }

    public ClaseNave() {
        this(1, 100);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void alternarModo() {
        int indiceActual = modosAtaque.indexOf(modoActual);
        modoActual = modosAtaque.get((indiceActual + 1) % modosAtaque.size());
    }

    private void reiniciarModoAtaque() {
        this.modoActual = modosAtaque.get(0);
        this.contadorAtaques = 0;
    }

    @Override
    public void atacar(Nave objetivo) {
        modoActual.ejecutarAtaque(this, objetivo);
        contadorAtaques++;

        if (contadorAtaques >= 10) {
            reiniciarModoAtaque();
        } else {
            alternarModo();
        }
        System.out.println("Ataque realizado con modo: " + modoActual.getClass().getSimpleName());
    }

    @Override
    public abstract void atender(Mision unaMision);

    @Override
    public int getDanio() {
        return danio;
    }

    @Override
    public void recibirDanio(int cantidad) {
        if (escudo > 0) {
            escudo -= cantidad;
            if (escudo < 0) {
                escudo = 0;
            }
        } else {
            vida -= cantidad;
            if (vida < 0) {
                vida = 0;
            }
        }
        System.out.println("Escudo reducido a " + escudo + " y vida restante: " + vida);
    }

    @Override
    public boolean puedeViajar(Planeta destino) {
        double combustibleRequerido = destino.getDistanciaALaTierra() * 1.1;
        return combustible >= combustibleRequerido;
    }

    @Override
    public void viajar(Planeta destino) {
        if (!puedeViajar(destino)) {
            throw new IllegalArgumentException("No hay suficiente combustible para viajar.");
        }
        double combustibleRequerido = destino.getDistanciaALaTierra() * 1.1;
        combustible -= combustibleRequerido;
        System.out.println("La nave ha viajado al planeta " + destino.getNombre());
    }

    public void cambiarModoAtaque(ModoAtaque nuevoModo) {
        this.modoActual = nuevoModo;
    }

    public int getCombustible() {
        return combustible;
    }

    public int getEscudo() {
        return escudo;
    }

    public int getVida() {
        return vida;
    }

    public void setEscudo(int escudo) {
        this.escudo = escudo;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setDanio(int danio) {
        this.danio = danio;
    }

    public void setCombustible(int combustible) {
        this.combustible = combustible;
    }
}
