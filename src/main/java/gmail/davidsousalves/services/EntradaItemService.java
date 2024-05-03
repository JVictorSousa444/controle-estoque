package gmail.davidsousalves.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import gmail.davidsousalves.dto.EntradaItemDTO;
import gmail.davidsousalves.model.EntradaItem;
import gmail.davidsousalves.repositories.EntradaItemRepository;

@Service
public class EntradaItemService {

	@Autowired
	private EntradaItemRepository repository;
	
	public List<EntradaItem> findAll() {
        return repository.findAll();
    }

	public EntradaItemDTO findById(Long id) {
		EntradaItem entradaItem = repository.findById(id).orElseThrow(
				() -> new IllegalArgumentException("Id nao existe"));
		
		return new EntradaItemDTO(entradaItem);	
		
	}

	public EntradaItemDTO create(EntradaItemDTO entradaItemDto) {
		EntradaItem entity = new EntradaItem();
		copyDtoToEntity(entradaItemDto, entity);
		entity = repository.save(entity);
		return new EntradaItemDTO(entity);
	}

	public EntradaItemDTO update(Long id, EntradaItemDTO entradaItemDto) {
		EntradaItem entity = repository.getReferenceById(id);
		copyDtoToEntity(entradaItemDto, entity);
		entity = repository.save(entity);
		return new EntradaItemDTO(entity);
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
	
    private void copyDtoToEntity(EntradaItemDTO dto, EntradaItem entity) {
		entity.setProduto(dto.produto());
		entity.setEntrada(dto.entrada());
		entity.setQuantidade(dto.quantidade());
		entity.setValorUnitario(dto.valorUnitario());

	}
}
