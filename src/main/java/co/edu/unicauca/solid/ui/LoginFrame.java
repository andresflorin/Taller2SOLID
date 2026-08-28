package co.edu.unicauca.solid.ui;


import co.edu.unicauca.solid.domain.Usuario;
import co.edu.unicauca.solid.service.UsuarioService;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private final UsuarioService usuarioService;

    private JTextField txtLogin;
    private JPasswordField txtPassword;

    public LoginFrame(UsuarioService usuarioService) {

        this.usuarioService = usuarioService;

        configurarVentana();
        crearComponentes();
    }

    private void configurarVentana() {

        setTitle("Taller 2 SOLID - Inicio de Sesión");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

   private void crearComponentes() {

    JPanel panel = new JPanel(
            new GridLayout(4, 2, 10, 10)
    );

    panel.setBorder(
            BorderFactory.createEmptyBorder(
                    30, 30, 30, 30
            )
    );

    JLabel lblLogin = new JLabel("Usuario:");
    JLabel lblPassword = new JLabel("Contraseña:");

    txtLogin = new JTextField();
    txtPassword = new JPasswordField();

    JButton btnIngresar =
            new JButton("Ingresar");

    JButton btnRegistrar =
            new JButton("Registrar usuario");

    panel.add(lblLogin);
    panel.add(txtLogin);

    panel.add(lblPassword);
    panel.add(txtPassword);

    panel.add(btnIngresar);
    panel.add(btnRegistrar);

    panel.add(new JLabel());
    panel.add(new JLabel());

    add(panel);

    btnIngresar.addActionListener(
            e -> iniciarSesion()
    );

    btnRegistrar.addActionListener(e -> {

        RegistroFrame registro =
                new RegistroFrame(usuarioService);

        registro.setVisible(true);
    });
}
    private void iniciarSesion() {

    String login = txtLogin.getText().trim();

    String password =
            new String(txtPassword.getPassword());

    try {

        Usuario usuario =
                usuarioService.autenticar(
                        login,
                        password
                );

        DashboardFrame dashboard =
        new DashboardFrame(
                usuario,
                usuarioService
        );

        dashboard.setVisible(true);

        dispose();

    } catch (IllegalArgumentException ex) {

        JOptionPane.showMessageDialog(
                this,
                ex.getMessage(),
                "Error de autenticación",
                JOptionPane.ERROR_MESSAGE
        );
    }
}

    
}