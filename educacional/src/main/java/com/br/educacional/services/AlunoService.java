package com.br.educacional.services;

import com.br.educacional.domains.Aluno;
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
public class AlunoService extends BaseService<Aluno> {

    public void deletar(Aluno aluno) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM ALUNO WHERE ID = ").append(aluno.getId());
        executeNativeUpdate(sql.toString());
    }

    public List<Aluno> pesquisarAlunos(Integer codigo) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM ALUNO");
        if (codigo != null) {
            sql.append(" WHERE ID = ").append(codigo);
        }
        return executeNativeQuery(Aluno.class, sql.toString());
    }

    public Aluno getAlunoPelaPessoa(Pessoa pessoa) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM ALUNO WHERE PESSOA_ID = ").append(pessoa.getId());
        List<Aluno> retorno = executeNativeQuery(Aluno.class, sql.toString());
        if (retorno != null || !retorno.isEmpty()) {
            return retorno.get(0);
        } else {
            return null;
        }
    }

}
