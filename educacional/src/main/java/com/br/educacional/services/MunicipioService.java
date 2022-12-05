package com.br.educacional.services;

import com.br.educacional.domains.Municipio;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Piaia
 */
@Stateless
@LocalBean
public class MunicipioService extends BaseService<Municipio> {

    public Municipio getCidadePorCodigo(Integer codigo) {
        String sql = "SELECT * FROM MUNICIPIO M WHERE M.ID = " + codigo;
        List<Municipio> retorno = executeNativeQuery(Municipio.class, sql);
        if (retorno != null & !retorno.isEmpty()) {
            return retorno.get(0);
        } else {
            return null;
        }
    }

    public List<Municipio> getCidades(String query) {
        String sql = "SELECT * FROM PESQUISAR_CIDADE('" + query + "')";
        return executeNativeQuery(Municipio.class, sql);
    }
}
