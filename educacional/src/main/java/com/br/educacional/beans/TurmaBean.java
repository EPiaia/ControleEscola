package com.br.educacional.beans;

import com.br.educacional.domains.Aluno;
import com.br.educacional.domains.Curso;
import com.br.educacional.domains.Disciplina;
import com.br.educacional.domains.Turma;
import com.br.educacional.domains.TurmaxAlunos;
import com.br.educacional.services.AlunoService;
import com.br.educacional.services.CursoService;
import com.br.educacional.services.DisciplinaService;
import com.br.educacional.services.TurmaService;
import com.br.educacional.services.TurmaxAlunosService;
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
public class TurmaBean implements Serializable {

    @EJB
    private TurmaService turmaService;
    @EJB
    private DisciplinaService disciplinaService;
    @EJB
    private CursoService cursoService;
    @EJB
    private AlunoService alunoService;
    @EJB
    private TurmaxAlunosService tas;

    private Turma turma = new Turma();
    private List<Turma> turmasFiltradas = new ArrayList<>();
    private Integer filtroCodTurma;
    private Turma turmaSelecionada;

    private Integer filtroCodAluno;
    private String filtroNomeAluno;
    private List<Aluno> alunosFiltrados = new ArrayList<>();
    private List<Aluno> alunosSelecionados = new ArrayList<>();
    private List<TurmaxAlunos> turmaxAlunos = new ArrayList<>();

    public void gravar() {
        if (turma.getId() == null) {
            turmaService.inserirTurma(turma);
        } else {
            turmaService.atualizarTurma(turma);
        }
        JsfUtil.info("Registro salvo com sucesso!");
        cancelar();
    }

    public void abrirPesquisa() {
        JsfUtil.pfShowDialog("wvBscTurma");
    }

    public void deletar() {
        if (this.turma == null || this.turma.getId() == null) {
            JsfUtil.warn("É necessário selecionar um registro para excluir");
            return;
        }
        turmaService.deletarTurma(turma);
        JsfUtil.warn("Registro deletado com sucesso!");
        cancelar();
    }

    public void cancelar() {
        this.turma = new Turma();
    }

    public List<Disciplina> filtrarDisciplinas(String query) {
        return disciplinaService.getDisciplinas(null, query);
    }

    public List<Curso> filtrarCursos(String query) {
        return cursoService.getCursos(null, query);
    }

    public void buscarTurmas() {
        turmasFiltradas = turmaService.getTurmas(filtroCodTurma);
    }

    public void selecionarTurma() {
        this.turma = turmaSelecionada;
        this.turmasFiltradas = new ArrayList<>();
        this.filtroCodTurma = null;
        JsfUtil.pfHideDialog("wvBscTurma");
        this.turmaxAlunos = tas.getTurmaxAlunos(turma.getId());
        buscarAlunos();
    }

    public void buscarAlunos() {
        this.alunosFiltrados = alunoService.pesquisarAlunos(filtroCodAluno, filtroNomeAluno);
        this.alunosFiltrados.removeAll(turmaxAlunos.stream().map(a -> a.getAluno()).collect(Collectors.toList()));
    }

    public void selecionarAlunos() {
        for (Aluno alunoSelecionado : alunosSelecionados) {
            TurmaxAlunos txa = new TurmaxAlunos();
            txa.setAluno(alunoSelecionado);
            txa.setTurma(turma);
            tas.inserirTurmaxAlunos(txa);
            turmaxAlunos.add(txa);
        }
        buscarAlunos();
    }

    public void removerAluno(TurmaxAlunos txa) {
        tas.deletarTurmaxAlunos(txa);
        this.turmaxAlunos.remove(txa);
        buscarAlunos();
    }

    public boolean isRenderizaSelecaoAluno() {
        return this.turma.getId() != null;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public List<Turma> getTurmasFiltradas() {
        return turmasFiltradas;
    }

    public void setTurmasFiltradas(List<Turma> turmasFiltradas) {
        this.turmasFiltradas = turmasFiltradas;
    }

    public Integer getFiltroCodTurma() {
        return filtroCodTurma;
    }

    public void setFiltroCodTurma(Integer filtroCodTurma) {
        this.filtroCodTurma = filtroCodTurma;
    }

    public Turma getTurmaSelecionada() {
        return turmaSelecionada;
    }

    public void setTurmaSelecionada(Turma turmaSelecionada) {
        this.turmaSelecionada = turmaSelecionada;
    }

    public Integer getFiltroCodAluno() {
        return filtroCodAluno;
    }

    public void setFiltroCodAluno(Integer filtroCodAluno) {
        this.filtroCodAluno = filtroCodAluno;
    }

    public String getFiltroNomeAluno() {
        return filtroNomeAluno;
    }

    public void setFiltroNomeAluno(String filtroNomeAluno) {
        this.filtroNomeAluno = filtroNomeAluno;
    }

    public List<Aluno> getAlunosSelecionados() {
        return alunosSelecionados;
    }

    public void setAlunosSelecionados(List<Aluno> alunosSelecionados) {
        this.alunosSelecionados = alunosSelecionados;
    }

    public List<TurmaxAlunos> getTurmaxAlunos() {
        return turmaxAlunos;
    }

    public void setTurmaxAlunos(List<TurmaxAlunos> turmaxAlunos) {
        this.turmaxAlunos = turmaxAlunos;
    }

    public List<Aluno> getAlunosFiltrados() {
        return alunosFiltrados;
    }

    public void setAlunosFiltrados(List<Aluno> alunosFiltrados) {
        this.alunosFiltrados = alunosFiltrados;
    }
}
