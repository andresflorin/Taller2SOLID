package co.edu.unicauca.solid.ui;

import co.edu.unicauca.solid.domain.EstadoUsuario;
import co.edu.unicauca.solid.domain.Rol;
import co.edu.unicauca.solid.service.UsuarioService;

import javax.swing.*;
import java.awt.*;

public class RegistroFrame extends JFrame {

    private final UsuarioService usuarioService;

    private JTextField txtLogin;
    private JTextField txtNombre;
    private JComboBox<Rol> cmbRol;
    private JComboBox<EstadoUsuario> cmbEstado;
    private JPasswordField txtPassword;

    public RegistroFrame(UsuarioService usuarioService) {

        this.usuarioService = usuarioService;

        configurarVentana();
        crearComponentes();
    }

    private void configurarVentana() {

        setTitle("Taller 2 SOLID - Registro de Usuario");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void crearComponentes() {

        JPanel panel = new JPanel(
                new GridLayout(6, 2, 10, 10)
        );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 20, 20, 20
                )
        );

        txtLogin = new JTextField();
        txtNombre = new JTextField();

        cmbRol = new JComboBox<>(Rol.values());
        cmbEstado = new JComboBox<>(EstadoUsuario.values());

        txtPassword = new JPasswordField();

        JButton btnRegistrar =
                new JButton("Registrar");

        panel.add(new JLabel("Usuario:"));
        panel.add(txtLogin);

        panel.add(new JLabel("Nombre completo:"));
        panel.add(txtNombre);

        panel.add(new JLabel("Rol:"));
        panel.add(cmbRol);

        panel.add(new JLabel("Estado:"));
        panel.add(cmbEstado);

        panel.add(new JLabel("Contraseña:"));
        panel.add(txtPassword);

        panel.add(new JLabel());
        panel.add(btnRegistrar);

        add(panel);

        btnRegistrar.addActionListener(
                e -> registrarUsuario()
        );
    }

    private void registrarUsuario() {

        String login = txtLogin.getText().trim();
        String nombre = txtNombre.getText().trim();

        Rol rol = (Rol) cmbRol.getSelectedItem();

        EstadoUsuario estado =
                (EstadoUsuario) cmbEstado.getSelectedItem();

        String password =
                new String(txtPassword.getPassword());

        if (login.isEmpty() || nombre.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe completar todos los campos.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        try {

            usuarioService.registrarUsuario(
                    login,
                    nombre,
                    rol,
                    estado,
                    password
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Usuario registrado correctamente.",
                    "Registro exitoso",
                    JOptionPane.INFORMATION_MESSAGE
            );

            dispose();

        } catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}