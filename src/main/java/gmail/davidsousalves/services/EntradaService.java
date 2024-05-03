package gmail.davidsousalves.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import gmail.davidsousalves.dto.EntradaDTO;
import gmail.davidsousalves.model.Entrada;
import gmail.davidsousalves.repositories.EntradaRepository;

@Service
public class EntradaService {

	@Autowired
	private EntradaRepository repository;
	
	public List<Entrada> findAll() {
        return repository.findAll();
    }

	public EntradaDTO findById(Long id) {
		Entrada entrada = repository.findById(id).orElseThrow(
				() -> new IllegalArgumentException("Id nao existe"));
		
		return new EntradaDTO(entrada);	
		
	}

	public EntradaDTO create(EntradaDTO entradaDto) {
		Entrada entity = new Entrada();
		copyDtoToEntity(entradaDto, entity);
		entity = repository.save(entity);
		return new EntradaDTO(entity);
	}
	
	public EntradaDTO update(Long id, EntradaDTO entradaDto) {
		Entrada entity = repository.getReferenceById(id);
		copyDtoToEntity(entradaDto, entity);
		entity = repository.save(entity);
		return new EntradaDTO(entity);
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
    
    private void copyDtoToEntity(EntradaDTO dto, Entrada entity) {
		entity.setDataEntrada(dto.dataEntrada());

	}
}
