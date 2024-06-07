package gmail.davidsousalves.services;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import gmail.davidsousalves.dto.FornecedorDTO;
import gmail.davidsousalves.exceptions.DatabaseException;
import gmail.davidsousalves.exceptions.ResourceNotFoundException;
import gmail.davidsousalves.model.Fornecedor;
import gmail.davidsousalves.repositories.FornecedorRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class FornecedorService {

	@Autowired
	private FornecedorRepository repository;
	
	public List<FornecedorDTO> findAll() {
        List<Fornecedor> fornecedores = repository.findAll();
        return fornecedores.stream()
                .map(fornecedor -> copyEntitytoDto(fornecedor))
                .collect(Collectors.toList());
    }
	
	public Page<FornecedorDTO> buscaPaginada(String nome, Pageable pageable) {
		if (StringUtils.isEmpty(nome)) {
        	return repository.findAll(pageable).map(FornecedorDTO::new);
		} else {
			return repository.findByNomeContainingIgnoreCase(nome, pageable).map(FornecedorDTO::new);
		}
    }

	public FornecedorDTO findById(Long id) {
		Fornecedor fornecedor = repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Id nao existe"));
		
		return new FornecedorDTO(fornecedor);	
		
	}

    public FornecedorDTO create(FornecedorDTO fornecedorDto) {
        Fornecedor fornecedor = new Fornecedor();
        copyDtoToEntity(fornecedorDto, fornecedor);
        fornecedor = repository.save(fornecedor);
        return new FornecedorDTO(fornecedor);
    }
    
    public FornecedorDTO update(Long id, FornecedorDTO fornecedorDto) {
    
    	try {
    		Fornecedor entity = repository.getReferenceById(id);
    		copyDtoToEntity(fornecedorDto, entity);
    		entity = repository.save(entity);
    		return new FornecedorDTO(entity);
    		
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
	
    private void copyDtoToEntity(FornecedorDTO dto, Fornecedor entity) {
		entity.setNome(dto.nome());
		entity.setDescricao(dto.descricao());
		entity.setEmail(dto.email());
		entity.setTelefone(dto.telefone());

	}
    
    private FornecedorDTO copyEntitytoDto(Fornecedor fornecedor) {
    	FornecedorDTO dto = new FornecedorDTO(fornecedor);
		return dto;
	}
}
