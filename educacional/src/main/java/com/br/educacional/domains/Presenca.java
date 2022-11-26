package com.br.educacional.domains;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Eduardo
 */
@Entity
@Table(name = "PRESENCA")
public class Presenca implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;
    @NotNull
    @JoinColumn(name = "MATRICULA_ID", referencedColumnName = "ID")
    @ManyToOne
    private Matricula matricula;
    @NotNull
    @JoinColumn(name = "TURMAXHORARIO_ID", referencedColumnName = "ID")
    @ManyToOne
    private TurmaxHorario turmaxHorario;

    public Presenca() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public TurmaxHorario getTurmaxHorario() {
        return turmaxHorario;
    }

    public void setTurmaxHorario(TurmaxHorario turmaxHorario) {
        this.turmaxHorario = turmaxHorario;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.id);
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
        final Presenca other = (Presenca) obj;
        return Objects.equals(this.id, other.id);
    }
}
