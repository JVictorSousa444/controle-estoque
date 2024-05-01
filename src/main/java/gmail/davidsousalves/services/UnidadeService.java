package gmail.davidsousalves.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gmail.davidsousalves.model.Unidade;
import gmail.davidsousalves.repositories.UnidadeRepository;

@Service
public class UnidadeService {

	@Autowired
	private UnidadeRepository repository;

	public List<Unidade> findAll() {

		return repository.findAll();
	}

	public Optional<Unidade> findById(Long id) {
		return repository.findById(id);
	}

	public Unidade create(Unidade unidade) {

		return repository.save(unidade);

	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}
}
