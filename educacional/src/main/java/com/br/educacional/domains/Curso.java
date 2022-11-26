package com.br.educacional.domains;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Eduardo
 */
@Entity
@Table(name = "CURSO")
public class Curso implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;
    @NotNull
    @Column(name = "DESCRICAO")
    private String descricao;
    @NotNull
    @Column(name = "QTD_SEMESTRES")
    private Integer qtdSemestres;
    @NotNull
    @Column(name = "HORAS_TOTAIS")
    private String horasTotais;

    public Curso() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQtdSemestres() {
        return qtdSemestres;
    }

    public void setQtdSemestres(Integer qtdSemestres) {
        this.qtdSemestres = qtdSemestres;
    }

    public String getHorasTotais() {
        return horasTotais;
    }

    public void setHorasTotais(String horasTotais) {
        this.horasTotais = horasTotais;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final Curso other = (Curso) obj;
        return Objects.equals(this.id, other.id);
    }
}
