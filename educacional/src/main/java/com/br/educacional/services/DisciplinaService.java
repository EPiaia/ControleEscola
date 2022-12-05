package com.br.educacional.services;

import com.br.educacional.domains.Disciplina;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Piaia
 */
@Stateless
@LocalBean
public class DisciplinaService extends BaseService<Disciplina> {

    public List<Disciplina> getDisciplinas(Integer codigo, String query) {
        String sql = "SELECT * FROM DISCIPLINA";
        if (codigo != null) {
            sql += " WHERE ID = " + codigo;
        } else if (query != null && !query.isEmpty()) {
            sql += " WHERE UPPER(DESCRICAO) LIKE UPPER('%" + query + "%')";
        }
        return executeNativeQuery(Disciplina.class, sql);
    }

    public Disciplina getDisciplinaPorCodigo(Integer codDisc) {
        List<Disciplina> disciplinas = getDisciplinas(codDisc, null);
        if (disciplinas != null && !disciplinas.isEmpty()) {
            return disciplinas.get(0);
        }
        return null;
    }

    public void inserirDisciplina(Disciplina disciplina) {
        String sql = "INSERT INTO DISCIPLINA (ID, DESCRICAO, CARGA_HORARIA, PROFESSOR_ID) VALUES";
        sql += "(GEN_ID(SEQ_DISCIPLINA, 1), '" + disciplina.getDescricao() + "', " + disciplina.getCargaHoraria()
                + ", " + disciplina.getProfessor().getId() + ")";
        executeNativeUpdate(sql);
    }

    public void atualizarDisciplina(Disciplina disciplina) {
        String sql = "UPDATE DISCIPLINA SET DESCRICAO = '" + disciplina.getDescricao() + "', CARGA_HORARIA = " + disciplina.getCargaHoraria()
                + ", PROFESSOR_ID = " + disciplina.getProfessor().getId() + " WHERE ID = " + disciplina.getId();
        executeNativeUpdate(sql);
    }

    public void deletarDisciplina(Disciplina disciplina) {
        String sql = "DELETE FROM DISCIPLINA WHERE ID = " + disciplina.getId();
        executeNativeUpdate(sql);
    }
}
