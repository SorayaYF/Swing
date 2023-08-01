package br.com.trier.view.componentes.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.trier.core.domain.Contato;

public class ContatoTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    private final int QTDE_COLUNAS = 3;

    private List<Contato> contatos;

    public ContatoTableModel(List<Contato> contatos) {
        this.contatos = contatos;
    }

    @Override
    public int getColumnCount() {
        return QTDE_COLUNAS;
    }

    public String getColumnName(int column) {
        if (column == 0) {
            return "ID";
        } else if (column == 1) {
            return "Nome Completo";
        } else if (column == 2) {
            return "Telefone";
        }
        throw new IllegalArgumentException("Indíce inválido");
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return contatos.get(rowIndex).getId();
        } else if (columnIndex == 1) {
            return contatos.get(rowIndex).getNomeCompleto();
        } else if (columnIndex == 2) {
            return contatos.get(rowIndex).getNumeroDeTelefone();
        }
        throw new IllegalArgumentException("Índice inválido");
    }

    @Override
    public int getRowCount() {
        return contatos.size();
    }

    public Contato getPor(int rowIndex) {
        return contatos.get(rowIndex);
    }

    public void removerPor(int rowIndex) {
        this.contatos.remove(rowIndex);
    }

}
