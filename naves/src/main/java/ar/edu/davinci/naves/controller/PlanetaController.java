package ar.edu.davinci.naves.controller;

import ar.edu.davinci.naves.controller.Interfaces.PlanetaControllerInterfaz;
import ar.edu.davinci.naves.models.planeta.Planeta;
import ar.edu.davinci.naves.service.interfaz.IPlanetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planetas")
public class PlanetaController implements PlanetaControllerInterfaz {

    private final IPlanetaService planetaService;

    @Autowired
    public PlanetaController(IPlanetaService planetaService) {
        this.planetaService = planetaService;
    }

    @Override
    @GetMapping
    public List<Planeta> findAll() {
        return planetaService.listarTodosLosPlanetas();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Planeta> findById(@PathVariable Long id) {
        return planetaService.obtenerPlanetaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public Planeta save(@RequestBody Planeta planeta) {
        return planetaService.guardarPlaneta(planeta);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Planeta> update(@PathVariable Long id, @RequestBody Planeta planeta) {
        return planetaService.actualizarPlaneta(id, planeta)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        planetaService.eliminarPlaneta(id);
    }
}