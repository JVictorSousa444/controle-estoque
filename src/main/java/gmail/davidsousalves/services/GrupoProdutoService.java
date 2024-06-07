package gmail.davidsousalves.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import gmail.davidsousalves.dto.GrupoProdutoDTO;
import gmail.davidsousalves.exceptions.DatabaseException;
import gmail.davidsousalves.exceptions.ResourceNotFoundException;
import gmail.davidsousalves.model.GrupoProduto;
import gmail.davidsousalves.repositories.GrupoProdutoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class GrupoProdutoService {

	@Autowired
	private GrupoProdutoRepository repository;
	
	public List<GrupoProdutoDTO> findAll() {
        List<GrupoProduto> grupoProdutos = repository.findAll();
        return grupoProdutos.stream()
                .map(grupoProduto -> copyEntitytoDto(grupoProduto))
                .collect(Collectors.toList());
    }
	
	public Page<GrupoProdutoDTO> buscaPaginada(Pageable pageable) {
        return repository.findAll(pageable).map(GrupoProdutoDTO::new);
    }
	
	public GrupoProdutoDTO findById(Long id) {
		GrupoProduto grupoProduto = repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Id nao existe"));
		
		return new GrupoProdutoDTO(grupoProduto);	
	}

	public GrupoProdutoDTO create(GrupoProdutoDTO grupoProdutoDto ) {

		GrupoProduto grupoPorduto = new GrupoProduto();
		copyDtoToEntity(grupoProdutoDto, grupoPorduto);
		grupoPorduto = repository.save(grupoPorduto);
		return new GrupoProdutoDTO(grupoPorduto);
	}
	
	public GrupoProdutoDTO update(Long id, GrupoProdutoDTO grupoProdutoDto) {
		try {
		GrupoProduto entity = repository.getReferenceById(id);
		copyDtoToEntity(grupoProdutoDto, entity);
		entity = repository.save(entity);
		return new GrupoProdutoDTO(entity);
		}
			
		 catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
	}

	public void deleteById(Long id) {
		if (!repository.existsById(id)) {
    		throw new ResourceNotFoundException("Recurso não encontrado");
    	}
    	try {
    		repository.deleteById(id);    		
    	}
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }	
    	
	}
	
	private void copyDtoToEntity(GrupoProdutoDTO dto, GrupoProduto entity) {
		entity.setNome(dto.nome());
		entity.setDescricao(dto.descricao());

	}
	
	private GrupoProdutoDTO copyEntitytoDto(GrupoProduto grupoProduto) {
		GrupoProdutoDTO dto = new GrupoProdutoDTO(grupoProduto);
		return dto;
	}
}
