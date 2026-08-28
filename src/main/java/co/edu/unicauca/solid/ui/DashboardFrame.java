package co.edu.unicauca.solid.ui;

import co.edu.unicauca.solid.domain.Rol;
import co.edu.unicauca.solid.domain.Usuario;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {

    private final Usuario usuario;
private final co.edu.unicauca.solid.service.UsuarioService usuarioService;

    public DashboardFrame(
        Usuario usuario,
        co.edu.unicauca.solid.service.UsuarioService usuarioService)  {

        this.usuario = usuario;
this.usuarioService = usuarioService;

        configurarVentana();
        crearComponentes();
    }

    private void configurarVentana() {

        setTitle("Taller 2 SOLID - Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void crearComponentes() {

        JPanel panel = new JPanel(
                new BorderLayout(10, 10)
        );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 20, 20, 20
                )
        );

        JLabel bienvenida = new JLabel(
                "Bienvenido, "
                        + usuario.getNombreCompleto()
                        + " | Rol: "
                        + usuario.getRol()
        );

        bienvenida.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        panel.add(
                bienvenida,
                BorderLayout.NORTH
        );

        JPanel acciones = new JPanel(
                new GridLayout(0, 1, 10, 10)
        );

        crearAccionesPorRol(acciones);

        panel.add(
                acciones,
                BorderLayout.CENTER
        );

        JButton btnCerrar =
                new JButton("Cerrar sesión");

        btnCerrar.addActionListener(e -> {

    dispose();

    LoginFrame login =
            new LoginFrame(usuarioService);

    login.setVisible(true);
});

        panel.add(
                btnCerrar,
                BorderLayout.SOUTH
        );

        add(panel);
    }

    private void crearAccionesPorRol(JPanel acciones) {

        Rol rol = usuario.getRol();

        switch (rol) {

            case ADMINISTRADOR -> {

                agregarBoton(
                        acciones,
                        "Gestionar usuarios",
                        "Aquí el administrador puede gestionar los usuarios."
                );

                agregarBoton(
                        acciones,
                        "Gestionar roles",
                        "Aquí el administrador puede gestionar los roles."
                );

                agregarBoton(
                        acciones,
                        "Gestionar sistema",
                        "Funciones generales de administración."
                );
            }

            case AUTOR_PREGUNTAS -> {

                agregarBoton(
                        acciones,
                        "Crear preguntas",
                        "Función para crear preguntas."
                );

                agregarBoton(
                        acciones,
                        "Mis preguntas",
                        "Función para consultar preguntas creadas."
                );
            }

            case REVISOR -> {

                agregarBoton(
                        acciones,
                        "Revisar preguntas",
                        "Función para revisar preguntas pendientes."
                );
            }

            case DOCENTE -> {

                agregarBoton(
                        acciones,
                        "Consultar preguntas",
                        "Función para consultar el banco de preguntas."
                );

                agregarBoton(
                        acciones,
                        "Gestionar evaluaciones",
                        "Función para gestionar evaluaciones."
                );
            }

            case ESTUDIANTE -> {

                agregarBoton(
                        acciones,
                        "Presentar evaluación",
                        "Función para presentar evaluaciones."
                );

                agregarBoton(
                        acciones,
                        "Consultar resultados",
                        "Función para consultar resultados."
                );
            }
        }
    }

    private void agregarBoton(
            JPanel panel,
            String texto,
            String mensaje) {

        JButton boton = new JButton(texto);

        boton.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this,
                        mensaje,
                        texto,
                        JOptionPane.INFORMATION_MESSAGE
                )
        );

        panel.add(boton);
    }
}
