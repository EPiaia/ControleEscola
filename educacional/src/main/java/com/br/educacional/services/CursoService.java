package com.br.educacional.services;

import com.br.educacional.domains.Curso;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Piaia
 */
@Stateless
@LocalBean
public class CursoService extends BaseService<Curso> {

    public List<Curso> getCursos(Integer codigo, String query) {
        String sql = "SELECT * FROM CURSO";
        if (codigo != null) {
            sql += " WHERE ID = " + codigo;
        } else if (query != null && !query.isEmpty()) {
            sql += " WHERE UPPER(DESCRICAO) LIKE UPPER('%" + query + "%')";
        }
        return executeNativeQuery(Curso.class, sql);
    }

    public Curso getCursoPeloCodigo(Integer codigo) {
        List<Curso> cursos = getCursos(codigo, null);
        if (cursos != null && !cursos.isEmpty()) {
            return cursos.get(0);
        }
        return null;
    }

    public void inserirCurso(Curso curso) {
        String sql = "INSERT INTO CURSO (ID, DESCRICAO, QTD_SEMESTRES) VALUES";
        sql += "(GEN_ID(SEQ_CURSO, 1), '" + curso.getDescricao() + "', " + curso.getQtdSemestres() + ")";
        executeNativeUpdate(sql);
    }

    public void atualizarCurso(Curso curso) {
        String sql = "UPDATE CURSO SET DESCRICAO = '" + curso.getDescricao() + "', QTD_SEMESTRES = " + curso.getQtdSemestres() + " WHERE ID = " + curso.getId();
        executeNativeUpdate(sql);
    }

    public void deletarCurso(Curso curso) {
        String sql = "DELETE FROM CURSO WHERE ID = " + curso.getId();
        executeNativeUpdate(sql);
    }
}
