package gmail.davidsousalves.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import gmail.davidsousalves.dto.ClienteDTO;
import gmail.davidsousalves.model.*;
import gmail.davidsousalves.utils.DataUtils;
import gmail.davidsousalves.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gmail.davidsousalves.dto.ClienteDTO;
import gmail.davidsousalves.dto.SaidaDTO;
import gmail.davidsousalves.exceptions.DatabaseException;
import gmail.davidsousalves.exceptions.ResourceNotFoundException;
import gmail.davidsousalves.model.Cliente;
import gmail.davidsousalves.model.Saida;
import gmail.davidsousalves.model.SaidaItem;
import gmail.davidsousalves.repositories.SaidaRepository;
import gmail.davidsousalves.vo.ClienteVO;
import gmail.davidsousalves.vo.ProdutoVO;
import gmail.davidsousalves.vo.SaidaItemVO;
import gmail.davidsousalves.vo.SaidaVO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaidaService {

	@Autowired
	private SaidaRepository repository;

	@Autowired
	private SaidaItemService saidaItemService;

	@Autowired
	private ClienteService clienteService;

	public List<SaidaDTO> findAll() {
        List<Saida> saidas = repository.findAll();
        return saidas.stream()
                .map(saida -> copyEntitytoDto(saida))
                .collect(Collectors.toList());
    }
	
	public Page<SaidaVO> buscaPaginada(Pageable pageable) {
		Page<Saida> lista = repository.findAll(pageable);
		List<SaidaVO> retorno = new ArrayList<>();

		if (lista != null) {
			for(Saida saida : lista.getContent()) {
				List<SaidaItem> itens = saidaItemService.findByEntityId(saida.getId());
				ClienteDTO cliente = clienteService.findById(saida.getCliente().getId());
				SaidaVO entradaVO = SaidaVO.builder()
						.data(saida.getData())
						.dataPagamento(saida.getDataPagamento())
						.dataVencimento(saida.getDataVencimento())
						.id(saida.getId())
						.itens(converterSaidaItem(itens))
						.cliente(ClienteVO.builder().id(cliente.id()).nome(cliente.nome()).build())
						.build();
				retorno.add(entradaVO);
			}
		}

		return new PageImpl<SaidaVO>(retorno, pageable, lista.getTotalElements());
    }

	public SaidaDTO findById(Long id) {
		Saida saida = repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Id nao existe"));
		
		return new SaidaDTO(saida);	
		
	}

	@Transactional
	public SaidaDTO create(SaidaDTO saidaDto) {
		Saida entity = new Saida();
		copyDtoToEntity(saidaDto, entity);
		entity = repository.save(entity);
		for (SaidaItem item : saidaDto.itens()) {
			item.setSaida(entity);
		}
		saidaItemService.create(saidaDto.itens());
		return new SaidaDTO(entity);
	}
	
	public SaidaDTO update(Long id, SaidaDTO saidaDto) {
		try {
			Saida entity = repository.getReferenceById(id);
			copyDtoToEntity(saidaDto, entity);
			entity = repository.save(entity);
			for (SaidaItem item : saidaDto.itens()) {
				item.setSaida(entity);
			}
			saidaItemService.create(saidaDto.itens());
			return new SaidaDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
		
	}

	@Transactional
	public void deleteById(Long id) {
		if (!repository.existsById(id)) {
    		throw new ResourceNotFoundException("Recurso não encontrado");
    	}
    	try {
			List<SaidaItem> itens = saidaItemService.findByEntityId(id);
			for(SaidaItem item : itens) {
				saidaItemService.deleteById(item.getId());
			}
			repository.deleteById(id);
    	}
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }	
    	
	}
    
    private void copyDtoToEntity(SaidaDTO dto, Saida entity) {
		entity.setData(dto.data());
		entity.setDataVencimento(dto.dataVencimento());
		entity.setDataPagamento(dto.dataPagamento());
		Cliente cliente = new Cliente();
		cliente.setId(dto.cliente().getId());
		entity.setCliente(cliente);

	}
    
    private SaidaDTO copyEntitytoDto(Saida saida) {
    	SaidaDTO dto = new SaidaDTO(saida);
		return dto;
	}

	private List<SaidaItemVO> converterSaidaItem(List<SaidaItem> itens) {
		List<SaidaItemVO> retorno = new ArrayList<>();
		for (SaidaItem item : itens) {
			ProdutoVO produtoVO = ProdutoVO.builder()
					.id(item.getProduto().getId())
					.nome(item.getProduto().getNome())
					.quantidade(item.getProduto().getQuantidade())
					.build();

			SaidaItemVO saidaItemVO = SaidaItemVO.builder()
					.id(item.getId())
					.produto(produtoVO)
					.valorUnitario(item.getValorUnitario())
					.quantidade(item.getQuantidade())
					.valorTotal(item.getValorUnitario() * item.getQuantidade())
					.build();
			retorno.add(saidaItemVO);
		}
		return retorno;
	}
}
