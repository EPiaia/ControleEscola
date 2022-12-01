package com.br.educacional.beans;

import com.br.educacional.domains.Aluno;
import com.br.educacional.domains.Estado;
import com.br.educacional.domains.Municipio;
import com.br.educacional.domains.Pessoa;
import com.br.educacional.domains.Professor;
import com.br.educacional.services.AlunoService;
import com.br.educacional.services.EstadoService;
import com.br.educacional.services.MunicipioService;
import com.br.educacional.services.PessoaService;
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
@ViewScoped
@Named
public class PessoaBean implements Serializable {

    @EJB
    private MunicipioService municipioService;
    @EJB
    private EstadoService estadoService;
    @EJB
    private PessoaService pessoaService;
    @EJB
    private AlunoService alunoService;
    @EJB
    private ProfessorService professorService;

    private Pessoa pessoa = new Pessoa();

    // Pesquisa
    private Integer filtroCodBusca;
    private List<Aluno> alunosFiltrados = new ArrayList<>();
    private List<Professor> professoresFiltrados = new ArrayList<>();
    private Aluno alunoSelecionado;
    private Professor professorSelecionado;

    public List<Municipio> filtrarCidades(String query) {
        return municipioService.getCidades(query);
    }

    public void gravar() {
        Pessoa p = pessoaService.getPessoaPeloCpf(pessoa.getCpf());
        if (pessoa.getId() != null && p != null) {
            JsfUtil.warn("Já existe uma pessoa com este CPF!");
            return;
        }
        Estado estado = estadoService.getEstadoPorUf(pessoa.getCidade().getUf());
        pessoa.setEstado(estado);
        if (pessoa.getId() == null) {
            pessoaService.inserirAlunoProfessor(pessoa, getTipo());
        } else {
            pessoaService.atualizarPessoa(pessoa);
        }
        JsfUtil.info("Registro salvo com sucesso!");
        cancelar();
    }

    public void abrirPesquisa() {
        if (isCadastroAluno()) {
            JsfUtil.pfShowDialog("wvBscAluno");
        } else if (isCadastroProfessor()) {
            JsfUtil.pfShowDialog("wvBscProfessor");
        }
    }

    public void deletar() {
        if (this.pessoa == null || this.pessoa.getId() == null) {
            JsfUtil.warn("É necessário selecionar um registro para excluir");
            return;
        }
        if (isCadastroAluno()) {
            Aluno aluno = alunoService.getAlunoPelaPessoa(pessoa);
            alunoService.deletar(aluno);
        } else if (isCadastroProfessor()) {
            Professor professor = professorService.getProfessorPelaPessoa(pessoa);
            professorService.deletar(professor);
        }
        JsfUtil.warn("Registro deletado com sucesso!");
        cancelar();
    }

    public void cancelar() {
        this.pessoa = new Pessoa();
        this.alunoSelecionado = null;
        this.alunosFiltrados = new ArrayList<>();
        this.professorSelecionado = null;
        this.professoresFiltrados = new ArrayList<>();
    }

    public void pesquisarAlunos() {
        this.alunosFiltrados = alunoService.pesquisarAlunos(filtroCodBusca);
    }

    public void selecionarAluno() {
        this.pessoa = alunoSelecionado.getPessoa();
        this.alunosFiltrados = new ArrayList<>();
        JsfUtil.pfHideDialog("wvBscAluno");
    }

    public void pesquisarProfessores() {
        this.professoresFiltrados = professorService.pesquisarProfessores(filtroCodBusca);
    }

    public void selecionarProfessor() {
        this.pessoa = professorSelecionado.getPessoa();
        this.professoresFiltrados = new ArrayList<>();
        JsfUtil.pfHideDialog("wvBscProfessor");
    }

    public boolean isCadastroAluno() {
        return JsfUtil.isPage("cadastro_aluno");
    }

    public boolean isCadastroProfessor() {
        return JsfUtil.isPage("cadastro_professor");
    }

    private String getTipo() {
        if (isCadastroAluno()) {
            return "A";
        } else if (isCadastroProfessor()) {
            return "P";
        } else {
            return "";
        }
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Integer getFiltroCodBusca() {
        return filtroCodBusca;
    }

    public void setFiltroCodBusca(Integer filtroCodBusca) {
        this.filtroCodBusca = filtroCodBusca;
    }

    public List<Aluno> getAlunosFiltrados() {
        return alunosFiltrados;
    }

    public void setAlunosFiltrados(List<Aluno> alunosFiltrados) {
        this.alunosFiltrados = alunosFiltrados;
    }

    public List<Professor> getProfessoresFiltrados() {
        return professoresFiltrados;
    }

    public void setProfessoresFiltrados(List<Professor> professoresFiltrados) {
        this.professoresFiltrados = professoresFiltrados;
    }

    public Aluno getAlunoSelecionado() {
        return alunoSelecionado;
    }

    public void setAlunoSelecionado(Aluno alunoSelecionado) {
        this.alunoSelecionado = alunoSelecionado;
    }

    public Professor getProfessorSelecionado() {
        return professorSelecionado;
    }

    public void setProfessorSelecionado(Professor professorSelecionado) {
        this.professorSelecionado = professorSelecionado;
    }
}
