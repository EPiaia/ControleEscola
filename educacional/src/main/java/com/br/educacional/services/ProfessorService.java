package com.br.educacional.services;

import com.br.educacional.domains.Pessoa;
import com.br.educacional.domains.Professor;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Piaia
 */
@Stateless
@LocalBean
public class ProfessorService extends BaseService<Professor> {

    public void deletar(Professor professor) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM PROFESSOR WHERE ID = ").append(professor.getId());
        executeNativeUpdate(sql.toString());
    }

    public List<Professor> pesquisarProfessores(Integer codigo) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM PROFESSOR");
        if (codigo != null) {
            sql.append(" WHERE ID = ").append(codigo);
        }
        return executeNativeQuery(Professor.class, sql.toString());
    }

    public Professor getProfessorPelaPessoa(Pessoa pessoa) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM PROFESSOR WHERE PESSOA_ID = ").append(pessoa.getId());
        List<Professor> retorno = executeNativeQuery(Professor.class, sql.toString());
        if (retorno != null || !retorno.isEmpty()) {
            return retorno.get(0);
        } else {
            return null;
        }
    }

}
