package ar.edu.davinci.naves.controller.Interfaces;

import ar.edu.davinci.naves.models.naves.Nave;
import ar.edu.davinci.naves.models.misiones.MisionBase;
import ar.edu.davinci.naves.models.planeta.Planeta;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface NavesControllerInterfaz {
    ResponseEntity<Nave> agregarNaveAFlota(Nave nave);
    ResponseEntity<Nave> crearNaveConConfiguracion(String tipo, String configuracion);
    ResponseEntity<String> atacarNave(Long naveAtacanteId, Long naveObjetivoId);
    ResponseEntity<String> asignarMision(Long naveId, Long misionId);
    ResponseEntity<String> viajarAPlaneta(Long naveId, Long planetaId);
    List<Nave> obtenerNavesPorRangoEnergia(double energiaMinima, double energiaMaxima);
}