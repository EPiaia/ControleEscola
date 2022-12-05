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

    public List<Professor> pesquisarProfessores(Integer codigo, String nome) {
        StringBuilder sql = new StringBuilder();
        if (codigo != null) {
            sql.append("SELECT * FROM PESQUISAR_PROFESSOR").append("(").append(codigo).append(")");
        } else {
            sql.append("SELECT * FROM PROFESSOR ");
            if (nome != null && !nome.isEmpty()) {
                sql.append("PF INNER JOIN PESSOA P ON P.ID = PF.PESSOA_ID WHERE UPPER(P.NOME) LIKE UPPER('%").append(nome).append("%')");
            }
        }
        return executeNativeQuery(Professor.class, sql.toString());
    }

    public Professor getProfessorPeloCodigo(Integer codigo) {
        List<Professor> professores = pesquisarProfessores(codigo, null);
        if (professores != null && !professores.isEmpty()) {
            return professores.get(0);
        }
        return null;
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
