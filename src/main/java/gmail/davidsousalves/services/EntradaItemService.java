package gmail.davidsousalves.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gmail.davidsousalves.model.EntradaItem;
import gmail.davidsousalves.repositories.EntradaItemRepository;

@Service
public class EntradaItemService {

	@Autowired
	private EntradaItemRepository repository;
	
	public List<EntradaItem> findAll() {
        return repository.findAll();
    }

    public Optional<EntradaItem> findById(Long id) {
        return repository.findById(id);
    }

    public EntradaItem save(EntradaItem entradaItem) {
        return repository.save(entradaItem);
    }

    public void deleteById(Long id) {
    	repository.deleteById(id);
    }
}
