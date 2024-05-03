package gmail.davidsousalves.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import gmail.davidsousalves.dto.ProdutoDTO;
import gmail.davidsousalves.model.Produto;
import gmail.davidsousalves.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;
	
	public List<Produto> findAll() {
        return repository.findAll();
    }

	public ProdutoDTO findById(Long id) {
		Produto produto = repository.findById(id).orElseThrow(
				() -> new IllegalArgumentException("Id nao existe"));
		
		return new ProdutoDTO(produto);	
		
	}

    public ProdutoDTO create(ProdutoDTO produtoDto) {
    	Produto entity = new Produto();
		copyDtoToEntity(produtoDto, entity);
		entity = repository.save(entity);
		return new ProdutoDTO(entity);
    
    }
    
    public ProdutoDTO update(Long id, ProdutoDTO produtoDto) {
    	Produto entity = repository.getReferenceById(id);
		copyDtoToEntity(produtoDto, entity);
		entity = repository.save(entity);
		return new ProdutoDTO(entity);
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
    
    private void copyDtoToEntity(ProdutoDTO dto, Produto entity) {
		entity.setNome(dto.nome());
		entity.setDescricao(dto.descricao());
		entity.setCodigo(dto.codigo());
		entity.setGrupoproduto(dto.grupoproduto());
		entity.setTipoUnidade(dto.tipoUnidade());
		entity.setFabricante(dto.fabricante());
		entity.setLucroSugerido(dto.lucroSugerido());
		entity.setFornecedor(dto.fornecedor());
	}
}
