package gmail.davidsousalves.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import gmail.davidsousalves.dto.ProdutoDTO;
import gmail.davidsousalves.dto.SubGrupoProdutoDTO;
import gmail.davidsousalves.exceptions.DatabaseException;
import gmail.davidsousalves.exceptions.ResourceNotFoundException;
import gmail.davidsousalves.model.Produto;
import gmail.davidsousalves.model.SubgrupoProduto;
import gmail.davidsousalves.repositories.SubGrupoProdutoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class SubGrupoProdutoService {

	@Autowired
	private SubGrupoProdutoRepository repository;
	
	public List<SubGrupoProdutoDTO> findAll() {
        List<SubgrupoProduto> subgrupoprodutos = repository.findAll();
        return subgrupoprodutos.stream()
                .map(subgrupoproduto -> copyEntitytoDto(subgrupoproduto))
                .collect(Collectors.toList());
    }
	
	public Page<SubGrupoProdutoDTO> buscaPaginada(Pageable pageable) {
        return repository.findAll(pageable).map(SubGrupoProdutoDTO::new);
    }
	
	public SubGrupoProdutoDTO findById(Long id) {
		SubgrupoProduto subGrupo = repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Id nao existe"));
		
		return new SubGrupoProdutoDTO(subGrupo);	
		
	}

	public SubGrupoProdutoDTO create(SubGrupoProdutoDTO subGrupoDto) {
		SubgrupoProduto entity = new SubgrupoProduto();
		copyDtoToEntity(subGrupoDto, entity);
		repository.save(entity);
		return new SubGrupoProdutoDTO(entity);
	}
	
	
	public SubGrupoProdutoDTO update(Long id ,SubGrupoProdutoDTO subGrupoDto) {
		try {
			SubgrupoProduto entity = repository.getReferenceById(id);
			copyDtoToEntity(subGrupoDto, entity);
			entity = repository.save(entity);
			return new SubGrupoProdutoDTO(entity);
			
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
    	}
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }	
    	
	}
	
    private void copyDtoToEntity(SubGrupoProdutoDTO dto, SubgrupoProduto entity) {
		entity.setDescricao(dto.descricao());
		entity.setGrupoProduto(dto.grupoProduto());
	}
    
    private SubGrupoProdutoDTO copyEntitytoDto(SubgrupoProduto unidade) {
    	SubGrupoProdutoDTO dto = new SubGrupoProdutoDTO(unidade);
		return dto;
	}
}
