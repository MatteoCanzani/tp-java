package ar.edu.davinci.naves.controller.Interfaces;

import ar.edu.davinci.naves.models.planeta.Planeta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PlanetaControllerInterfaz {
    List<Planeta> findAll();
    ResponseEntity<Planeta> findById(@PathVariable Long unId);
    Planeta save(@RequestBody Planeta unPlaneta);
    ResponseEntity<Planeta> update(@PathVariable Long unId, @RequestBody Planeta unPlaneta);
    void deleteById(@PathVariable Long unId);
}