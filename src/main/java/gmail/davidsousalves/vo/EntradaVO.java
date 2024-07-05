package gmail.davidsousalves.vo;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Transient;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EntradaVO {

    private Long id;

    private Date dataEntrada;

    private Date dataPagamento;

    private Date dataVencimento;

    private FornecedorVO fornecedor;

    @Transient
    private List<EntradaItemVO> itens;
}
