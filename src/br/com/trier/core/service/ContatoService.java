package br.com.trier.core.service;

import java.util.List;

import br.com.trier.core.dao.DaoContato;
import br.com.trier.core.dao.FactoryDao;
import br.com.trier.core.domain.Contato;

public class ContatoService {

    private DaoContato dao;

    public ContatoService() {
        this.dao = FactoryDao.getInstance().getDaoContato();
    }

    public void salvar(Contato contato) {
        this.validar(contato);
        boolean isJaInserido = contato.getId() > 0;
        if (isJaInserido) {
            this.dao.alterar(contato);
        } else {
            this.dao.inserir(contato);
        }
    }

    private void validar(Contato contato) {

        if (contato != null) {

            boolean isNomeInvalido = contato.getNomeCompleto() == null && contato.getNomeCompleto().isBlank()
                    && contato.getNomeCompleto().length() < 3 && contato.getNomeCompleto().length() > 120;

            if (isNomeInvalido) {
                throw new IllegalArgumentException(
                        "O nome completo é obrigatório " + "e deve possuir entre 3 e 120 caracteres");
            }

            boolean isTelefoneInvalido = contato.getNumeroDeTelefone() == null
                    && contato.getNumeroDeTelefone().isBlank() && contato.getNomeCompleto().length() != 14;

            if (isTelefoneInvalido) {
                throw new IllegalArgumentException(
                        "O telefone do contato deve " + "possuir 14 caracteres incluindo máscara");
            }

            boolean isEmailInvalido = contato.getEmail() == null && contato.getEmail().isBlank()
                    && contato.getNomeCompleto().length() > 200 && !contato.getEmail().contains("@");

            if (isEmailInvalido) {
                throw new IllegalArgumentException(
                        "O email do contato deve " + "possuir menos de 200 caracteres e possuir '@'");
            }

        } else {
            throw new NullPointerException("O contato não pode ser nulo");
        }
    }

    public void removerPor(int idDoContato) {
        if (idDoContato > 0) {
            this.dao.excluirPor(idDoContato);
        } else {
            throw new IllegalArgumentException("O id para remoção do contato deve ser maior que zero");
        }
    }

    public List<Contato> listarPor(String nome) {
        if (nome != null && !nome.isBlank()) {
            String filtro = "%" + nome + "%";
            return dao.listarPor(filtro);
        } else {
            throw new IllegalArgumentException("O filtro para listagem é obrigatório");
        }
    }

}
