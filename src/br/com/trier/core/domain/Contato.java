package br.com.trier.core.domain;

import java.util.Objects;

public class Contato {

    private int id;

    private String nomeCompleto;

    private String numeroDeTelefone;

    private String email;

    public Contato(String nomeCompleto, String numeroDeTelefone, String email) {
        this.nomeCompleto = nomeCompleto;
        this.numeroDeTelefone = numeroDeTelefone;
        this.email = email;
    }

    public Contato(int id, String nomeCompleto, String numeroDeTelefone, String email) {
        this(nomeCompleto, numeroDeTelefone, email);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getNumeroDeTelefone() {
        return numeroDeTelefone;
    }

    public void setNumeroDeTelefone(String numeroDeTelefone) {
        this.numeroDeTelefone = numeroDeTelefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Contato other = (Contato) obj;
        return id == other.id;
    }

}
