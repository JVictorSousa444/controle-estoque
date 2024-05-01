package gmail.davidsousalves.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gmail.davidsousalves.model.Fabricante;
import gmail.davidsousalves.repositories.FabricanteRepository;

@Service
public class FabricanteService {

	@Autowired
	private FabricanteRepository repository;
	
	
	public List<Fabricante> findAll() {
        return repository.findAll();
    }

    public Optional<Fabricante> findById(Long id) {
        return repository.findById(id);
    }

    public Fabricante save(Fabricante fabricante) {
        return repository.save(fabricante);
    }

    public void deleteById(Long id) {
    	repository.deleteById(id);
    }
	
	
}
