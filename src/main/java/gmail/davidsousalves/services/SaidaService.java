package gmail.davidsousalves.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gmail.davidsousalves.model.Saida;
import gmail.davidsousalves.repositories.SaidaRepository;

@Service
public class SaidaService {

	@Autowired
	private SaidaRepository repository;
	
	public List<Saida> findAll() {
        return repository.findAll();
    }

    public Optional<Saida> findById(Long id) {
        return repository.findById(id);
    }

    public Saida save(Saida saida) {
        return repository.save(saida);
    }

    public void deleteById(Long id) {
    	repository.deleteById(id);
    }
}
