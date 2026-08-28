package co.edu.unicauca.solid;

import co.edu.unicauca.solid.config.AppConfig;
import co.edu.unicauca.solid.service.UsuarioService;
import co.edu.unicauca.solid.ui.LoginFrame;

import javax.swing.SwingUtilities;

public class App {

    public static void main(String[] args) {

        UsuarioService usuarioService =
                AppConfig.crearUsuarioService();

        SwingUtilities.invokeLater(() -> {

            LoginFrame ventana =
                    new LoginFrame(usuarioService);

            ventana.setVisible(true);
        });
    }
}