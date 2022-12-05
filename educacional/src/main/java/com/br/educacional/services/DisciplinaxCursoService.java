package com.br.educacional.services;

import com.br.educacional.domains.DisciplinaxCurso;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Piaia
 */
@Stateless
@LocalBean
public class DisciplinaxCursoService extends BaseService<DisciplinaxCurso> {

    public void inserirDisciplinaxCurso(DisciplinaxCurso discxCurso) {
        String sql = "INSERT INTO DISCIPLINAXCURSO(ID, DISCIPLINA_ID, CURSO_ID, SEMESTRE) VALUES(GEN_ID(SEQ_DXC, 1), "
                + discxCurso.getDisciplina().getId() + ", " + discxCurso.getCurso().getId() + ", " + discxCurso.getSemestre() + ")";
        executeNativeUpdate(sql);
    }

    public void atualizarDisciplinaxCurso(DisciplinaxCurso discxCurso) {
        String sql = "UPDATE DISCIPLINAXCURSO SET SEMESTRE = " + discxCurso.getSemestre();
        executeNativeUpdate(sql);
    }

    public void deletarDisciplinaxCurso(DisciplinaxCurso discxCurso) {
        String sql = "DELETE FROM DISCIPLINAXCURSO WHERE ID = " + discxCurso.getId();
        executeNativeUpdate(sql);
    }

    public List<DisciplinaxCurso> getDisciplinaxCurso(Integer codCurso) {
        String sql = "SELECT * FROM DISCIPLINAXCURSO WHERE CURSO_ID = " + codCurso;
        return executeNativeQuery(DisciplinaxCurso.class, sql);
    }
}
