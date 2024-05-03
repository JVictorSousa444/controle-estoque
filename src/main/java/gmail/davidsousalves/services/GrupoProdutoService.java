package gmail.davidsousalves.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import gmail.davidsousalves.dto.GrupoProdutoDTO;
import gmail.davidsousalves.model.GrupoProduto;
import gmail.davidsousalves.repositories.GrupoProdutoRepository;

@Service
public class GrupoProdutoService {

	@Autowired
	private GrupoProdutoRepository repository;
	
	public List<GrupoProduto> findAll(){
		return repository.findAll();
	}
	
	public GrupoProdutoDTO findById(Long id) {
		GrupoProduto grupoProduto = repository.findById(id).orElseThrow(
				() -> new IllegalArgumentException("Id nao existe"));
		
		return new GrupoProdutoDTO(grupoProduto);	
	}

	public GrupoProdutoDTO create(GrupoProdutoDTO grupoProdutoDto ) {

		GrupoProduto grupoPorduto = new GrupoProduto();
		copyDtoToEntity(grupoProdutoDto, grupoPorduto);
		grupoPorduto = repository.save(grupoPorduto);
		return new GrupoProdutoDTO(grupoPorduto);
	}
	
	public GrupoProdutoDTO update(Long id, GrupoProdutoDTO grupoProdutoDto) {
		GrupoProduto entity = repository.getReferenceById(id);
		copyDtoToEntity(grupoProdutoDto, entity);
		entity = repository.save(entity);
		return new GrupoProdutoDTO(entity);
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
	
	private void copyDtoToEntity(GrupoProdutoDTO dto, GrupoProduto entity) {
		entity.setNome(dto.nome());
		entity.setDescricao(dto.descricao());

	}
}
