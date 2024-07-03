package gmail.davidsousalves.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import gmail.davidsousalves.model.EntradaItem;
import gmail.davidsousalves.utils.DataUtils;
import gmail.davidsousalves.vo.EntradaItemVO;
import gmail.davidsousalves.vo.EntradaVO;
import gmail.davidsousalves.vo.ProdutoVO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import gmail.davidsousalves.dto.EntradaDTO;
import gmail.davidsousalves.exceptions.DatabaseException;
import gmail.davidsousalves.exceptions.ResourceNotFoundException;
import gmail.davidsousalves.model.Entrada;
import gmail.davidsousalves.model.EntradaItem;
import gmail.davidsousalves.repositories.EntradaRepository;
import gmail.davidsousalves.utils.DataUtils;
import gmail.davidsousalves.vo.EntradaItemVO;
import gmail.davidsousalves.vo.EntradaVO;
import gmail.davidsousalves.vo.ProdutoVO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class EntradaService {

	@Autowired
	private EntradaRepository repository;

	@Autowired
	private EntradaItemService entradaItemService;

	public List<EntradaDTO> findAll() {
		List<Entrada> entradas = repository.findAll();
		return entradas.stream().map(entrada -> copyEntitytoDto(entrada)).collect(Collectors.toList());
	}

	public Page<EntradaVO> buscaPaginada(Pageable pageable) {
		Page<Entrada> lista = repository.findAll(pageable);
		List<EntradaVO> retorno = new ArrayList<>();

		if (lista != null) {
			for(Entrada entrada : lista.getContent()) {
				List<EntradaItem> itens = entradaItemService.findByEntityId(entrada.getId());
				EntradaVO entradaVO = EntradaVO.builder()
						.dataEntrada(DataUtils.converterLocalDateTimeParaDate(entrada.getDataEntrada()))
						.id(entrada.getId())
						.itens(converterEntradaItem(itens))
						.build();
				retorno.add(entradaVO);
			}
		}

        return new PageImpl<EntradaVO>(retorno, pageable, lista.getTotalElements());
	}

	public EntradaDTO findById(Long id) {
		Entrada entrada = repository.findById(id).orElseThrow
				(() -> new ResourceNotFoundException("Id: " + id + " nao existe"));

		return new EntradaDTO(entrada);

	}

	@Transactional
	public EntradaDTO create(EntradaDTO entradaDto) {
		Entrada entity = new Entrada();
		copyDtoToEntity(entradaDto, entity);
		entity = repository.save(entity);
		for (EntradaItem item : entradaDto.itens()) {
			item.setEntrada(entity);
		}
		entradaItemService.create(entradaDto.itens());
		return new EntradaDTO(entity);
	}

	@Transactional
	public EntradaDTO update(Long id, EntradaDTO entradaDto) {
		try {
			Entrada entity = repository.getReferenceById(id);
			copyDtoToEntity(entradaDto, entity);
			entity = repository.save(entity);
			for (EntradaItem item : entradaDto.itens()) {
				item.setEntrada(entity);
			}
			entradaItemService.create(entradaDto.itens());
			return new EntradaDTO(entity);

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
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Falha de integridade referencial");

		}
	}

	private void copyDtoToEntity(EntradaDTO dto, Entrada entity) {
		entity.setDataEntrada(dto.dataEntrada());

	}

	private EntradaDTO copyEntitytoDto(Entrada entrada) {
		EntradaDTO dto = new EntradaDTO(entrada);
		return dto;
	}

	private List<EntradaItemVO> converterEntradaItem(List<EntradaItem> itens) {
		List<EntradaItemVO> retorno = new ArrayList<>();
		for (EntradaItem item : itens) {
			ProdutoVO produtoVO = ProdutoVO.builder()
					.id(item.getProduto().getId())
					.nome(item.getProduto().getNome())
					.quantidade(item.getProduto().getQuantidade())
					.build();
			EntradaItemVO entradaItemVO = EntradaItemVO.builder()
					.id(item.getId())
					.produto(produtoVO)
					.valorUnitario(item.getValorUnitario())
					.quantidade(item.getQuantidade())
					.valorTotal(item.getValorUnitario() * item.getQuantidade())
					.build();
			retorno.add(entradaItemVO);
		}
		return retorno;
	}
}
