// NavesRepository.java
package ar.edu.davinci.naves.repository;

import ar.edu.davinci.naves.models.naves.Nave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NavesRepository extends JpaRepository<Nave, Long> {
    List<Nave> findByEnergiaEscudosBetween(double energiaMinima, double energiaMaxima);
}