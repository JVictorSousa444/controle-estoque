package gmail.davidsousalves.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gmail.davidsousalves.model.Estoque;
import gmail.davidsousalves.repositories.EstoqueRepository;

@Service
public class EstoqueService {

	@Autowired
	private EstoqueRepository repository;
	
	public List<Estoque> findAll() {
        return repository.findAll();
    }

    public Optional<Estoque> findById(Long id) {
        return repository.findById(id);
    }

    public Estoque save(Estoque estoque) {
        return repository.save(estoque);
    }

    public void deleteById(Long id) {
    	repository.deleteById(id);
    }
	
}
