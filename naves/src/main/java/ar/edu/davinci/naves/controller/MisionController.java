package ar.edu.davinci.naves.controller;

import ar.edu.davinci.naves.controller.Interfaces.MisionControllerInterfaz;
import ar.edu.davinci.naves.models.misiones.MisionBase;
import ar.edu.davinci.naves.service.interfaz.IMisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/misiones")
public class MisionController implements MisionControllerInterfaz {

    private final IMisionService misionService;

    @Autowired
    public MisionController(IMisionService misionService) {
        this.misionService = misionService;
    }

    @Override
    @GetMapping
    public List<MisionBase> findAll() {
        return misionService.listarTodasLasMisiones();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<MisionBase> findById(@PathVariable Long id) {
        return misionService.obtenerMisionPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public MisionBase save(@RequestBody MisionBase mision) {
        return misionService.guardarMision(mision);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<MisionBase> update(@PathVariable Long id, @RequestBody MisionBase mision) {
        return misionService.actualizarMision(id, mision)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        misionService.eliminarMision(id);
    }
}