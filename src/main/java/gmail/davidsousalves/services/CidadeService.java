package gmail.davidsousalves.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import gmail.davidsousalves.dto.CidadeDTO;
import gmail.davidsousalves.exceptions.DatabaseException;
import gmail.davidsousalves.exceptions.ResourceNotFoundException;
import gmail.davidsousalves.model.Cidade;
import gmail.davidsousalves.repositories.CidadeRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repository;

	public List<CidadeDTO> findAll(String nome) {
		List<Cidade> cidades = new ArrayList<>();
		if (nome != null && !nome.isBlank()) {
			cidades = repository.findByNome(nome);
		} else {
			cidades = repository.findAll();
		}
		return cidades.stream().map(cidade -> copyEntitytoDto(cidade)).collect(Collectors.toList());
	}

	public Page<CidadeDTO> buscaPaginada(Pageable pageable) {
		return repository.findAll(pageable).map(CidadeDTO::new);
	}

	public CidadeDTO findById(Long id) {
		Cidade cidade = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id nao existe"));

		return new CidadeDTO(cidade);

	}

	public CidadeDTO create(CidadeDTO cidadeDTO) {
		Cidade entity = new Cidade();
		copyDtoToEntity(cidadeDTO, entity);
		entity = repository.save(entity);
		return new CidadeDTO(entity);
	}

	public CidadeDTO update(Long id, CidadeDTO cidadeDto) {
		try {
			Cidade entity = repository.getReferenceById(id);
			copyDtoToEntity(cidadeDto, entity);
			entity = repository.save(entity);
			return new CidadeDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}

	}

	public void deleteById(Long id) {
		try {
			if (!repository.existsById(id)) {
				throw new ResourceNotFoundException("Recurso não encontrado");
			}
			repository.deleteById(id);

		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Falha de integridade referencial");
		}

	}

	private void copyDtoToEntity(CidadeDTO dto, Cidade entity) {
		entity.setNome(dto.nome());
		entity.setSiglaEstado(dto.siglaEstado());

	}

	private CidadeDTO copyEntitytoDto(Cidade cidade) {
		CidadeDTO dto = new CidadeDTO(cidade);
		return dto;
	}
}
