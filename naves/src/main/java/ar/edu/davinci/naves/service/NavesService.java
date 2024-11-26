package ar.edu.davinci.naves.service;

import ar.edu.davinci.naves.models.naves.*;
import ar.edu.davinci.naves.models.misiones.MisionBase;
import ar.edu.davinci.naves.models.planeta.Planeta;
import ar.edu.davinci.naves.repository.NavesRepository;
import ar.edu.davinci.naves.repository.MisionRepository;
import ar.edu.davinci.naves.repository.PlanetaRepository;
import ar.edu.davinci.naves.service.interfaz.INavesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NavesService implements INavesService {

    private final NavesRepository navesRepository;
    private final MisionRepository misionRepository;
    private final PlanetaRepository planetaRepository;

    @Autowired
    public NavesService(NavesRepository navesRepository,
                        MisionRepository misionRepository,
                        PlanetaRepository planetaRepository) {
        this.navesRepository = navesRepository;
        this.misionRepository = misionRepository;
        this.planetaRepository = planetaRepository;
    }

    @Override
    public Nave agregarNaveAFlota(Nave nave) {
        return navesRepository.save(nave);
    }

    @Override
    public Nave crearNaveConConfiguracion(String tipo, String configuracion) {
        // Obtener instancia única del Builder
        NaveBuilder builder = NaveBuilder.obtenerInstancia();

        Nave nuevaNave;

        // Configurar y construir la nave según el tipo
        switch (tipo.toLowerCase()) {
            case "ataque" -> {
                int danioOfensivo = obtenerValorDeConfiguracion(configuracion, "danioOfensivo", 100);
                int cantidadTripulantes = obtenerValorDeConfiguracion(configuracion, "cantidadTripulantes", 3);
                int cantidadMisiles = obtenerValorDeConfiguracion(configuracion, "cantidadMisiles", 5);

                nuevaNave = builder
                        .configurarNaveDeAtaque(danioOfensivo, cantidadTripulantes, cantidadMisiles)
                        .construirNaveDeAtaque();
            }
            case "embajadora" -> {
                int cantidadConsules = obtenerValorDeConfiguracion(configuracion, "cantidadConsules", 2);
                nuevaNave = builder
                        .configurarNaveEmbajadora(cantidadConsules)
                        .construirNaveEmbajadora();
            }
            case "mixta" -> {
                int danioOfensivo = obtenerValorDeConfiguracion(configuracion, "danioOfensivo", 70);
                int cantidadTripulantes = obtenerValorDeConfiguracion(configuracion, "cantidadTripulantes", 2);
                int cantidadConsules = obtenerValorDeConfiguracion(configuracion, "cantidadConsules", 1);
                int cantidadMisiles = obtenerValorDeConfiguracion(configuracion, "cantidadMisiles", 4);

                nuevaNave = builder
                        .configurarNaveMixta(danioOfensivo, cantidadTripulantes, cantidadConsules, cantidadMisiles)
                        .construirNaveMixta();
            }
            default -> throw new IllegalArgumentException("Tipo de nave no válido: " + tipo);
        }
        return navesRepository.save(nuevaNave);
    }

    private int obtenerValorDeConfiguracion(String configuracion, String clave, int valorPorDefecto) {
        return valorPorDefecto;
    }


    @Override
    public void atacarNave(Long naveAtacanteId, Long naveObjetivoId) {
        Nave naveAtacante = navesRepository.findById(naveAtacanteId)
                .orElseThrow(() -> new RuntimeException("Nave atacante no encontrada"));
        Nave naveObjetivo = navesRepository.findById(naveObjetivoId)
                .orElseThrow(() -> new RuntimeException("Nave objetivo no encontrada"));

        if (naveAtacante instanceof NaveDeAtaque) {
            ((NaveDeAtaque) naveAtacante).atacar(naveObjetivo);
            navesRepository.save(naveAtacante);
            navesRepository.save(naveObjetivo);
        } else {
            throw new RuntimeException("La nave atacante debe ser una nave de ataque");
        }
    }

    @Override
    public void asignarMision(Long naveId, Long misionId) {
        Nave nave = navesRepository.findById(naveId)
                .orElseThrow(() -> new RuntimeException("Nave no encontrada"));
        MisionBase mision = misionRepository.findById(misionId)
                .orElseThrow(() -> new RuntimeException("Misión no encontrada"));

        mision.ejecutar(nave);
        navesRepository.save(nave);
    }

    @Override
    public void viajarAPlaneta(Long naveId, Long planetaId) {
        Nave nave = navesRepository.findById(naveId)
                .orElseThrow(() -> new RuntimeException("Nave no encontrada"));
        Planeta planeta = planetaRepository.findById(planetaId)
                .orElseThrow(() -> new RuntimeException("Planeta no encontrado"));

        if (nave instanceof NaveEmbajadora) {
            ((NaveEmbajadora) nave).viajar(planeta);
            navesRepository.save(nave);
        } else {
            throw new RuntimeException("Solo las naves embajadoras pueden realizar viajes diplomáticos");
        }
    }

    @Override
    public List<Nave> buscarNavesPorRangoEnergia(double energiaMinima, double energiaMaxima) {
        return navesRepository.findByEnergiaEscudosBetween(energiaMinima, energiaMaxima);
    }

    private void aplicarConfiguracion(Nave nave, String configuracion) {
        // Implementar la lógica de configuración según el formato especificado
        // Ejemplo: "energia:100,escudos:50"
        String[] params = configuracion.split(",");
        for (String param : params) {
            String[] keyValue = param.split(":");
            switch (keyValue[0].toLowerCase()) {
                case "energia" -> nave.setEnergiaEscudos(Double.parseDouble(keyValue[1]));
                // Agregar más configuraciones según sea necesario
            }
        }
    }
}