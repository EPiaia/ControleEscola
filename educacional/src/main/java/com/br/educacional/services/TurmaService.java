package com.br.educacional.services;

import com.br.educacional.domains.Turma;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Piaia
 */
@Stateless
@LocalBean
public class TurmaService extends BaseService<Turma> {

    public List<Turma> getTurmas(Integer codigo) {
        String sql = "SELECT * FROM TURMA";
        if (codigo != null) {
            sql += " WHERE ID = " + codigo;
        }
        return executeNativeQuery(Turma.class, sql);
    }

    public void inserirTurma(Turma turma) {
        String sql = "INSERT INTO TURMA (ID, DISCIPLINA_ID, CURSO_ID, ANO_INICIO, SEMESTRE_INICIO) VALUES";
        sql += "(GEN_ID(SEQ_TURMA, 1), '" + turma.getDisciplina().getId() + "', " + turma.getCurso().getId()
                + ", " + turma.getAnoInicio() + ", " + turma.getSemestreInicio() + ")";
        executeNativeUpdate(sql);
    }

    public void atualizarTurma(Turma turma) {
        String sql = "UPDATE TURMA SET DISCIPLINA_ID = " + turma.getDisciplina().getId() + ", CURSO_ID = " + turma.getCurso().getId()
                + ", ANO_INICIO = " + turma.getAnoInicio() + ", SEMESTRE_INICIO = " + turma.getSemestreInicio()
                + " WHERE ID = " + turma.getId();
        executeNativeUpdate(sql);
    }

    public void deletarTurma(Turma turma) {
        String sql = "DELETE FROM TURMA WHERE ID = " + turma.getId();
        executeNativeUpdate(sql);
    }
}
