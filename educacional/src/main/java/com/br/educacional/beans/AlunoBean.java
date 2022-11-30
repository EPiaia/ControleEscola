package com.br.educacional.beans;

import com.br.educacional.domains.Aluno;
import com.br.educacional.domains.Estado;
import com.br.educacional.domains.Municipio;
import com.br.educacional.domains.Pessoa;
import com.br.educacional.services.AlunoService;
import com.br.educacional.services.EstadoService;
import com.br.educacional.services.MunicipioService;
import com.br.educacional.services.PessoaService;
import com.br.educacional.utils.JsfUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Piaia
 */
@ViewScoped
@Named
public class AlunoBean implements Serializable {

    @EJB
    private MunicipioService municipioService;
    @EJB
    private EstadoService estadoService;
    @EJB
    private AlunoService alunoService;
    @EJB
    private PessoaService pessoaService;

    private Aluno aluno = new Aluno();

    @PostConstruct
    private void init() {
        this.aluno.setPessoa(new Pessoa());
    }

    public List<Municipio> filtrarCidades(String query) {
        return municipioService.getCidades(query);
    }

    public void gravar() {
        Pessoa pessoa = pessoaService.getPessoaPeloCpf(aluno.getPessoa().getCpf());
        if (pessoa != null) {
            JsfUtil.warn("Já existe uma pessoa com este CPF!");
            return;
        }
        Estado estado = estadoService.getEstadoPorUf(aluno.getPessoa().getCidade().getUf());
        aluno.getPessoa().setEstado(estado);
        alunoService.salvar(aluno);
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
}
