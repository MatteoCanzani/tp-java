package ar.edu.davinci.naves.controller.Interfaces;

import ar.edu.davinci.naves.models.misiones.MisionBase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface MisionControllerInterfaz {
    List<MisionBase> findAll();
    ResponseEntity<MisionBase> findById(@PathVariable Long unId);
    MisionBase save(@RequestBody MisionBase unaMision);
    ResponseEntity<MisionBase> update(@PathVariable Long unId, @RequestBody MisionBase unaMision);
    void deleteById(@PathVariable Long unId);
}