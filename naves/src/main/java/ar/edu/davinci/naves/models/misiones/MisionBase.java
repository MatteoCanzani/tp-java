package ar.edu.davinci.naves.models.misiones;

import ar.edu.davinci.naves.models.naves.ClaseNave;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class MisionBase implements Mision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public abstract void ejecutar(ClaseNave nave);
}
