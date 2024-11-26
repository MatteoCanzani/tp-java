package ar.edu.davinci.naves.service.interfaz;

import ar.edu.davinci.naves.models.planeta.Planeta;
import java.util.List;
import java.util.Optional;

public interface IPlanetaService {
    List<Planeta> listarTodosLosPlanetas();
    Optional<Planeta> obtenerPlanetaPorId(Long id);
    Planeta guardarPlaneta(Planeta planeta);
    Optional<Planeta> actualizarPlaneta(Long id, Planeta planeta);
    void eliminarPlaneta(Long id);
}