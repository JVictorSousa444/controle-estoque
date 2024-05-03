package gmail.davidsousalves.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import gmail.davidsousalves.dto.SaidaItemDTO;
import gmail.davidsousalves.model.SaidaItem;
import gmail.davidsousalves.repositories.SaidaItemRepository;

@Service
public class SaidaItemService {

	@Autowired
	private SaidaItemRepository repository;
	
	public List<SaidaItem> findAll() {
        return repository.findAll();
    }

	public SaidaItemDTO findById(Long id) {
		SaidaItem saidaItem = repository.findById(id).orElseThrow(
				() -> new IllegalArgumentException("Id nao existe"));
		
		return new SaidaItemDTO(saidaItem);	
		
	}

	public SaidaItemDTO create(SaidaItemDTO saidaItemDto) {
		SaidaItem entity = new SaidaItem();
		copyDtoToEntity(saidaItemDto, entity);
		entity = repository.save(entity);
		return new SaidaItemDTO(entity);
	}
	
	public SaidaItemDTO update(Long id, SaidaItemDTO saidaItemDto) {
		SaidaItem entity = repository.getReferenceById(id);
		copyDtoToEntity(saidaItemDto, entity);
		entity = repository.save(entity);
		return new SaidaItemDTO(entity);
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
    
    private void copyDtoToEntity(SaidaItemDTO dto, SaidaItem entity) {
		entity.setProduto(dto.produto());
		entity.setQuantidade(dto.quantidade());
		entity.setSaida(dto.saida());
		entity.setValorUnitario(dto.valorUnitario());

	}
}
