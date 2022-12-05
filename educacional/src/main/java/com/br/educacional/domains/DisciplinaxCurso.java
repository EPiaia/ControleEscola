package com.br.educacional.domains;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Piaia
 */
@Entity
@Table(name = "DISCIPLINAXCURSO")
public class DisciplinaxCurso implements Serializable {

    @Id
    private Integer id;
    @NotNull
    @JoinColumn(name = "DISCIPLINA_ID", referencedColumnName = "ID")
    @ManyToOne
    private Disciplina disciplina;
    @NotNull
    @JoinColumn(name = "CURSO_ID", referencedColumnName = "ID")
    @ManyToOne
    private Curso curso;
    @NotNull
    @Column(name = "SEMESTRE")
    private Integer semestre;

    public DisciplinaxCurso() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DisciplinaxCurso other = (DisciplinaxCurso) obj;
        return Objects.equals(this.id, other.id);
    }
}
