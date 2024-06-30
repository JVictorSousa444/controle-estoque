package gmail.davidsousalves.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.util.Date;

@Data
@AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RelatorioDTO {
    private Long id;
    private String cliente;
    private String fornecedor;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date data;
    private Date dataVencimento;
    private Date dataPagamento;
    private String situacao;
    private Double valorTotal;
    private String mes;
    private Double valorTotalEntrada;
    private Double valorTotalSaida;
    private String produto;
    private Long quantidade;
    private Long qtdAtual;
    private Long qtdEntrada;
    private Long qtdSaida;
    private Double precoMedioEntrada;
    private Double precoMedioSaida;

}
