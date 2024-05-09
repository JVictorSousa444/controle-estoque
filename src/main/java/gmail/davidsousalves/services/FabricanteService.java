package gmail.davidsousalves.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import gmail.davidsousalves.dto.FabricanteDTO;
import gmail.davidsousalves.model.Fabricante;
import gmail.davidsousalves.repositories.FabricanteRepository;
import gmail.davidsousalves.services.exceptions.DatabaseException;
import gmail.davidsousalves.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class FabricanteService {

	@Autowired
	private FabricanteRepository repository;
	
	
	public List<FabricanteDTO> findAll() {
        List<Fabricante> fabricantes = repository.findAll();
        return fabricantes.stream()
                .map(fabricante -> copyEntitytoDto(fabricante))
                .collect(Collectors.toList());
    }
	
	public Page<FabricanteDTO> buscaPaginada(Pageable pageable) {
        return repository.findAll(pageable).map(FabricanteDTO::new);
    }

	public FabricanteDTO findById(Long id) {
		Fabricante fabricante = repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Id nao existe"));
		
		return new FabricanteDTO(fabricante);	
		
	}

	public FabricanteDTO create(FabricanteDTO fabricanteDto) {
		Fabricante entity = new Fabricante();
		copyDtoToEntity(fabricanteDto, entity);
		entity = repository.save(entity);
		return new FabricanteDTO(entity);
	}
	public FabricanteDTO update(Long id, FabricanteDTO fabricanteDto) {
		try {
			Fabricante entity = repository.getReferenceById(id);
			copyDtoToEntity(fabricanteDto, entity);
			entity = repository.save(entity);
			return new FabricanteDTO(entity);
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
	
    private void copyDtoToEntity(FabricanteDTO dto, Fabricante entity) {
		entity.setNome(dto.nome());
		entity.setDescricao(dto.descricao());
		entity.setNumCpfCnpj(dto.numCpfCnpj());
		entity.setTelefone(dto.telefone());
		entity.setEmail(dto.email());
		entity.setDocumento(dto.documento());

	}
    
    private FabricanteDTO copyEntitytoDto(Fabricante fabricante) {
    	FabricanteDTO dto = new FabricanteDTO(fabricante);
		return dto;
	}
}
