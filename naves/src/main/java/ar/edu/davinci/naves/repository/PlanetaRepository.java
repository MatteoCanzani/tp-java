package ar.edu.davinci.naves.repository;

import ar.edu.davinci.naves.models.planeta.Planeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetaRepository extends JpaRepository<Planeta, Long> {
}