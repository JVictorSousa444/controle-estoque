package gmail.davidsousalves.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gmail.davidsousalves.model.SaidaItem;
import gmail.davidsousalves.repositories.SaidaItemRepository;

@Service
public class SaidaItemService {

	@Autowired
	private SaidaItemRepository repository;
	
	public List<SaidaItem> findAll() {
        return repository.findAll();
    }

    public Optional<SaidaItem> findById(Long id) {
        return repository.findById(id);
    }

    public SaidaItem save(SaidaItem saidaItem) {
        return repository.save(saidaItem);
    }

    public void deleteById(Long id) {
    	repository.deleteById(id);
    }
}
