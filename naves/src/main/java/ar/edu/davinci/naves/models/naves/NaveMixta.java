package ar.edu.davinci.naves.models.naves;

import ar.edu.davinci.naves.models.misiones.Mision;
import ar.edu.davinci.naves.models.planeta.Planeta;
import ar.edu.davinci.naves.service.exception.CantidadInvalidaException;
import ar.edu.davinci.naves.service.exception.CombustibleInsuficienteException;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
    class NaveMixta extends ClaseNave {
        private int danioOfensivo;
        private int tripulantes;
        private int consules;
        private int misiles;
        private int combustible;
        private int escudo;
        private int energia;
    @Id
    private Long id;

    public NaveMixta(int danioOfensivo, int tripulantes, int consules, int misiles) {
            if (tripulantes <= 0) {
                throw new CantidadInvalidaException("La cantidad de tripulantes debe ser mayor a 0.");
            }
            if (consules <= 0) {
                throw new CantidadInvalidaException("La cantidad de cónsules debe ser mayor a 0.");
            }
            this.danioOfensivo = danioOfensivo;
            this.tripulantes = tripulantes;
            this.consules = consules;
            this.misiles = misiles;
            this.combustible = 100; // Inicializa el combustible
            this.escudo = 100;      // Inicializa el escudo
            this.energia = 100;     // Inicializa la energía
        }

    public NaveMixta() {

    }

    @Override
        public void atender(Mision unaMision) {

        }

        @Override
        public void incrementarEscudo(int i) {

        }

    @Override
    public void setEnergiaEscudos(double v) {

    }

    @Override
        public int getDanio() {
            return danioOfensivo * tripulantes;
        }

        @Override
        public void atacar(Nave objetivo) {
            int danioTotal = getDanio();
            int danioPropio = (int) (danioTotal * 0.001);
            recibirDanio(danioPropio);
            objetivo.recibirDanio(danioTotal);
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
                System.out.println("Viajando a " + destino.getNombre() + "...");
            } else {
                throw new CombustibleInsuficienteException("Combustible insuficiente para viajar al planeta " + destino.getNombre());
            }
        }

        @Override
        public String toString() {
            return "NaveMixta{" +
                    "danioOfensivo=" + danioOfensivo +
                    ", tripulantes=" + tripulantes +
                    ", consules=" + consules +
                    ", misiles=" + misiles +
                    ", combustible=" + combustible +
                    ", escudo=" + escudo +
                    ", energia=" + energia +
                    '}';
        }

        @Override
        public void recibirDanio(int cantidad) {
            if (escudo > 0) {
                escudo -= cantidad;
                if (escudo < 0) {
                    escudo = 0;
                }
            } else {

            }
        }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}


