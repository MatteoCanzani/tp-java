package ar.edu.davinci.naves.service.interfaz;

import ar.edu.davinci.naves.models.naves.Nave;
import java.util.List;

public interface INavesService {
    Nave agregarNaveAFlota(Nave nave);
    Nave crearNaveConConfiguracion(String tipo, String configuracion);
    void atacarNave(Long naveAtacanteId, Long naveObjetivoId);
    void asignarMision(Long naveId, Long misionId);
    void viajarAPlaneta(Long naveId, Long planetaId);
    List<Nave> buscarNavesPorRangoEnergia(double energiaMinima, double energiaMaxima);
}