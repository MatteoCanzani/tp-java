package ar.edu.davinci.naves.service;

import ar.edu.davinci.naves.models.misiones.MisionBase;
import ar.edu.davinci.naves.repository.MisionRepository;
import ar.edu.davinci.naves.service.interfaz.IMisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MisionService implements IMisionService {

    private final MisionRepository misionRepository;

    @Autowired
    public MisionService(MisionRepository misionRepository) {
        this.misionRepository = misionRepository;
    }

    @Override
    public List<MisionBase> listarTodasLasMisiones() {
        return misionRepository.findAll();
    }

    @Override
    public Optional<MisionBase> obtenerMisionPorId(Long id) {
        return misionRepository.findById(id);
    }

    @Override
    public MisionBase guardarMision(MisionBase mision) {
        return misionRepository.save(mision);
    }

    @Override
    public Optional<MisionBase> actualizarMision(Long id, MisionBase misionActualizada) {
        return misionRepository.findById(id)
                .map(misionExistente -> {

                    if (misionExistente.getClass() == misionActualizada.getClass()) {
                        misionActualizada.setId(id);
                        return misionRepository.save(misionActualizada);
                    }
                    return misionExistente;
                });
    }

    @Override
    public void eliminarMision(Long id) {
        misionRepository.deleteById(id);
    }
}
