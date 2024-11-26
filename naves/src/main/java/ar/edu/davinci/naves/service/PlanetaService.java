package ar.edu.davinci.naves.service;

import ar.edu.davinci.naves.models.planeta.Planeta;
import ar.edu.davinci.naves.repository.PlanetaRepository;
import ar.edu.davinci.naves.service.interfaz.IPlanetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanetaService implements IPlanetaService {

    private final PlanetaRepository planetaRepository;

    @Autowired
    public PlanetaService(PlanetaRepository planetaRepository) {
        this.planetaRepository = planetaRepository;
    }

    @Override
    public List<Planeta> listarTodosLosPlanetas() {
        return planetaRepository.findAll();
    }

    @Override
    public Optional<Planeta> obtenerPlanetaPorId(Long id) {
        return planetaRepository.findById(id);
    }

    @Override
    public Planeta guardarPlaneta(Planeta planeta) {
        return planetaRepository.save(planeta);
    }

    @Override
    public Optional<Planeta> actualizarPlaneta(Long id, Planeta planetaActualizado) {
        return planetaRepository.findById(id)
                .map(planetaExistente -> {
                    planetaExistente.setNombre(planetaActualizado.getNombre());
                    planetaExistente.setDistanciaALaTierra(planetaActualizado.getDistanciaALaTierra());
                    return planetaRepository.save(planetaExistente);
                });
    }

    @Override
    public void eliminarPlaneta(Long id) {
        planetaRepository.deleteById(id);
    }
}