package com.br.educacional.beans;

import com.br.educacional.domains.Curso;
import com.br.educacional.domains.Disciplina;
import com.br.educacional.domains.DisciplinaxCurso;
import com.br.educacional.services.CursoService;
import com.br.educacional.services.DisciplinaService;
import com.br.educacional.services.DisciplinaxCursoService;
import com.br.educacional.utils.JsfUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Piaia
 */
@Named
@ViewScoped
public class CursoBean implements Serializable {

    @EJB
    private CursoService cursoService;
    @EJB
    private DisciplinaService disciplinaService;
    @EJB
    private DisciplinaxCursoService dcs;

    private Curso curso = new Curso();
    private List<Curso> cursosFiltrados = new ArrayList<>();
    private Curso cursoSelecionado;
    private Integer filtroCod;

    private Integer filtroCodDisc;
    private String filtroDescDisc;
    private List<Disciplina> disciplinas = new ArrayList<>();
    private List<Disciplina> disciplinasSelecionadas = new ArrayList<>();
    private List<DisciplinaxCurso> discxCurso = new ArrayList<>();

    public void gravar() {
        if (curso.getId() == null) {
            cursoService.inserirCurso(curso);
        } else {
            if (!existeDiscParaTodosSemestres()) {
                JsfUtil.warn("Cada semestre deve conter ao menos uma disciplina");
                return;
            }
            cursoService.atualizarCurso(curso);
            for (DisciplinaxCurso disciplinaxCurso : discxCurso) {
                dcs.atualizarDisciplinaxCurso(disciplinaxCurso);
            }
        }
        JsfUtil.info("Registro salvo com sucesso!");
        cancelar();
    }

    public void abrirPesquisa() {
        JsfUtil.pfShowDialog("wvBscCurso");
    }

    public void deletar() {
        if (this.curso == null || this.curso.getId() == null) {
            JsfUtil.warn("É necessário selecionar um registro para excluir");
            return;
        }
        cursoService.deletarCurso(curso);
        JsfUtil.warn("Registro deletado com sucesso!");
        cancelar();
    }

    public void cancelar() {
        this.curso = new Curso();
        this.filtroCodDisc = null;
        this.filtroDescDisc = "";
        this.disciplinas = new ArrayList<>();
        this.disciplinasSelecionadas = new ArrayList<>();
        this.discxCurso = new ArrayList<>();
    }

    public void buscarCursos() {
        cursosFiltrados = cursoService.getCursos(filtroCod, null);
    }

    public void selecionarCurso() {
        this.curso = cursoSelecionado;
        this.cursosFiltrados = new ArrayList<>();
        this.filtroCod = null;
        JsfUtil.pfHideDialog("wvBscCurso");
        this.discxCurso = dcs.getDisciplinaxCurso(this.curso.getId());
    }

    public void buscarDisciplinas() {
        this.disciplinas = disciplinaService.getDisciplinas(filtroCodDisc, filtroDescDisc);
        this.disciplinas.removeAll(discxCurso.stream().map(d -> d.getDisciplina()).collect(Collectors.toList()));
    }

    public void selecionarDisciplinas() {
        for (Disciplina disciplina : disciplinasSelecionadas) {
            DisciplinaxCurso discxCurso2 = new DisciplinaxCurso();
            discxCurso2.setCurso(curso);
            discxCurso2.setDisciplina(disciplina);
            discxCurso2.setSemestre(1);
            dcs.inserirDisciplinaxCurso(discxCurso2);
            this.discxCurso.add(discxCurso2);
        }
        buscarDisciplinas();
    }

    public void removerDisciplina(DisciplinaxCurso dxc) {
        discxCurso.remove(dxc);
        dcs.deletarDisciplinaxCurso(dxc);
        buscarDisciplinas();
    }

    public List<Integer> getSemestres() {
        List<Integer> semestres = new ArrayList<>();
        for (int i = 1; i <= curso.getQtdSemestres(); i++) {
            semestres.add(i);
        }
        return semestres;
    }

    private boolean existeDiscParaTodosSemestres() {
        for (int i = 1; i <= curso.getQtdSemestres(); i++) {
            int q = i;
            if (discxCurso.stream().filter(d -> d.getSemestre().equals(q)).count() == 0) {
                return false;
            }
        }
        return true;
    }

    public void alterarSemestre() {
        for (DisciplinaxCurso disciplinaxCurso : discxCurso) {
            if (disciplinaxCurso.getSemestre() > this.curso.getQtdSemestres()) {
                disciplinaxCurso.setSemestre(this.curso.getQtdSemestres());
            }
        }
    }

    public boolean isRenderizaSelecaoDisc() {
        return this.curso.getId() != null;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Curso> getCursosFiltrados() {
        return cursosFiltrados;
    }

    public void setCursosFiltrados(List<Curso> cursosFiltrados) {
        this.cursosFiltrados = cursosFiltrados;
    }

    public Curso getCursoSelecionado() {
        return cursoSelecionado;
    }

    public void setCursoSelecionado(Curso cursoSelecionado) {
        this.cursoSelecionado = cursoSelecionado;
    }

    public Integer getFiltroCod() {
        return filtroCod;
    }

    public void setFiltroCod(Integer filtroCod) {
        this.filtroCod = filtroCod;
    }

    public Integer getFiltroCodDisc() {
        return filtroCodDisc;
    }

    public void setFiltroCodDisc(Integer filtroCodDisc) {
        this.filtroCodDisc = filtroCodDisc;
    }

    public String getFiltroDescDisc() {
        return filtroDescDisc;
    }

    public void setFiltroDescDisc(String filtroDescDisc) {
        this.filtroDescDisc = filtroDescDisc;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public List<Disciplina> getDisciplinasSelecionadas() {
        return disciplinasSelecionadas;
    }

    public void setDisciplinasSelecionadas(List<Disciplina> disciplinasSelecionadas) {
        this.disciplinasSelecionadas = disciplinasSelecionadas;
    }

    public List<DisciplinaxCurso> getDiscxCurso() {
        return discxCurso;
    }

    public void setDiscxCurso(List<DisciplinaxCurso> discxCurso) {
        this.discxCurso = discxCurso;
    }
}
