package ar.edu.davinci.naves.controller;

import ar.edu.davinci.naves.controller.Interfaces.NavesControllerInterfaz;
import ar.edu.davinci.naves.models.naves.*;
import ar.edu.davinci.naves.models.misiones.MisionBase;
import ar.edu.davinci.naves.models.planeta.Planeta;
import ar.edu.davinci.naves.service.interfaz.INavesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/naves-nodriza")
public class NavesController implements NavesControllerInterfaz {

    private final INavesService navesService;

    @Autowired
    public NavesController(INavesService navesService) {
        this.navesService = navesService;
    }

    @PostMapping("/flota")
    public ResponseEntity<Nave> agregarNaveAFlota(@RequestBody Nave nave) {
        return ResponseEntity.ok(navesService.agregarNaveAFlota(nave));
    }

    @PostMapping("/crear")
    public ResponseEntity<Nave> crearNaveConConfiguracion(
            @RequestParam String tipo,
            @RequestBody String configuracion) {
        Nave nuevaNave = navesService.crearNaveConConfiguracion(tipo, configuracion);
        return ResponseEntity.ok(nuevaNave);
    }

    @PostMapping("/{naveId}/atacar/{objetivoId}")
    public ResponseEntity<String> atacarNave(
            @PathVariable Long naveId,
            @PathVariable Long objetivoId) {
        navesService.atacarNave(naveId, objetivoId);
        return ResponseEntity.ok("Ataque realizado con éxito");
    }

    @PostMapping("/{naveId}/mision/{misionId}")
    public ResponseEntity<String> asignarMision(
            @PathVariable Long naveId,
            @PathVariable Long misionId) {
        navesService.asignarMision(naveId, misionId);
        return ResponseEntity.ok("Misión asignada con éxito");
    }

    @PostMapping("/{naveId}/viajar/{planetaId}")
    public ResponseEntity<String> viajarAPlaneta(
            @PathVariable Long naveId,
            @PathVariable Long planetaId) {
        navesService.viajarAPlaneta(naveId, planetaId);
        return ResponseEntity.ok("Viaje iniciado con éxito");
    }

    @GetMapping("/por-energia")
    public List<Nave> obtenerNavesPorRangoEnergia(
            @RequestParam double energiaMinima,
            @RequestParam double energiaMaxima) {
        return navesService.buscarNavesPorRangoEnergia(energiaMinima, energiaMaxima);
    }
}