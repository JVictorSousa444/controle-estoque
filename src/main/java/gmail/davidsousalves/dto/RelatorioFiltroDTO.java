package gmail.davidsousalves.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class RelatorioFiltroDTO {

    private Long cliente;
    private Long produto;
    private Long fornecedor;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataInicio;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataFim;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataVencimentoInicio;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataVencimentoFim;

    private TipoRelatorio tipoRelatorio;

    private String diasAVencer;
}
