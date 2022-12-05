package com.br.educacional.services;

import com.br.educacional.domains.TurmaxAlunos;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Piaia
 */
@Stateless
@LocalBean
public class TurmaxAlunosService extends BaseService<TurmaxAlunos> {

    public void inserirTurmaxAlunos(TurmaxAlunos turmaxAlunos) {
        String sql = "INSERT INTO TURMAXALUNOS(ID, TURMA_ID, ALUNO_ID) VALUES(GEN_ID(SEQ_TXA, 1), "
                + turmaxAlunos.getTurma().getId() + ", " + turmaxAlunos.getAluno().getId() + ")";
        executeNativeUpdate(sql);
    }

    public void deletarTurmaxAlunos(TurmaxAlunos turmaxAlunos) {
        String sql = "DELETE FROM TURMAXALUNOS WHERE ID = " + turmaxAlunos.getId();
        executeNativeUpdate(sql);
    }

    public List<TurmaxAlunos> getTurmaxAlunos(Integer codTurma) {
        String sql = "SELECT * FROM TURMAXALUNOS WHERE TURMA_ID = " + codTurma;
        return executeNativeQuery(TurmaxAlunos.class, sql);
    }
}
