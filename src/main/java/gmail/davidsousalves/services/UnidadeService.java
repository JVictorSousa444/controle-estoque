package gmail.davidsousalves.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import gmail.davidsousalves.dto.UnidadeDTO;
import gmail.davidsousalves.exceptions.DatabaseException;
import gmail.davidsousalves.exceptions.ResourceNotFoundException;
import gmail.davidsousalves.model.Unidade;
import gmail.davidsousalves.repositories.UnidadeRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UnidadeService {

	@Autowired
	private UnidadeRepository repository;

	public List<UnidadeDTO> findAll() {
        List<Unidade> unidades = repository.findAll();
        return unidades.stream()
                .map(unidade -> copyEntitytoDto(unidade))
                .collect(Collectors.toList());
    }
	
	public Page<UnidadeDTO> buscaPaginada(Pageable pageable) {
        return repository.findAll(pageable).map(UnidadeDTO::new);
    }

	public UnidadeDTO findById(Long id) {
		Unidade unidade = repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Id nao existe"));
		
		return new UnidadeDTO(unidade);	
		
	}

	public UnidadeDTO create(UnidadeDTO unidadeDto) {
		
		Unidade entity = new Unidade();
		copyDtoToEntity(unidadeDto, entity);
		entity = repository.save(entity);
		return new UnidadeDTO(entity);
	}

	public UnidadeDTO update(Long id, UnidadeDTO unidadeDto) {
		try {
			Unidade entity = repository.getReferenceById(id);
			copyDtoToEntity(unidadeDto, entity);
			entity = repository.save(entity);
			return new UnidadeDTO(entity);
			
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
	
	private void copyDtoToEntity(UnidadeDTO dto, Unidade entity) {
		entity.setSigla(dto.sigla());

	}
	
	private UnidadeDTO copyEntitytoDto(Unidade unidade) {
		UnidadeDTO dto = new UnidadeDTO(unidade);
		return dto;
	}
}
