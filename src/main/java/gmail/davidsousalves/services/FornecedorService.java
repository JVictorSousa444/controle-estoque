package gmail.davidsousalves.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gmail.davidsousalves.model.Fornecedor;
import gmail.davidsousalves.repositories.FornecedorRepository;

@Service
public class FornecedorService {

	@Autowired
	private FornecedorRepository repository;
	
	public List<Fornecedor> findAll() {
        return repository.findAll();
    }

    public Optional<Fornecedor> findById(Long id) {
        return repository.findById(id);
    }

    public Fornecedor save(Fornecedor fornecedor) {
        return repository.save(fornecedor);
    }

    public void deleteById(Long id) {
    	repository.deleteById(id);
    }
	
	
}
