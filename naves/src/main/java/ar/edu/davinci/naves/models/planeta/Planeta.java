package ar.edu.davinci.naves.models.planeta;

import jakarta.persistence.*;

@Entity
@Table(name = "planeta")
public class Planeta {

    @Id
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(name = "distancia_tierra", nullable = false)
    private double distanciaALaTierra;

    // Constructor con parámetros
    public Planeta(String nombre, double distanciaALaTierra) {
        this.nombre = nombre;
        this.distanciaALaTierra = distanciaALaTierra;
    }


    public Planeta() {
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getDistanciaALaTierra() {
        return distanciaALaTierra;
    }

    public void setDistanciaALaTierra(double distanciaALaTierra) {
        this.distanciaALaTierra = distanciaALaTierra;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
