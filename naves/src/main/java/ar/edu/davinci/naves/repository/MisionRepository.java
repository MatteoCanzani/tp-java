package ar.edu.davinci.naves.repository;

import ar.edu.davinci.naves.models.misiones.MisionBase;
import ar.edu.davinci.naves.models.misiones.MisionBelica;
import ar.edu.davinci.naves.models.misiones.MisionDiplomatica;
import ar.edu.davinci.naves.models.misiones.MisionMixta;
import ar.edu.davinci.naves.models.naves.ClaseNave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MisionRepository extends JpaRepository<MisionBase, Long> {
    List<MisionBelica> findByObjetivo(ClaseNave objetivo);
    List<MisionDiplomatica> findByTipoMision(String tipoMision);

}