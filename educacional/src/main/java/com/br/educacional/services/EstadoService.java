package com.br.educacional.services;

import com.br.educacional.domains.Estado;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Piaia
 */
@Stateless
@LocalBean
public class EstadoService extends BaseService<Estado> {

    public Estado getEstadoPorUf(String uf) {
        String sql = "SELECT * FROM ESTADO E WHERE E.UF = '" + uf + "'";
        List<Estado> retorno = executeNativeQuery(Estado.class, sql);
        if (retorno != null & !retorno.isEmpty()) {
            return retorno.get(0);
        } else {
            return null;
        }
    }
}
