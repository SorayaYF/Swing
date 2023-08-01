package br.com.trier.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.com.trier.core.domain.Contato;
import br.com.trier.core.service.ContatoService;

public class ViewCadastroContato extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JTextField edtNomeCompleto;
    private JTextField edtEmail;
    private JFormattedTextField ftmTelefone;

    private ContatoService service;
    private Contato contato;

    /**
     * Create the frame.
     */
    public ViewCadastroContato() {
        setResizable(false);
        setTitle("Gerenciar Contatos - Cadastro");
        setName("frmCadastro");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 216);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        this.service = new ContatoService();

        JButton btnConsultar = new JButton("Consultar");
        btnConsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewConsultarContato view = new ViewConsultarContato();
                view.setVisible(true);
                dispose();
            }
        });
        btnConsultar.setBounds(335, 11, 89, 23);
        contentPane.add(btnConsultar);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String nomeCompleto = edtNomeCompleto.getText();
                String telefone = ftmTelefone.getText();
                String email = edtEmail.getText();

                if (contato == null) {
                    contato = new Contato(nomeCompleto, telefone, email);
                } else {
                    contato.setNomeCompleto(nomeCompleto);
                    contato.setNumeroDeTelefone(telefone);
                    contato.setEmail(email);
                }

                try {
                    service.salvar(contato);
                    JOptionPane.showMessageDialog(contentPane, "Contato salvo com sucesso");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(contentPane, ex.getMessage());
                }

            }
        });
        btnSalvar.setBounds(335, 143, 89, 23);
        contentPane.add(btnSalvar);

        JLabel lblNome = new JLabel("Nome Completo");
        lblNome.setBounds(10, 29, 98, 14);
        contentPane.add(lblNome);

        edtNomeCompleto = new JTextField();
        edtNomeCompleto.setBounds(10, 55, 414, 20);
        contentPane.add(edtNomeCompleto);
        edtNomeCompleto.setColumns(10);

        JLabel lblTelefone = new JLabel("Telefone");
        lblTelefone.setBounds(10, 86, 116, 14);
        contentPane.add(lblTelefone);

        ftmTelefone = new JFormattedTextField();
        ftmTelefone.setBounds(10, 111, 133, 20);
        contentPane.add(ftmTelefone);
        try {
            MaskFormatter mascara = new MaskFormatter("(##)#####-####");
            mascara.install(ftmTelefone);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JLabel lblEmail = new JLabel("E-mail");
        lblEmail.setBounds(154, 87, 55, 16);
        contentPane.add(lblEmail);

        edtEmail = new JTextField();
        edtEmail.setBounds(155, 111, 267, 20);
        contentPane.add(edtEmail);
        edtEmail.setColumns(10);
    }

    public void setContato(Contato contato) {
        this.contato = contato;
        this.edtNomeCompleto.setText(contato.getNomeCompleto());
        this.edtEmail.setText(contato.getEmail());
        this.ftmTelefone.setText(contato.getNumeroDeTelefone());
    }

}
