package gmail.davidsousalves.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import gmail.davidsousalves.dto.SaidaDTO;
import gmail.davidsousalves.model.Saida;
import gmail.davidsousalves.repositories.SaidaRepository;

@Service
public class SaidaService {

	@Autowired
	private SaidaRepository repository;
	
	public List<Saida> findAll() {
        return repository.findAll();
    }

	public SaidaDTO findById(Long id) {
		Saida saida = repository.findById(id).orElseThrow(
				() -> new IllegalArgumentException("Id nao existe"));
		
		return new SaidaDTO(saida);	
		
	}

	public SaidaDTO create(SaidaDTO saidaDto) {
		Saida entity = new Saida();
		copyDtoToEntity(saidaDto, entity);
		entity = repository.save(entity);
		return new SaidaDTO(entity);
	}
	
	public SaidaDTO update(Long id, SaidaDTO saidaDto) {
		Saida entity = repository.getReferenceById(id);
		copyDtoToEntity(saidaDto, entity);
		entity = repository.save(entity);
		return new SaidaDTO(entity);
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
    
    private void copyDtoToEntity(SaidaDTO dto, Saida entity) {
		entity.setData(dto.data());
		entity.setDataVencimento(dto.dataVencimento());
		entity.setCliente(dto.cliente());

	}
}
