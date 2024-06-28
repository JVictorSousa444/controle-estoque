package gmail.davidsousalves.utils;


import jakarta.persistence.Query;

import java.util.Date;

public class SqlUtils {

    public static void addParam(StringBuilder sql, Long valor, String propriedade) {
        if (valor != null) {
            sql.append(propriedade);
        }
    }

    public static void addParam(StringBuilder sql, Date valor, String propriedade) {
        if (valor != null) {
            sql.append(propriedade);
        }
    }

    public static void addParam(StringBuilder sql, String valor, String propriedade) {
        if (valor != null && !valor.isBlank()) {
            sql.append(propriedade);
        }
    }

    public static void setParam(Query query, Long valor, String propriedade) {
        if (valor != null) {
            query.setParameter(propriedade, valor);
        }
    }

    public static void setParam(Query query, Date valor, String propriedade) {
        if (valor != null) {
            query.setParameter(propriedade, valor);
        }
    }

    public static void setParam(Query query, String valor, String propriedade) {
        if (valor != null && !valor.isBlank()) {
            query.setParameter(propriedade, valor);
        }
    }
}
