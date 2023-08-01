package br.com.trier.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.trier.core.domain.Contato;
import br.com.trier.core.service.ContatoService;
import br.com.trier.view.componentes.table.ContatoTableModel;
import javax.swing.border.TitledBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewConsultarContato extends JFrame {

    private static final long serialVersionUID = 1L;

    private ContatoService service;

    private JPanel contentPane;

    private JTextField edtFiltro;

    private JTable tableContatos;

    private JScrollPane spTable;

    /**
     * Create the frame.
     */
    public ViewConsultarContato() {
        setResizable(false);
        setTitle("Gerenciar Contatos - Consulta");
        setName("frmConsulta");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 417);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        this.service = new ContatoService();

        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setBackground(Color.WHITE);
        btnAdicionar.setForeground(Color.GREEN);
        btnAdicionar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnAdicionar.setBackground(Color.GREEN);
                btnAdicionar.setForeground(Color.WHITE);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnAdicionar.setBackground(Color.WHITE);
                btnAdicionar.setForeground(Color.GREEN);
            }
        });
        btnAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewCadastroContato view = new ViewCadastroContato();
                view.setVisible(true);
                dispose();
            }
        });
        btnAdicionar.setBounds(335, 11, 89, 23);
        contentPane.add(btnAdicionar);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "Ações", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setBounds(224, 320, 198, 46);
        contentPane.add(panel);
        panel.setLayout(null);

        JButton btnRemover = new JButton("Remover");
        btnRemover.setBackground(Color.WHITE);
        btnRemover.setForeground(Color.RED);
        btnRemover.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnRemover.setBackground(Color.RED);
                btnRemover.setForeground(Color.WHITE);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnRemover.setBackground(Color.WHITE);
                btnRemover.setForeground(Color.RED);
                
            }
        });
        btnRemover.setBounds(104, 18, 89, 23);
        panel.add(btnRemover);

        JButton btnEditar = new JButton("Editar");
        btnEditar.setBackground(Color.WHITE);
        btnEditar.setForeground(Color.BLUE);
        btnEditar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnEditar.setBackground(Color.BLUE);
                btnEditar.setForeground(Color.WHITE);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnEditar.setBackground(Color.WHITE);
                btnEditar.setForeground(Color.BLUE);
            }
        });
        btnEditar.setBounds(5, 18, 89, 23);
        panel.add(btnEditar);
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = tableContatos.getSelectedRow();
                if (linhaSelecionada >= 0) {
                    ContatoTableModel model = (ContatoTableModel) tableContatos.getModel();
                    Contato contatoSelecionado = model.getPor(linhaSelecionada);
                    ViewCadastroContato view = new ViewCadastroContato();
                    view.setContato(contatoSelecionado);
                    view.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Selecione uma linha para edição");
                }
            }
        });
        btnRemover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = tableContatos.getSelectedRow();
                if (linhaSelecionada >= 0) { //
                    int opcao = JOptionPane.showConfirmDialog(contentPane, "Deseja remover o registro selecionado?",
                            "Remoção", JOptionPane.YES_NO_OPTION);
                    if (opcao == 0) {
                        ContatoTableModel model = (ContatoTableModel) tableContatos.getModel();
                        Contato contatoSelecionado = model.getPor(linhaSelecionada);
                        try {
                            service.removerPor(contatoSelecionado.getId());
                            model.removerPor(linhaSelecionada);
                            tableContatos.updateUI();
                            JOptionPane.showMessageDialog(contentPane, "Contato removido com sucesso");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(contentPane, ex.getMessage());
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Selecione uma linha para remoção");
                }
            }
        });

        JLabel lblFiltro = new JLabel("Filtro");
        lblFiltro.setBounds(10, 38, 55, 16);
        contentPane.add(lblFiltro);

        edtFiltro = new JTextField();
        edtFiltro.setBounds(10, 65, 315, 20);
        contentPane.add(edtFiltro);
        edtFiltro.setColumns(10);

        JButton btnListar = new JButton("Listar");
        btnListar.setBackground(Color.BLACK);
        btnListar.setForeground(Color.YELLOW);
        btnListar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnListar.setBackground(Color.YELLOW);
                btnListar.setForeground(Color.BLACK);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnListar.setBackground(Color.BLACK);
                btnListar.setForeground(Color.YELLOW);
            }
        });
        btnListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Contato> contatos = service.listarPor(edtFiltro.getText());
                    ContatoTableModel model = new ContatoTableModel(contatos);
                    tableContatos.setModel(model);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(contentPane, ex.getMessage());
                }
            }
        });
        btnListar.setBounds(335, 62, 89, 23);
        contentPane.add(btnListar);

        ContatoTableModel model = new ContatoTableModel(new ArrayList<Contato>());
        tableContatos = new JTable(model);
        spTable = new JScrollPane(tableContatos);
        spTable.setBounds(10, 128, 414, 187);
        contentPane.add(spTable);

        JLabel lblResultados = new JLabel("Resultados");
        lblResultados.setBounds(10, 101, 105, 16);
        contentPane.add(lblResultados);
        tableContatos.getTableHeader().setReorderingAllowed(false);
        for (int i = 0; i < tableContatos.getColumnModel().getColumnCount(); i++) {
            tableContatos.getColumnModel().getColumn(i).setResizable(false);
        }
    }

}
