package com.br.educacional.services;

import com.br.educacional.domains.Pessoa;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Piaia
 */
@Stateless
@LocalBean
public class PessoaService extends BaseService<Pessoa> {

    public Pessoa getPessoaPeloCpf(String cpf) {
        String sql = "SELECT * FROM PESSOA P WHERE P.CPF = '" + cpf + "'";
        List<Pessoa> retorno = executeNativeQuery(Pessoa.class, sql);
        if (retorno != null && !retorno.isEmpty()) {
            return retorno.get(0);
        } else {
            return null;
        }
    }

    public void inserirAlunoProfessor(Pessoa pessoa, String tipo) {
        if (!"A".equals(tipo) && !"P".equals(tipo)) {
            throw new RuntimeException("Tipo não cadastrado");
        }
        StringBuilder sql = new StringBuilder();
        sql.append("CALL INSERE_ALUNO_PROFESSOR (");
        sql.append("'").append(pessoa.getNome()).append("', ");
        sql.append("'").append(pessoa.getCpf()).append("', ");
        sql.append("'").append(pessoa.getEmail()).append("', ");
        sql.append("'").append(pessoa.getTelefone()).append("', ");
        sql.append("'").append(pessoa.getRua()).append("', ");
        sql.append("'").append(pessoa.getBairro()).append("', ");
        sql.append(pessoa.getNumero()).append(", ");
        sql.append(pessoa.getCidade().getId()).append(", ");
        sql.append(pessoa.getEstado().getId()).append(", ");
        sql.append("'").append(tipo).append("');");
        executeNativeUpdate(sql.toString());
    }

    public void atualizarPessoa(Pessoa pessoa) {
        StringBuilder sql = new StringBuilder();
        sql.append("CALL ATUALIZA_PESSOA (");
        sql.append("'").append(pessoa.getNome()).append("', ");
        sql.append("'").append(pessoa.getCpf()).append("', ");
        sql.append("'").append(pessoa.getEmail()).append("', ");
        sql.append("'").append(pessoa.getTelefone()).append("', ");
        sql.append("'").append(pessoa.getRua()).append("', ");
        sql.append("'").append(pessoa.getBairro()).append("', ");
        sql.append(pessoa.getNumero()).append(", ");
        sql.append(pessoa.getCidade().getId()).append(", ");
        sql.append(pessoa.getEstado().getId()).append(");");
        executeNativeUpdate(sql.toString());
    }

}
