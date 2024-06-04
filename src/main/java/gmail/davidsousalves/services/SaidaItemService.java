package gmail.davidsousalves.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import gmail.davidsousalves.dto.SaidaItemDTO;
import gmail.davidsousalves.model.SaidaItem;
import gmail.davidsousalves.repositories.SaidaItemRepository;
import gmail.davidsousalves.services.exceptions.DatabaseException;
import gmail.davidsousalves.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaidaItemService {

	@Autowired
	private SaidaItemRepository repository;

	@Autowired
	private ProdutoService produtoService;
	
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
	
	public Page<SaidaItemDTO> buscaPaginada(Pageable pageable) {
        return repository.findAll(pageable).map(SaidaItemDTO::new);
    }

	public SaidaItemDTO create(SaidaItemDTO saidaItemDto) {
		SaidaItem entity = new SaidaItem();
		copyDtoToEntity(saidaItemDto, entity);
		entity = repository.save(entity);
		return new SaidaItemDTO(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<SaidaItem>  create(List<SaidaItem> itens) {

		for(SaidaItem saidaItem : itens) {
			if (saidaItem.getId() != null) {
				continue;
			}
			Long idProduto = saidaItem.getProduto().getId();
			Long quantidade = saidaItem.getQuantidade();
			produtoService.atualizarQuantidadeRemover(idProduto, quantidade != null ? quantidade.intValue() : 0);
		}
		List<SaidaItem> saidaItems = repository.saveAll(itens);
		return saidaItems;
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

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteById(Long id) {
		if (!repository.existsById(id)) {
    		throw new ResourceNotFoundException("Recurso não encontrado");
    	}
    	try {
			Optional<SaidaItem> item = repository.findById(id);
			SaidaItem saidaItem = item.get();
			produtoService.atualizarQuantidade(saidaItem.getProduto().getId(), saidaItem.getQuantidade().intValue());
			repository.deleteById(id);
    	}
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }	
    	
	}

	public List<SaidaItem> findByEntityId(Long entityId) {
		return repository.findBySaidaId(entityId);
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
