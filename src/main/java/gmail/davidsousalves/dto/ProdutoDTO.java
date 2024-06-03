package gmail.davidsousalves.dto;

import gmail.davidsousalves.model.Fabricante;
import gmail.davidsousalves.model.Fornecedor;
import gmail.davidsousalves.model.GrupoProduto;
import gmail.davidsousalves.model.Produto;
import gmail.davidsousalves.model.Unidade;


public record ProdutoDTO(Long id, String nome, String descricao, Long codigo, GrupoProduto grupoProduto, Unidade tipoUnidade,
		Fabricante fabricante, Double lucroSugerido, Fornecedor fornecedor) {

	public ProdutoDTO(Produto entity) {
        this(entity.getId(), entity.getNome(), entity.getDescricao(), entity.getCodigo(), entity.getGrupoproduto(), entity.getTipoUnidade(),
        		entity.getFabricante(), entity.getLucroSugerido(), entity.getFornecedor());
    }	
}
