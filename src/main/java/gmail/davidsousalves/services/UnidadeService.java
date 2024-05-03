package gmail.davidsousalves.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import gmail.davidsousalves.dto.UnidadeDTO;
import gmail.davidsousalves.model.Unidade;
import gmail.davidsousalves.repositories.UnidadeRepository;

@Service
public class UnidadeService {

	@Autowired
	private UnidadeRepository repository;

	public List<Unidade> findAll() {

		return repository.findAll();
	}

	public UnidadeDTO findById(Long id) {
		Unidade unidade = repository.findById(id).orElseThrow(
				() -> new IllegalArgumentException("Id nao existe"));
		
		return new UnidadeDTO(unidade);	
		
	}

	public UnidadeDTO create(UnidadeDTO unidadeDto) {
		Unidade entity = new Unidade();
		copyDtoToEntity(unidadeDto, entity);
		entity = repository.save(entity);
		return new UnidadeDTO(entity);
	}

	public UnidadeDTO update(Long id, UnidadeDTO unidadeDto) {
		Unidade entity = repository.getReferenceById(id);
		copyDtoToEntity(unidadeDto, entity);
		entity = repository.save(entity);
		return new UnidadeDTO(entity);
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
	
	private void copyDtoToEntity(UnidadeDTO dto, Unidade entity) {
		entity.setSigla(dto.sigla());

	}
}
