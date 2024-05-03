package gmail.davidsousalves.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import gmail.davidsousalves.dto.SubGrupoProdutoDTO;
import gmail.davidsousalves.model.SubgrupoProduto;
import gmail.davidsousalves.repositories.SubGrupoProdutoRepository;

@Service
public class SubGrupoProdutoService {

	@Autowired
	private SubGrupoProdutoRepository repository;
	
	public List<SubgrupoProduto> findAll() {
        return repository.findAll();
    }

	public SubGrupoProdutoDTO findById(Long id) {
		SubgrupoProduto subGrupo = repository.findById(id).orElseThrow(
				() -> new IllegalArgumentException("Id nao existe"));
		
		return new SubGrupoProdutoDTO(subGrupo);	
		
	}

	public SubGrupoProdutoDTO create(SubGrupoProdutoDTO subGrupoDto) {
		SubgrupoProduto entity = new SubgrupoProduto();
		copyDtoToEntity(subGrupoDto, entity);
		entity = repository.save(entity);
		return new SubGrupoProdutoDTO(entity);
	}
	
	public SubGrupoProdutoDTO update(Long id ,SubGrupoProdutoDTO subGrupoDto) {
		SubgrupoProduto entity = repository.getReferenceById(id);
		copyDtoToEntity(subGrupoDto, entity);
		entity = repository.save(entity);
		return new SubGrupoProdutoDTO(entity);
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
	
    private void copyDtoToEntity(SubGrupoProdutoDTO dto, SubgrupoProduto entity) {
		entity.setDescricao(dto.descricao());
		entity.setGrupoProduto(dto.grupoProduto());
	}
}
