package gmail.davidsousalves.repositories.custom;

import gmail.davidsousalves.dto.RelatorioDTO;
import gmail.davidsousalves.dto.RelatorioFiltroDTO;
import gmail.davidsousalves.dto.TipoRelatorio;
import gmail.davidsousalves.utils.DataUtils;
import gmail.davidsousalves.utils.SqlUtils;
import jakarta.persistence.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Date;

import java.util.List;

@Repository
public class RelatporioCustomRepositoryImpl implements RelatporioCustomRepository {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<RelatorioDTO> buscarContasAReceber(RelatorioFiltroDTO filtro, Pageable pageable) {
        StringBuilder sql = new StringBuilder();
        String consultaBase = criarConsultaBasicaContasAReceber(filtro);
        sql.append(consultaBase);

        List<RelatorioDTO> result = getResult(sql.toString(), filtro, pageable);

        return new PageImpl<>(result, pageable, getResultCount(consultaBase, filtro));
    }

    private String criarConsultaBasicaContasAReceber(RelatorioFiltroDTO filtro) {
        StringBuilder sql = new StringBuilder();
        sql.append(" ");
        sql.append(" SELECT ");
        sql.append(" s.id AS id,  ");
        sql.append(" c.nome AS cliente, ");
        sql.append(" COALESCE(SUM(si.quantidade * si.valor_unitario), 0) AS valorTotal, ");
        sql.append(" s.data,  ");
        sql.append(" s.data_pagamento as dataPagamento,  ");
        sql.append(" s.data_vencimento as dataVencimento,  ");
        sql.append(" CASE ");
        sql.append(" WHEN s.data_pagamento IS NOT NULL THEN 'Paga' ");
        sql.append(" WHEN s.data_vencimento IS NOT NULL AND CURRENT_DATE > s.data_vencimento THEN 'Em Atraso' ");
        sql.append(" ELSE 'Em Aberto' ");
        sql.append(" END AS situacao ");
        sql.append(" FROM ");
        sql.append(" public.saida s ");
        sql.append(" JOIN        public.cliente c ON s.cliente_id = c.id ");
        sql.append(" LEFT JOIN  public.saida_item si ON s.id = si.saida_id ");
        sql.append("WHERE 1 = 1 ");

        if (filtro.getTipoRelatorio() != null) {
            switch (filtro.getTipoRelatorio()) {
                case TipoRelatorio.A_RECEBER:
                    sql.append(" AND s.data_pagamento IS NULL AND (s.data_vencimento IS NULL OR CURRENT_DATE < s.data_vencimento)");
                    break;
                case TipoRelatorio.RECEBIDO:
                    sql.append(" AND s.data_pagamento IS NOT NULL ");
                    break;
                case TipoRelatorio.VENCIDO:
                    sql.append(" AND s.data_vencimento IS NOT NULL AND CURRENT_DATE > s.data_vencimento ");
                    break;
            }
        }

        SqlUtils.addParam(sql, filtro.getCliente(), " AND c.id = :idCliente ");

        if (filtro.getDataInicio() != null && filtro.getDataFim() != null) {
            SqlUtils.addParam(sql, filtro.getDataInicio(), " AND s.data between :dataInicio ");
            SqlUtils.addParam(sql, filtro.getDataFim(), " AND :dataFim ");
        } else if (filtro.getDataInicio() != null ) {
            SqlUtils.addParam(sql, filtro.getDataInicio(), " AND s.data >= :dataInicio ");
        } else if (filtro.getDataFim() != null) {
            SqlUtils.addParam(sql, filtro.getDataFim(), " AND s.data <= :dataFim ");
        }

        if (filtro.getDataVencimentoInicio() != null && filtro.getDataVencimentoFim() != null) {
            SqlUtils.addParam(sql, filtro.getDataVencimentoInicio(), " AND s.data_vencimento between :dataVencimentoInicio ");
            SqlUtils.addParam(sql, filtro.getDataVencimentoFim(), " AND :dataVencimentoFim ");
        } else if (filtro.getDataVencimentoInicio() != null ) {
            SqlUtils.addParam(sql, filtro.getDataVencimentoInicio(), " AND s.data_vencimento >= :dataVencimentoInicio ");
        } else if (filtro.getDataVencimentoFim() != null) {
            SqlUtils.addParam(sql, filtro.getDataVencimentoFim(), " AND s.data_vencimento <= :dataVencimentoFim ");
        }

        sql.append(" GROUP BY s.id, c.nome, s.data_pagamento, s.data_vencimento ");
        sql.append(" ORDER BY s.id ");

        return sql.toString();
    }

    private List<RelatorioDTO> getResult(String sql, RelatorioFiltroDTO filtro, Pageable pageable) {
        Query query = entityManager.createNativeQuery(sql, Tuple.class);

        SqlUtils.setParam(query, filtro.getCliente(), "idCliente");
        SqlUtils.setParam(query, filtro.getDataInicio(), "dataInicio");
        SqlUtils.setParam(query, DataUtils.ajustarDataParaFimDoDia(filtro.getDataFim()), "dataFim");
        SqlUtils.setParam(query, filtro.getDataVencimentoInicio(), "dataVencimentoInicio");
        SqlUtils.setParam(query, DataUtils.ajustarDataParaFimDoDia(filtro.getDataVencimentoFim()), "dataVencimentoFim");

        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<Tuple> resultList = query.getResultList();

        return resultList.stream()
                .map(this::convertTupleToRelatorioDTO)
                .toList();
    }

    private Long getResultCount(String consulta, RelatorioFiltroDTO filtro) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(1) FROM (");
        sql.append(consulta);
        sql.append(") AS T");

        Query query = entityManager.createNativeQuery(sql.toString());

        SqlUtils.setParam(query, filtro.getCliente(), "idCliente");
        SqlUtils.setParam(query, filtro.getDataInicio(), "dataInicio");
        SqlUtils.setParam(query, DataUtils.ajustarDataParaFimDoDia(filtro.getDataFim()), "dataFim");
        SqlUtils.setParam(query, filtro.getDataVencimentoInicio(), "dataVencimentoInicio");
        SqlUtils.setParam(query, DataUtils.ajustarDataParaFimDoDia(filtro.getDataVencimentoFim()), "dataVencimentoFim");
        Object resultado = query.getSingleResult();
        return resultado != null ? ((Number) resultado).longValue() : 0 ;
    }

    private RelatorioDTO convertTupleToRelatorioDTO(Tuple tuple) {
        RelatorioDTO dto = new RelatorioDTO();
        dto.setId(((Number) tuple.get("id")).longValue());
        dto.setCliente(tuple.get("cliente", String.class));
        dto.setData(tuple.get("data", Date.class));
        dto.setDataPagamento(tuple.get("dataPagamento", Date.class));
        dto.setDataVencimento(tuple.get("dataVencimento", Date.class));
        dto.setSituacao(tuple.get("situacao", String.class));
        dto.setValorTotal(tuple.get("valorTotal", Double.class));
        return dto;
    }
}
