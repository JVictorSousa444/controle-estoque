package gmail.davidsousalves.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import gmail.davidsousalves.dto.EntradaDTO;
import gmail.davidsousalves.model.Entrada;
import gmail.davidsousalves.repositories.EntradaRepository;
import gmail.davidsousalves.services.exceptions.DatabaseException;
import gmail.davidsousalves.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EntradaService {

	@Autowired
	private EntradaRepository repository;

	public List<EntradaDTO> findAll() {
		List<Entrada> entradas = repository.findAll();
		return entradas.stream().map(entrada -> copyEntitytoDto(entrada)).collect(Collectors.toList());
	}

	public Page<EntradaDTO> buscaPaginada(Pageable pageable) {
        return repository.findAll(pageable).map(EntradaDTO::new);
    }

	public EntradaDTO findById(Long id) {
		Entrada entrada = repository.findById(id).orElseThrow
				(() -> new ResourceNotFoundException("Id: " + id + " nao existe"));

		return new EntradaDTO(entrada);

	}

	public EntradaDTO create(EntradaDTO entradaDto) {
		Entrada entity = new Entrada();
		copyDtoToEntity(entradaDto, entity);
		entity = repository.save(entity);
		return new EntradaDTO(entity);
	}

	public EntradaDTO update(Long id, EntradaDTO entradaDto) {
		try {
			Entrada entity = repository.getReferenceById(id);
			copyDtoToEntity(entradaDto, entity);
			entity = repository.save(entity);
			return new EntradaDTO(entity);

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

	private void copyDtoToEntity(EntradaDTO dto, Entrada entity) {
		entity.setDataEntrada(dto.dataEntrada());

	}

	private EntradaDTO copyEntitytoDto(Entrada entrada) {
		EntradaDTO dto = new EntradaDTO(entrada);
		return dto;
	}
}
