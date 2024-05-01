package gmail.davidsousalves.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gmail.davidsousalves.model.Entrada;
import gmail.davidsousalves.repositories.EntradaRepository;

@Service
public class EntradaService {

	@Autowired
	private EntradaRepository repository;
	
	public List<Entrada> findAll() {
        return repository.findAll();
    }

    public Optional<Entrada> findById(Long id) {
        return repository.findById(id);
    }

    public Entrada save(Entrada entrada) {
        return repository.save(entrada);
    }

    public void deleteById(Long id) {
    	repository.deleteById(id);
    }
}
