package gmail.davidsousalves.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import gmail.davidsousalves.dto.EntradaItemDTO;
import gmail.davidsousalves.model.EntradaItem;
import gmail.davidsousalves.repositories.EntradaItemRepository;
import gmail.davidsousalves.services.exceptions.DatabaseException;
import gmail.davidsousalves.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EntradaItemService {

	@Autowired
	private EntradaItemRepository repository;

	public List<EntradaItemDTO> findAll() {
		List<EntradaItem> entradaItems = repository.findAll();
		return entradaItems.stream().map(entradaItem -> copyEntitytoDto(entradaItem)).collect(Collectors.toList());
	}

	public List<EntradaItemDTO> buscarClientesPaginados(int pagina, int tamanhoPagina, String campoOrdenacao) {

		Pageable pageable = PageRequest.of(pagina, tamanhoPagina, Sort.by(campoOrdenacao));
		Page<EntradaItem> entradaItemPage = repository.findAll(pageable);
		List<EntradaItemDTO> entradaItemDTO = entradaItemPage.getContent().stream().map(this::copyEntitytoDto)
				.collect(Collectors.toList());

		return entradaItemDTO;
	}

	public EntradaItemDTO findById(Long id) {
		EntradaItem entradaItem = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Id nao existe"));

		return new EntradaItemDTO(entradaItem);

	}

	public EntradaItemDTO create(EntradaItemDTO entradaItemDto) {
		EntradaItem entity = new EntradaItem();
		copyDtoToEntity(entradaItemDto, entity);
		entity = repository.save(entity);
		return new EntradaItemDTO(entity);
	}

	public EntradaItemDTO update(Long id, EntradaItemDTO entradaItemDto) {
		try {
			EntradaItem entity = repository.getReferenceById(id);
			copyDtoToEntity(entradaItemDto, entity);
			entity = repository.save(entity);
			return new EntradaItemDTO(entity);

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
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Falha de integridade referencial");
		}

	}

	private void copyDtoToEntity(EntradaItemDTO dto, EntradaItem entity) {
		entity.setProduto(dto.produto());
		entity.setEntrada(dto.entrada());
		entity.setQuantidade(dto.quantidade());
		entity.setValorUnitario(dto.valorUnitario());

	}

	private EntradaItemDTO copyEntitytoDto(EntradaItem entradaItem) {
		EntradaItemDTO dto = new EntradaItemDTO(entradaItem);
		return dto;
	}
}