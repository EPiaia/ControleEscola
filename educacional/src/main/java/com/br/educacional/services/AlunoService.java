package com.br.educacional.services;

import com.br.educacional.domains.Aluno;
import com.br.educacional.domains.Pessoa;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Piaia
 */
@Stateless
@LocalBean
public class AlunoService extends BaseService<Aluno> {

    @EJB
    private PessoaService pessoaService;

    public void salvar(Aluno aluno) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO PESSOA(NOME, CPF, EMAIL, TELEFONE, RUA, BAIRRO, NUMERO, CIDADE_ID, ESTADO_ID) VALUES (");
        sql.append("'").append(aluno.getPessoa().getNome()).append("', ");
        sql.append("'").append(aluno.getPessoa().getCpf()).append("', ");
        sql.append("'").append(aluno.getPessoa().getEmail()).append("', ");
        sql.append("'").append(aluno.getPessoa().getTelefone()).append("', ");
        sql.append("'").append(aluno.getPessoa().getRua()).append("', ");
        sql.append("'").append(aluno.getPessoa().getBairro()).append("', ");
        sql.append(aluno.getPessoa().getNumero()).append(", ");
        sql.append(aluno.getPessoa().getCidade().getId()).append(", ");
        sql.append(aluno.getPessoa().getEstado().getId()).append(");");
        executeNativeUpdate(sql.toString());

        Pessoa pessoaSalva = pessoaService.getPessoaPeloCpf(aluno.getPessoa().getCpf());
        sql = new StringBuilder();
        sql.append("INSERT INTO ALUNO(PESSOA_ID) VALUES (").append(pessoaSalva.getId()).append(");");
        executeNativeUpdate(sql.toString());
    }

}
