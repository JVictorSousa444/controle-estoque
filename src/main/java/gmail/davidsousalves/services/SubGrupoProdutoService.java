package gmail.davidsousalves.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gmail.davidsousalves.model.SubgrupoProduto;
import gmail.davidsousalves.repositories.SubGrupoProdutoRepository;

@Service
public class SubGrupoProdutoService {

	@Autowired
	private SubGrupoProdutoRepository repository;
	
	public List<SubgrupoProduto> findAll() {
        return repository.findAll();
    }

    public Optional<SubgrupoProduto> findById(Long id) {
        return repository.findById(id);
    }

    public SubgrupoProduto save(SubgrupoProduto subGrupoProduto) {
        return repository.save(subGrupoProduto);
    }

    public void deleteById(Long id) {
    	repository.deleteById(id);
    }
	
	
}
