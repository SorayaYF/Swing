package br.com.trier.core.dao.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.trier.core.dao.DaoContato;
import br.com.trier.core.dao.ManagerDb;
import br.com.trier.core.domain.Contato;

public class DaoPostgresContato implements DaoContato {

    private final String INSERT = "INSERT INTO contatos (nome_completo, " + "numero_telefone, email) VALUES (?, ?, ?) ";

    private final String UPDATE = "UPDATE contatos SET nome_completo = ?, "
            + "numero_telefone = ?, email = ? WHERE id = ? ";

    private final String DELETE = "DELETE FROM contatos WHERE id = ? ";

    private final String SELECT_BY_NOME = "SELECT id, nome_completo, numero_telefone, email "
            + "FROM contatos WHERE Upper(nome_completo) LIKE Upper(?)";

    private Connection conexao;

    public DaoPostgresContato() {
        this.conexao = ManagerDb.getInstance().getConexao();
    }

    @Override
    public void inserir(Contato contato) {
        PreparedStatement ps = null;
        try {
            ps = conexao.prepareStatement(INSERT);
            ps.setString(1, contato.getNomeCompleto());
            ps.setString(2, contato.getNumeroDeTelefone());
            ps.setString(3, contato.getEmail());
            ps.execute();
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro ao " + "inserir o contato. Motivo: " + e.getMessage());
        } finally {
            ManagerDb.getInstance().fechar(ps);
        }
    }

    @Override
    public void alterar(Contato contato) {
        PreparedStatement ps = null;
        try {
            ManagerDb.getInstance().configurarAutocommitDa(conexao, false);
            ps = conexao.prepareStatement(UPDATE);
            ps.setString(1, contato.getNomeCompleto());
            ps.setString(2, contato.getNumeroDeTelefone());
            ps.setString(3, contato.getEmail());
            ps.setInt(4, contato.getId());
            boolean isAlteracaoOK = ps.executeUpdate() == 1;
            if (isAlteracaoOK) {
                this.conexao.commit();
            } else {
                this.conexao.rollback();
            }
            ManagerDb.getInstance().configurarAutocommitDa(conexao, true);
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro ao " + "alterar o contato. Motivo: " + e.getMessage());
        } finally {
            ManagerDb.getInstance().fechar(ps);
        }
    }

    @Override
    public void excluirPor(int id) {
        PreparedStatement ps = null;
        try {
            ManagerDb.getInstance().configurarAutocommitDa(conexao, false);
            ps = conexao.prepareStatement(DELETE);
            ps.setInt(1, id);
            boolean isExclusaoOK = ps.executeUpdate() == 1;
            if (isExclusaoOK) {
                this.conexao.commit();
            } else {
                this.conexao.rollback();
            }
            ManagerDb.getInstance().configurarAutocommitDa(conexao, true);
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro ao " + "excluir o contato. Motivo: " + e.getMessage());
        } finally {
            ManagerDb.getInstance().fechar(ps);
        }
    }

    @Override
    public List<Contato> listarPor(String nomeCompleto) {
        List<Contato> contatos = new ArrayList<Contato>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conexao.prepareStatement(SELECT_BY_NOME);
            ps.setString(1, nomeCompleto);
            rs = ps.executeQuery();
            while (rs.next()) {
                contatos.add(extrairDo(rs));
            }
            return contatos;
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro ao " + "listar os contatos. Motivo: " + e.getMessage());
        } finally {
            ManagerDb.getInstance().fechar(ps);
            ManagerDb.getInstance().fechar(rs);
        }
    }

    private Contato extrairDo(ResultSet rs) {
        try {
            int id = rs.getInt("id");
            String nomeCompleto = rs.getString("nome_completo");
            String numeroDeTelefone = rs.getString("numero_telefone");
            String email = rs.getString("email");
            return new Contato(id, nomeCompleto, numeroDeTelefone, email);
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro ao " + "extrair o contato. Motivo: " + e.getMessage());
        }
    }

}
