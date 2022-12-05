package com.br.educacional.beans;

import com.br.educacional.domains.Disciplina;
import com.br.educacional.domains.Professor;
import com.br.educacional.services.DisciplinaService;
import com.br.educacional.services.ProfessorService;
import com.br.educacional.utils.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Piaia
 */
@Named
@ViewScoped
public class DisciplinaBean implements Serializable {

    @EJB
    private DisciplinaService disciplinaService;
    @EJB
    private ProfessorService professorService;

    private Disciplina disciplina = new Disciplina();
    private List<Disciplina> disciplinasFiltradas = new ArrayList<>();
    private Disciplina disciplinaSelecionada;
    private Integer filtroCod;

    public void gravar() {
        if (disciplina.getId() == null) {
            disciplinaService.inserirDisciplina(disciplina);
        } else {
            disciplinaService.atualizarDisciplina(disciplina);
        }
        JsfUtil.info("Registro salvo com sucesso!");
        cancelar();
    }

    public void abrirPesquisa() {
        JsfUtil.pfShowDialog("wvBscDisciplina");
    }

    public void deletar() {
        if (this.disciplina == null || this.disciplina.getId() == null) {
            JsfUtil.warn("É necessário selecionar um registro para excluir");
            return;
        }
        disciplinaService.deletarDisciplina(disciplina);
        JsfUtil.warn("Registro deletado com sucesso!");
        cancelar();
    }

    public void cancelar() {
        this.disciplina = new Disciplina();
    }

    public List<Professor> filtrarProfessores(String query) {
        return professorService.pesquisarProfessores(null, query);
    }

    public void buscarDisciplinas() {
        disciplinasFiltradas = disciplinaService.getDisciplinas(filtroCod, null);
    }

    public void selecionarDisciplina() {
        this.disciplina = disciplinaSelecionada;
        this.disciplinasFiltradas = new ArrayList<>();
        this.filtroCod = null;
        JsfUtil.pfHideDialog("wvBscDisciplina");
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public List<Disciplina> getDisciplinasFiltradas() {
        return disciplinasFiltradas;
    }

    public void setDisciplinasFiltradas(List<Disciplina> disciplinasFiltradas) {
        this.disciplinasFiltradas = disciplinasFiltradas;
    }

    public Integer getFiltroCod() {
        return filtroCod;
    }

    public void setFiltroCod(Integer filtroCod) {
        this.filtroCod = filtroCod;
    }

    public Disciplina getDisciplinaSelecionada() {
        return disciplinaSelecionada;
    }

    public void setDisciplinaSelecionada(Disciplina disciplinaSelecionada) {
        this.disciplinaSelecionada = disciplinaSelecionada;
    }
}
