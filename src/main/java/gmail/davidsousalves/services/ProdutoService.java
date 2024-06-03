package gmail.davidsousalves.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import gmail.davidsousalves.dto.ProdutoDTO;
import gmail.davidsousalves.exceptions.DatabaseException;
import gmail.davidsousalves.exceptions.ResourceNotFoundException;
import gmail.davidsousalves.model.Produto;
import gmail.davidsousalves.repositories.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;
	
	public List<ProdutoDTO> findAll() {
        List<Produto> produtos = repository.findAll();
        return produtos.stream()
                .map(produto -> copyEntitytoDto(produto))
                .collect(Collectors.toList());
    }
	
	public ProdutoDTO findById(Long id) {
		Produto produto = repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Id nao existe"));
		
		return new ProdutoDTO(produto);	
		
	}
	
	public Page<ProdutoDTO> buscaPaginada(Pageable pageable) {
        return repository.findAll(pageable).map(ProdutoDTO::new);
    }

    public ProdutoDTO create(ProdutoDTO produtoDto) {
    	Produto entity = new Produto();
		copyDtoToEntity(produtoDto, entity);
		entity = repository.save(entity);
		return new ProdutoDTO(entity);
    
    }
    
    public ProdutoDTO update(Long id, ProdutoDTO produtoDto) {
    	try {
    		Produto entity = repository.getReferenceById(id);
    		copyDtoToEntity(produtoDto, entity);
    		entity = repository.save(entity);
    		return new ProdutoDTO(entity);
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
    
    private ProdutoDTO copyEntitytoDto(Produto produto) {
    	ProdutoDTO dto = new ProdutoDTO(produto);
		return dto;
	}
}
