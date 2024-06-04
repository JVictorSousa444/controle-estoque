package gmail.davidsousalves.vo;

import gmail.davidsousalves.model.Produto;
import gmail.davidsousalves.model.Saida;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaidaItemVO {

    private Long id;

    private ProdutoVO produto;

    private Long quantidade;

    private Double valorUnitario;

    private Double valorTotal;
}
