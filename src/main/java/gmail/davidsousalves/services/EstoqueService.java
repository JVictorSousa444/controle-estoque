package gmail.davidsousalves.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import gmail.davidsousalves.dto.EstoqueDTO;
import gmail.davidsousalves.model.Estoque;
import gmail.davidsousalves.repositories.EstoqueRepository;

@Service
public class EstoqueService {

	@Autowired
	private EstoqueRepository repository;
	
	public List<Estoque> findAll() {
        return repository.findAll();
    }

	public EstoqueDTO findById(Long id) {
		Estoque estoque = repository.findById(id).orElseThrow(
				() -> new IllegalArgumentException("Id nao existe"));
		
		return new EstoqueDTO(estoque);	
		
	}

	public EstoqueDTO create(EstoqueDTO estoqueDto) {
		Estoque entity = new Estoque();
		copyDtoToEntity(estoqueDto, entity);
		entity = repository.save(entity);
		return new EstoqueDTO(entity);
	}

	public EstoqueDTO update(Long id, EstoqueDTO estoqueDto) {
		Estoque entity = repository.getReferenceById(id);
		copyDtoToEntity(estoqueDto, entity);
		entity = repository.save(entity);
		return new EstoqueDTO(entity);
	}
	
	public void deleteById(Long id) {
		if (!repository.existsById(id)) {
    		throw new IllegalArgumentException("Recurso não encontrado");
    	}
    	try {
    		repository.deleteById(id);    		
    	}
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Falha de integridade referencial");
        }	
    	
	}
	
    private void copyDtoToEntity(EstoqueDTO dto, Estoque entity) {
		entity.setProduto(dto.produto());
		entity.setQuantidade(dto.quantidade());

	}
}
