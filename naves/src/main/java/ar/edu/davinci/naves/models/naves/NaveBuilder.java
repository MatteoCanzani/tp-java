package ar.edu.davinci.naves.models.naves;

public class NaveBuilder {
    // Instancia única de NaveBuilder (Singleton)
    // Implementacion de patron Builder
    private static NaveBuilder instanciaUnica;


    private int danioOfensivo;
    private int cantidadTripulantes;
    private int cantidadMisiles;
    private int cantidadConsules;


    private NaveBuilder() {}




    public static NaveBuilder obtenerInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new NaveBuilder();
        }
        return instanciaUnica;
    }


    public NaveBuilder configurarNaveDeAtaque(int danioOfensivo, int cantidadTripulantes, int cantidadMisiles) {
        this.danioOfensivo = danioOfensivo;
        this.cantidadTripulantes = Math.max(cantidadTripulantes, 1); // No permite 0 tripulantes
        this.cantidadMisiles = cantidadMisiles;
        this.cantidadConsules = 0; // No aplica para naves de ataque
        return this;
    }

    public NaveBuilder configurarNaveEmbajadora(int cantidadConsules) {
        this.danioOfensivo = 0; // No aplica para naves embajadoras
        this.cantidadTripulantes = 0;
        this.cantidadMisiles = 0;
        this.cantidadConsules = cantidadConsules;
        return this;
    }

    public NaveBuilder configurarNaveMixta(int danioOfensivo, int cantidadTripulantes, int cantidadConsules, int cantidadMisiles) {
        this.danioOfensivo = danioOfensivo;
        this.cantidadTripulantes = Math.max(cantidadTripulantes, 1); // No permite 0 tripulantes
        this.cantidadMisiles = cantidadMisiles;
        this.cantidadConsules = cantidadConsules;
        return this;
    }


    public Nave construirNaveDeAtaque() {
        Nave nave = new NaveDeAtaque(danioOfensivo, cantidadTripulantes, cantidadMisiles);
        reset();
        return nave;
    }

    public Nave construirNaveEmbajadora() {
        Nave nave = new NaveEmbajadora(cantidadConsules);
        reset();
        return nave;
    }

    public Nave construirNaveMixta() {
        Nave nave = new NaveMixta(danioOfensivo, cantidadTripulantes, cantidadConsules, cantidadMisiles);
        reset();
        return nave;
    }


    private void reset() {
        danioOfensivo = 0;
        cantidadTripulantes = 0;
        cantidadMisiles = 0;
        cantidadConsules = 0;
    }
}
