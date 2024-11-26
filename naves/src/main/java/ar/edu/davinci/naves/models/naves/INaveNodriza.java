package ar.edu.davinci.naves.models.naves;

import ar.edu.davinci.naves.models.misiones.Mision;

import java.util.List;

public interface INaveNodriza {
    void agregarNave(Nave nave);
    void eliminarNave(Nave nave);
    List<ClaseNave> getFlota();
    void ejecutarMision(Mision unaMision);
}
