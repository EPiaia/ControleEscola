package com.br.educacional.services;

import com.br.educacional.domains.Pessoa;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Piaia
 */
@Stateless
@LocalBean
public class PessoaService extends BaseService<Pessoa> {

    public Pessoa getPessoaPeloCpf(String cpf) {
        String sql = "SELECT * FROM PESSOA P WHERE P.CPF = '" + cpf + "'";
        List<Pessoa> retorno = executeNativeQuery(Pessoa.class, sql);
        if (retorno != null && !retorno.isEmpty()) {
            return retorno.get(0);
        } else {
            return null;
        }
    }

}
