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
        sql.append("SELECT * FROM PESQUISAR_ALUNO");
        if (codigo != null) {
            sql.append("(").append(codigo).append(")");
        }
        return executeNativeQuery(Aluno.class, sql.toString());
    }

    public List<Aluno> pesquisarAlunos(Integer codigo, String nome) {
        if (codigo != null) {
            return pesquisarAlunos(codigo);
        }
        StringBuilder sql = new StringBuilder();
        if (nome != null && !nome.isEmpty()) {
            sql.append("SELECT A.*, P.* FROM ALUNO A");
            sql.append(" INNER JOIN PESSOA P ON P.ID = A.PESSOA_ID");
            sql.append(" WHERE UPPER(P.NOME) LIKE UPPER('%").append(nome).append("%')");
        } else {
            sql.append("SELECT * FROM ALUNO");
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
