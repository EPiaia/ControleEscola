package com.br.educacional.domains;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Eduardo
 */
@Entity
@Table(name = "TURMA")
public class Turma implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;
    @NotNull
    @JoinColumn(name = "DISCIPLINA_ID", referencedColumnName = "ID")
    @OneToOne
    private Disciplina disciplina;
    @NotNull
    @Column(name = "ANO_INICIO")
    private Integer anoInicio;
    @NotNull
    @Column(name = "SEMESTRE_INICIO")
    private Integer semestreInicio;

    public Turma() {
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

    public Integer getAnoInicio() {
        return anoInicio;
    }

    public void setAnoInicio(Integer anoInicio) {
        this.anoInicio = anoInicio;
    }

    public Integer getSemestreInicio() {
        return semestreInicio;
    }

    public void setSemestreInicio(Integer semestreInicio) {
        this.semestreInicio = semestreInicio;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final Turma other = (Turma) obj;
        return Objects.equals(this.id, other.id);
    }
}
