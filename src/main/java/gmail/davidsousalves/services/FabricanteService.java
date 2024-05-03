package gmail.davidsousalves.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import gmail.davidsousalves.dto.FabricanteDTO;
import gmail.davidsousalves.model.Fabricante;
import gmail.davidsousalves.repositories.FabricanteRepository;

@Service
public class FabricanteService {

	@Autowired
	private FabricanteRepository repository;
	
	
	public List<Fabricante> findAll() {
        return repository.findAll();
    }

	public FabricanteDTO findById(Long id) {
		Fabricante fabricante = repository.findById(id).orElseThrow(
				() -> new IllegalArgumentException("Id nao existe"));
		
		return new FabricanteDTO(fabricante);	
		
	}

	public FabricanteDTO create(FabricanteDTO fabricanteDto) {
		Fabricante entity = new Fabricante();
		copyDtoToEntity(fabricanteDto, entity);
		entity = repository.save(entity);
		return new FabricanteDTO(entity);
	}
	public FabricanteDTO update(Long id, FabricanteDTO fabricanteDto) {
		Fabricante entity = repository.getReferenceById(id);
		copyDtoToEntity(fabricanteDto, entity);
		entity = repository.save(entity);
		return new FabricanteDTO(entity);
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
	
    private void copyDtoToEntity(FabricanteDTO dto, Fabricante entity) {
		entity.setNome(dto.nome());
		entity.setDescricao(dto.descricao());
		entity.setNumCpfCnpj(dto.numCpfCnpj());
		entity.setTelefone(dto.telefone());
		entity.setEmail(dto.email());
		entity.setDocumento(dto.documento());

	}
}
