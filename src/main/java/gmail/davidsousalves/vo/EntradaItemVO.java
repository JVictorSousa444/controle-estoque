package gmail.davidsousalves.vo;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EntradaItemVO {

    private Long id;

    private ProdutoVO produto;

    private Long quantidade;

    private Double valorUnitario;

    private Double valorTotal;
}
