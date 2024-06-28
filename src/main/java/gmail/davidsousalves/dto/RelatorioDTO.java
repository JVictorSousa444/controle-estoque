package gmail.davidsousalves.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RelatorioDTO {
    private Long id;
    private String cliente;
    private String fornecedor;
    private Date data;
    private Date dataVencimento;
    private Date dataPagamento;
    private String situacao;
    private Double valorTotal;
}
