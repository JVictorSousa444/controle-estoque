package gmail.davidsousalves.repositories.custom;

import gmail.davidsousalves.converter.TransformerTupleToDTO;
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

    @Override
    public Page<RelatorioDTO> buscarContasAPagar(RelatorioFiltroDTO filtro, Pageable pageable) {
        StringBuilder sql = new StringBuilder();
        String consultaBase = criarConsultaBasicaContasAPagar(filtro);
        sql.append(consultaBase);

        List<RelatorioDTO> result = getResult(sql.toString(), filtro, pageable);

        return new PageImpl<>(result, pageable, getResultCount(consultaBase, filtro));
    }

    @Override
    public List<RelatorioDTO> buscarTotalEntradaESaidaPorMes(RelatorioFiltroDTO filtro) {
        StringBuilder sql = new StringBuilder();
        sql.append(" ");

        sql.append(" WITH entrada_totais AS ( ");
        sql.append(" SELECT ");
        sql.append(" DATE_TRUNC('month', e.data_entrada) AS mes, ");
        sql.append(" SUM(ei.quantidade * ei.valor_unitario) AS valor_total_entrada ");
        sql.append(" FROM ");
        sql.append(" public.entrada e ");
        sql.append(" LEFT JOIN        public.entrada_item ei ON e.id = ei.entrada_id ");
        sql.append(" WHERE ");
        sql.append(" EXTRACT(YEAR FROM e.data_entrada) = EXTRACT(YEAR FROM CURRENT_DATE) ");
        sql.append(" GROUP BY  DATE_TRUNC('month', e.data_entrada) ");
        sql.append(" ),  saida_totais AS ( ");
        sql.append(" SELECT ");
        sql.append(" DATE_TRUNC('month', s.data) AS mes, ");
        sql.append(" SUM(si.quantidade * si.valor_unitario) AS valor_total_saida ");
        sql.append(" FROM ");
        sql.append(" public.saida s ");
        sql.append(" LEFT JOIN        public.saida_item si ON s.id = si.saida_id ");
        sql.append(" WHERE ");
        sql.append(" EXTRACT(YEAR FROM s.data) = EXTRACT(YEAR FROM CURRENT_DATE) ");
        sql.append(" GROUP BY  DATE_TRUNC('month', s.data) ");
        sql.append(" ) ");
        sql.append(" SELECT ");
        sql.append(" COALESCE(TO_CHAR(e.mes, 'YYYY-MM'), TO_CHAR(s.mes, 'YYYY-MM')) AS mes, ");
        sql.append(" COALESCE(e.valor_total_entrada, 0) AS valorTotalEntrada, ");
        sql.append(" COALESCE(s.valor_total_saida, 0) AS valorTotalSaida ");
        sql.append(" FROM (SELECT mes FROM entrada_totais   UNION  SELECT mes FROM saida_totais) AS meses ");
        sql.append(" LEFT JOIN entrada_totais e ON meses.mes = e.mes ");
        sql.append(" LEFT JOIN saida_totais s ON meses.mes = s.mes ");
        sql.append(" ORDER BY mes; ");

        return getResult(sql.toString(), filtro, null);
    }

    @Override
    public List<RelatorioDTO> rankQuantidadeDeProdutosVendidosNoMes(RelatorioFiltroDTO filtro) {
        StringBuilder sql = new StringBuilder();
        sql.append(" ");

        sql.append(" WITH vendasPorMes AS ( ");
        sql.append(" SELECT ");
        sql.append(" p.id AS produto_id, ");
        sql.append(" p.nome AS produto_nome, ");
        sql.append(" DATE_TRUNC('month', s.data) AS mes, ");
        sql.append(" SUM(si.quantidade) AS total_quantidade_saida ");
        sql.append(" FROM public.produto p ");
        sql.append(" JOIN public.saida_item si ON p.id = si.produto_id ");
        sql.append(" JOIN public.saida s ON si.saida_id = s.id ");
        sql.append(" WHERE ");
        sql.append(" EXTRACT(YEAR FROM s.data) = EXTRACT(YEAR FROM CURRENT_DATE) ");
        sql.append(" GROUP BY        p.id, p.nome, DATE_TRUNC('month', s.data) ");
        sql.append(" ) ");
        sql.append(" SELECT ");
        sql.append(" TO_CHAR(mes, 'YYYY-MM') mes, ");
        sql.append(" produto_id, ");
        sql.append(" produto_nome produto, ");
        sql.append(" total_quantidade_saida quantidade ");
        sql.append(" FROM ( ");
        sql.append(" SELECT ");
        sql.append(" mes, ");
        sql.append(" produto_id, ");
        sql.append(" produto_nome, ");
        sql.append(" total_quantidade_saida, ");
        sql.append(" ROW_NUMBER() OVER (PARTITION BY mes ORDER BY total_quantidade_saida DESC) AS rank ");
        sql.append(" FROM vendasPorMes ");
        sql.append(" ) t ");
        sql.append("  WHERE ");
        sql.append(" rank <= 5 ");
        sql.append(" ORDER BY mes, rank; ");

        return getResult(sql.toString(), filtro, null);
    }

    public List<RelatorioDTO>  valorTotalDeEntradaESaidaNoMes(RelatorioFiltroDTO filtro) {
        StringBuilder sql = new StringBuilder();
        sql.append(" ");

        sql.append(" with saidas as ( ");
        sql.append(" select SUM(si.quantidade * si.valor_unitario) as valor ");
        sql.append(" from saida s ");
        sql.append(" join saida_item si on si.saida_id = s.id ");
        sql.append(" where ");
        sql.append(" DATE_TRUNC('month', s.data) = DATE_TRUNC('month', CURRENT_DATE ) ");
        sql.append("   ), entradas as ( ");
        sql.append(" SELECT SUM(ei.quantidade * ei.valor_unitario) as valor ");
        sql.append(" FROM public.entrada e ");
        sql.append(" join entrada_item ei on ei.entrada_id = e.id ");
        sql.append(" where DATE_TRUNC('month', data_entrada) = DATE_TRUNC('month', CURRENT_DATE) ");
        sql.append(" ) ");
        sql.append(" select ");
        sql.append(" s.valor as valorTotalSaida, ");
        sql.append(" e.valor as valorTotalEntrada ");
        sql.append(" from ");
        sql.append(" saidas s, entradas e ");

        return getResult(sql.toString(), filtro, null);
    }

    public Integer quantidadeContasAReceberVencidas(RelatorioFiltroDTO filtro) {
        StringBuilder sql = new StringBuilder();
        sql.append(" ");

        sql.append(" SELECT ");
        sql.append(" COUNT(*) AS quantidade_saidas_prestes_a_vencer ");
        sql.append(" FROM ");
        sql.append(" public.saida ");
        sql.append("  WHERE ");
        sql.append(" data_vencimento IS NOT NULL ");

        if (filtro.getDiasAVencer() != null && !filtro.getDiasAVencer().isBlank()) {
            sql.append(" AND data_vencimento BETWEEN CURRENT_DATE AND CURRENT_DATE + INTERVAL '" + filtro.getDiasAVencer() + " days' ");
        }

        sql.append(" and data_pagamento is null; ");

        Query query = entityManager.createNativeQuery(sql.toString());

        Object resultado = query.getSingleResult();
        return resultado != null ? ((Number) resultado).intValue() : 0 ;
    }

    public Page<RelatorioDTO> produtos(RelatorioFiltroDTO filtro, Pageable pageable) {
        StringBuilder sql = new StringBuilder();
        String consultaBase = criarConsultaBasicaProdutos(filtro, false);
        sql.append(consultaBase);

        List<RelatorioDTO> result = getResult(sql.toString(), filtro, pageable);

        return new PageImpl<>(result, pageable, getResultCount(consultaBase, filtro));
    }

    public Page<RelatorioDTO> produtosComMenosQuantidadeEstoque(RelatorioFiltroDTO filtro, Pageable pageable) {
        StringBuilder sql = new StringBuilder();
        String consultaBase = criarConsultaBasicaProdutos(filtro, true);
        sql.append(consultaBase);

        List<RelatorioDTO> result = getResult(sql.toString(), filtro, pageable);

        return new PageImpl<>(result, pageable, getResultCount(consultaBase, filtro));
    }

    private String criarConsultaBasicaProdutos(RelatorioFiltroDTO filtro, boolean isOrderByQtd) {
        StringBuilder sql = new StringBuilder();
        sql.append(" ");
        sql.append(" SELECT ");
        sql.append(" p.id AS id, ");
        sql.append(" p.nome AS produto, ");
        sql.append(" COALESCE(ei.total_quantidade_entrada, 0) AS qtdEntrada, ");
        sql.append(" COALESCE(si.total_quantidade_saida, 0) AS qtdSaida, ");
        sql.append(" COALESCE(ei.media_valor_entrada, 0) AS precoMedioEntrada, ");
        sql.append(" COALESCE(si.media_valor_saida, 0) AS precoMedioSaida, ");
        sql.append(" p.quantidade AS qtdAtual ");
        sql.append(" FROM        public.produto p ");
        sql.append(" LEFT JOIN ");
        sql.append(" (SELECT ");
        sql.append(" entrada_item.produto_id, ");
        sql.append(" SUM(entrada_item.quantidade) AS total_quantidade_entrada, ");
        sql.append(" AVG(entrada_item.valor_unitario) AS media_valor_entrada ");
        sql.append(" FROM ");
        sql.append(" public.entrada e ");
        sql.append(" join public.entrada_item on e.id = entrada_id ");
        sql.append("WHERE 1 = 1 ");
        if (filtro.getDataInicio() != null && filtro.getDataFim() != null) {
            SqlUtils.addParam(sql, filtro.getDataInicio(), " AND e.data_entrada between :dataInicio ");
            SqlUtils.addParam(sql, filtro.getDataFim(), " AND :dataFim ");
        } else if (filtro.getDataInicio() != null ) {
            SqlUtils.addParam(sql, filtro.getDataInicio(), " AND e.data_entrada >= :dataInicio ");
        } else if (filtro.getDataFim() != null) {
            SqlUtils.addParam(sql, filtro.getDataFim(), " AND e.data_entrada <= :dataFim ");
        }
        sql.append(" GROUP BY        entrada_item.produto_id ) ei ON p.id = ei.produto_id ");

        sql.append(" LEFT JOIN ");
        sql.append(" (SELECT ");
        sql.append(" saida_item.produto_id, ");
        sql.append(" SUM(saida_item.quantidade) AS total_quantidade_saida, ");
        sql.append(" AVG(saida_item.valor_unitario) AS media_valor_saida ");
        sql.append(" FROM saida s");
        sql.append(" join public.saida_item on s.id = saida_id ");
        sql.append("WHERE 1 = 1 ");
        if (filtro.getDataInicio() != null && filtro.getDataFim() != null) {
            SqlUtils.addParam(sql, filtro.getDataInicio(), " AND s.data between :dataInicio ");
            SqlUtils.addParam(sql, filtro.getDataFim(), " AND :dataFim ");
        } else if (filtro.getDataInicio() != null ) {
            SqlUtils.addParam(sql, filtro.getDataInicio(), " AND s.data >= :dataInicio ");
        } else if (filtro.getDataFim() != null) {
            SqlUtils.addParam(sql, filtro.getDataFim(), " AND s.data <= :dataFim ");
        }

        sql.append(" GROUP BY        saida_item.produto_id ) si ON p.id = si.produto_id ");

        sql.append("WHERE 1 = 1 ");
        SqlUtils.addParam(sql, filtro.getProduto(), " AND p.id = :idProduto ");
        if (isOrderByQtd) {
            sql.append(" ORDER BY        p.quantidade ASC ");
        } else {
            sql.append(" ORDER BY        p.nome  ");
        }

        return sql.toString();
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

    private String criarConsultaBasicaContasAPagar(RelatorioFiltroDTO filtro) {
        StringBuilder sql = new StringBuilder();
        sql.append(" ");
        sql.append(" select ");
        sql.append(" e.id as id, ");
        sql.append(" coalesce(SUM(ei.quantidade * ei.valor_unitario), 0) as valorTotal, ");
        sql.append(" e.data_entrada as data ");
        sql.append(" from ");
        sql.append(" public.entrada e ");
        sql.append(" left join public.entrada_item ei on	e.id = ei.entrada_id ");
        sql.append("WHERE 1 = 1 ");

//        if (filtro.getTipoRelatorio() != null) {
//            switch (filtro.getTipoRelatorio()) {
//                case TipoRelatorio.A_RECEBER:
//                    sql.append(" AND s.data_pagamento IS NULL AND (s.data_vencimento IS NULL OR CURRENT_DATE < s.data_vencimento)");
//                    break;
//                case TipoRelatorio.RECEBIDO:
//                    sql.append(" AND s.data_pagamento IS NOT NULL ");
//                    break;
//                case TipoRelatorio.VENCIDO:
//                    sql.append(" AND s.data_vencimento IS NOT NULL AND CURRENT_DATE > s.data_vencimento ");
//                    break;
//            }
//        }

        SqlUtils.addParam(sql, filtro.getFornecedor(), " AND f.id = :idFornecedor ");

        if (filtro.getDataInicio() != null && filtro.getDataFim() != null) {
            SqlUtils.addParam(sql, filtro.getDataInicio(), " AND e.data_entrada between :dataInicio ");
            SqlUtils.addParam(sql, filtro.getDataFim(), " AND :dataFim ");
        } else if (filtro.getDataInicio() != null ) {
            SqlUtils.addParam(sql, filtro.getDataInicio(), " AND e.data_entrada >= :dataInicio ");
        } else if (filtro.getDataFim() != null) {
            SqlUtils.addParam(sql, filtro.getDataFim(), " AND e.data_entrada <= :dataFim ");
        }

//        if (filtro.getDataVencimentoInicio() != null && filtro.getDataVencimentoFim() != null) {
//            SqlUtils.addParam(sql, filtro.getDataVencimentoInicio(), " AND s.data_vencimento between :dataVencimentoInicio ");
//            SqlUtils.addParam(sql, filtro.getDataVencimentoFim(), " AND :dataVencimentoFim ");
//        } else if (filtro.getDataVencimentoInicio() != null ) {
//            SqlUtils.addParam(sql, filtro.getDataVencimentoInicio(), " AND s.data_vencimento >= :dataVencimentoInicio ");
//        } else if (filtro.getDataVencimentoFim() != null) {
//            SqlUtils.addParam(sql, filtro.getDataVencimentoFim(), " AND s.data_vencimento <= :dataVencimentoFim ");
//        }

        sql.append(" group by e.id, e.data_entrada ");
        sql.append(" order by e.data_entrada ");

        return sql.toString();
    }

    private List<RelatorioDTO> getResult(String sql, RelatorioFiltroDTO filtro, Pageable pageable) {
        Query query = entityManager.createNativeQuery(sql, Tuple.class);

        SqlUtils.setParam(query, filtro.getCliente(), "idCliente");
        SqlUtils.setParam(query, filtro.getFornecedor(), "idFornecedor");
        SqlUtils.setParam(query, filtro.getProduto(), "idProduto");
        SqlUtils.setParam(query, filtro.getDataInicio(), "dataInicio");
        SqlUtils.setParam(query, DataUtils.ajustarDataParaFimDoDia(filtro.getDataFim()), "dataFim");
        SqlUtils.setParam(query, filtro.getDataVencimentoInicio(), "dataVencimentoInicio");
        SqlUtils.setParam(query, DataUtils.ajustarDataParaFimDoDia(filtro.getDataVencimentoFim()), "dataVencimentoFim");

        if (pageable != null) {
            query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
            query.setMaxResults(pageable.getPageSize());
        }

        List<Tuple> resultList = query.getResultList();

        List<RelatorioDTO> relatorioDTOS = TransformerTupleToDTO.<RelatorioDTO>convertTupleToList(RelatorioDTO.class, resultList);
        return relatorioDTOS;
    }

    private Long getResultCount(String consulta, RelatorioFiltroDTO filtro) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(1) FROM (");
        sql.append(consulta);
        sql.append(") AS T");

        Query query = entityManager.createNativeQuery(sql.toString());

        SqlUtils.setParam(query, filtro.getCliente(), "idCliente");
        SqlUtils.setParam(query, filtro.getFornecedor(), "idFornecedor");
        SqlUtils.setParam(query, filtro.getProduto(), "idProduto");
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
