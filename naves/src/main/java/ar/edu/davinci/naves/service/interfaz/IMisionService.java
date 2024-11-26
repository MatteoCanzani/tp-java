package ar.edu.davinci.naves.service.interfaz;

import ar.edu.davinci.naves.models.misiones.MisionBase;
import java.util.List;
import java.util.Optional;

public interface IMisionService {
    List<MisionBase> listarTodasLasMisiones();
    Optional<MisionBase> obtenerMisionPorId(Long id);
    MisionBase guardarMision(MisionBase mision);
    Optional<MisionBase> actualizarMision(Long id, MisionBase mision);
    void eliminarMision(Long id);
}