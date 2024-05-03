package gmail.davidsousalves.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import gmail.davidsousalves.dto.FornecedorDTO;
import gmail.davidsousalves.model.Fornecedor;
import gmail.davidsousalves.repositories.FornecedorRepository;

@Service
public class FornecedorService {

	@Autowired
	private FornecedorRepository repository;
	
	public List<Fornecedor> findAll() {
        return repository.findAll();
    }

	public FornecedorDTO findById(Long id) {
		Fornecedor fornecedor = repository.findById(id).orElseThrow(
				() -> new IllegalArgumentException("Id nao existe"));
		
		return new FornecedorDTO(fornecedor);	
		
	}

    public FornecedorDTO create(FornecedorDTO fornecedorDto) {
        Fornecedor fornecedor = new Fornecedor();
        copyDtoToEntity(fornecedorDto, fornecedor);
        fornecedor = repository.save(fornecedor);
        return new FornecedorDTO(fornecedor);
    }
    
    public FornecedorDTO update(Long id, FornecedorDTO fornecedorDto) {
    	Fornecedor entity = repository.getReferenceById(id);
		copyDtoToEntity(fornecedorDto, entity);
		entity = repository.save(entity);
		return new FornecedorDTO(entity);
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
	
    private void copyDtoToEntity(FornecedorDTO dto, Fornecedor entity) {
		entity.setNome(dto.nome());
		entity.setDescricao(dto.descricao());
		entity.setEmail(dto.email());
		entity.setTelefone(dto.telefone());

	}
}
