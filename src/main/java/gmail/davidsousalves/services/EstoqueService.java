package gmail.davidsousalves.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import gmail.davidsousalves.dto.EstoqueDTO;
import gmail.davidsousalves.exceptions.DatabaseException;
import gmail.davidsousalves.exceptions.ResourceNotFoundException;
import gmail.davidsousalves.model.Estoque;
import gmail.davidsousalves.repositories.EstoqueRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EstoqueService {

	@Autowired
	private EstoqueRepository repository;

	public List<EstoqueDTO> findAll() {
		List<Estoque> estoques = repository.findAll();
		return estoques.stream().map(estoque -> copyEntitytoDto(estoque)).collect(Collectors.toList());
	}

	public Page<EstoqueDTO> buscaPaginada(Pageable pageable) {
        return repository.findAll(pageable).map(EstoqueDTO::new);
    }

	public EstoqueDTO findById(Long id) {
		Estoque estoque = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id nao existe"));

		return new EstoqueDTO(estoque);

	}

	public EstoqueDTO create(EstoqueDTO estoqueDto) {
		Estoque entity = new Estoque();
		copyDtoToEntity(estoqueDto, entity);
		entity = repository.save(entity);
		return new EstoqueDTO(entity);
	}

	public EstoqueDTO update(Long id, EstoqueDTO estoqueDto) {
		try {
			Estoque entity = repository.getReferenceById(id);
			copyDtoToEntity(estoqueDto, entity);
			entity = repository.save(entity);
			return new EstoqueDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Recurso não encontrado");

		}
	}

	public void deleteById(Long id) {
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Falha de integridade referencial");
		}

	}

	private void copyDtoToEntity(EstoqueDTO dto, Estoque entity) {
		entity.setProduto(dto.produto());
		entity.setQuantidade(dto.quantidade());

	}

	private EstoqueDTO copyEntitytoDto(Estoque estoque) {
		EstoqueDTO dto = new EstoqueDTO(estoque);
		return dto;
	}
}
