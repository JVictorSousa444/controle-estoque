package gmail.davidsousalves.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gmail.davidsousalves.model.GrupoProduto;
import gmail.davidsousalves.repositories.GrupoProdutoRepository;

@Service
public class GrupoProdutoService {

	@Autowired
	private GrupoProdutoRepository repository;
	
	public List<GrupoProduto> findAll(){
		return repository.findAll();
	}
	
	public Optional<GrupoProduto> findById(Long id) {
		return repository.findById(id);
	}

	public GrupoProduto create(GrupoProduto grupoProduto ) {

		return repository.save(grupoProduto);

	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}
}
