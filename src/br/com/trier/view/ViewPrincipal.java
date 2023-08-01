package br.com.trier.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    /**
     * Create the frame.
     */
    public ViewPrincipal() {
        setResizable(false);
        setName("frmPrincipal");
        setTitle("Agenda de Contatos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JMenuBar barraPrincipal = new JMenuBar();
        barraPrincipal.setBounds(0, 0, 784, 22);
        contentPane.add(barraPrincipal);

        JMenu menuCadastros = new JMenu("Cadastros");
        barraPrincipal.add(menuCadastros);

        JMenuItem opcaoContatos = new JMenuItem("Contatos");
        opcaoContatos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewConsultarContato view = new ViewConsultarContato();
                view.setVisible(true);
            }
        });
        menuCadastros.add(opcaoContatos);

        JMenu menuSistema = new JMenu("Sistema");
        barraPrincipal.add(menuSistema);

        JMenuItem opcaoSair = new JMenuItem("Sair");
        opcaoSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        menuSistema.add(opcaoSair);
        setLocationRelativeTo(null);
    }
}
