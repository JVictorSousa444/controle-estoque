package gmail.davidsousalves.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import gmail.davidsousalves.dto.SaidaItemDTO;
import gmail.davidsousalves.model.SaidaItem;
import gmail.davidsousalves.repositories.SaidaItemRepository;
import gmail.davidsousalves.services.exceptions.DatabaseException;
import gmail.davidsousalves.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class SaidaItemService {

	@Autowired
	private SaidaItemRepository repository;
	
	public List<SaidaItemDTO> findAll() {
        List<SaidaItem> saidaItems = repository.findAll();
        return saidaItems.stream()
                .map(saidaItem -> copyEntitytoDto(saidaItem))
                .collect(Collectors.toList());
    }
	public SaidaItemDTO findById(Long id) {
		SaidaItem saidaItem = repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Id nao existe"));
		
		return new SaidaItemDTO(saidaItem);	
		
	}

	public SaidaItemDTO create(SaidaItemDTO saidaItemDto) {
		SaidaItem entity = new SaidaItem();
		copyDtoToEntity(saidaItemDto, entity);
		entity = repository.save(entity);
		return new SaidaItemDTO(entity);
	}
	
	public SaidaItemDTO update(Long id, SaidaItemDTO saidaItemDto) {
		try {
			SaidaItem entity = repository.getReferenceById(id);
			copyDtoToEntity(saidaItemDto, entity);
			entity = repository.save(entity);
			return new SaidaItemDTO(entity);
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
    
    private void copyDtoToEntity(SaidaItemDTO dto, SaidaItem entity) {
		entity.setProduto(dto.produto());
		entity.setQuantidade(dto.quantidade());
		entity.setSaida(dto.saida());
		entity.setValorUnitario(dto.valorUnitario());

	}
    
    private SaidaItemDTO copyEntitytoDto(SaidaItem saidaItem) {
    	SaidaItemDTO dto = new SaidaItemDTO(saidaItem);
		return dto;
	}
}
