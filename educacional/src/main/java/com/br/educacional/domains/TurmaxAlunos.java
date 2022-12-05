package com.br.educacional.domains;

import java.io.Serializable;
import java.util.Objects;
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
@Table(name = "TURMAXALUNOS")
public class TurmaxAlunos implements Serializable {

    @Id
    private Integer id;
    @NotNull
    @JoinColumn(name = "TURMA_ID", referencedColumnName = "ID")
    @ManyToOne
    private Turma turma;
    @NotNull
    @JoinColumn(name = "ALUNO_ID", referencedColumnName = "ID")
    @ManyToOne
    private Aluno aluno;

    public TurmaxAlunos() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
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
        final TurmaxAlunos other = (TurmaxAlunos) obj;
        return Objects.equals(this.id, other.id);
    }
}
