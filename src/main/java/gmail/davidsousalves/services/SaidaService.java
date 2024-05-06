package gmail.davidsousalves.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import gmail.davidsousalves.dto.SaidaDTO;
import gmail.davidsousalves.model.Saida;
import gmail.davidsousalves.repositories.SaidaRepository;
import gmail.davidsousalves.services.exceptions.DatabaseException;
import gmail.davidsousalves.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class SaidaService {

	@Autowired
	private SaidaRepository repository;
	
	public List<SaidaDTO> findAll() {
        List<Saida> saidas = repository.findAll();
        return saidas.stream()
                .map(saida -> copyEntitytoDto(saida))
                .collect(Collectors.toList());
    }

	public SaidaDTO findById(Long id) {
		Saida saida = repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Id nao existe"));
		
		return new SaidaDTO(saida);	
		
	}

	public SaidaDTO create(SaidaDTO saidaDto) {
		Saida entity = new Saida();
		copyDtoToEntity(saidaDto, entity);
		entity = repository.save(entity);
		return new SaidaDTO(entity);
	}
	
	public SaidaDTO update(Long id, SaidaDTO saidaDto) {
		try {
			Saida entity = repository.getReferenceById(id);
			copyDtoToEntity(saidaDto, entity);
			entity = repository.save(entity);
			return new SaidaDTO(entity);
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
    
    private void copyDtoToEntity(SaidaDTO dto, Saida entity) {
		entity.setData(dto.data());
		entity.setDataVencimento(dto.dataVencimento());
		entity.setDataPagamento(dto.dataPagamento());
		entity.setCliente(dto.cliente());

	}
    
    private SaidaDTO copyEntitytoDto(Saida saida) {
    	SaidaDTO dto = new SaidaDTO(saida);
		return dto;
	}
}
