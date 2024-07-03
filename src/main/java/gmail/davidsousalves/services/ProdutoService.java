package gmail.davidsousalves.services;

import java.util.List;
import java.util.Optional;
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
import io.micrometer.common.util.StringUtils;
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
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto com ID " + id + " não encontrado"));
        return new ProdutoDTO(produto);
    }
	
	public Page<ProdutoDTO> buscaPaginada(String nome, Pageable pageable) {
		if (StringUtils.isEmpty(nome)) {
        	return repository.findAll(pageable).map(ProdutoDTO::new);
		} else {
			return repository.findByNomeContainingIgnoreCase(nome, pageable).map(ProdutoDTO::new);
		}
    }

    public ProdutoDTO create(ProdutoDTO produtoDto) {
    	Produto entity = new Produto();
		copyDtoToEntity(produtoDto, entity);
		entity.setQuantidade(0);
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

	public void atualizarQuantidade(Long id, Integer quantidade) {
		repository.atualizarQuantidade(id, quantidade);
	}

	public void atualizarQuantidadeRemover(Long id, Integer quantidade) {
		Optional<Produto> produto = repository.findById(id);

		if (produto.isPresent()) {
			if (produto.get().getQuantidade().compareTo(quantidade) < 0) {
				throw new RuntimeException(produto.get().getNome() +
						" com quantidade insuficiente para saida. Quantidade solicitada: " + quantidade +
						". Quantidade em estoque: " + produto.get().getQuantidade());
			}
			repository.atualizarQuantidadeRemover(id, quantidade);
		}
	}

    private void copyDtoToEntity(ProdutoDTO dto, Produto entity) {
		entity.setNome(dto.nome());
		entity.setDescricao(dto.descricao());
		entity.setCodigo(dto.codigo());
		entity.setGrupoProduto(dto.grupoProduto());
		entity.setTipoUnidade(dto.tipoUnidade());
		entity.setFabricante(dto.fabricante());
		entity.setLucroSugerido(dto.lucroSugerido());
	}
    
    private ProdutoDTO copyEntitytoDto(Produto produto) {
    	ProdutoDTO dto = new ProdutoDTO(produto);
		return dto;
	}
}
