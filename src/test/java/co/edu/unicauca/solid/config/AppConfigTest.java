package co.edu.unicauca.solid.config;

import co.edu.unicauca.solid.domain.EstadoUsuario;
import co.edu.unicauca.solid.domain.Rol;
import co.edu.unicauca.solid.domain.Usuario;
import co.edu.unicauca.solid.service.UsuarioService;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppConfigTest {

    @Test
    void deberiaCrearUsuarioService() {

        UsuarioService usuarioService =
                AppConfig.crearUsuarioService();

        assertNotNull(usuarioService);
    }

    @Test
    void deberiaPermitirRegistrarUsuarioConConfiguracionReal() {

        UsuarioService usuarioService =
                AppConfig.crearUsuarioService();

        String login = "configTest_" + System.currentTimeMillis();

        usuarioService.registrarUsuario(
                login,
                "Usuario Configuracion",
                Rol.ESTUDIANTE,
                EstadoUsuario.ACTIVO,
                "Config123!"
        );

        Usuario usuario =
                usuarioService.autenticar(
                        login,
                        "Config123!"
                );

        assertEquals(login, usuario.getLogin());
        assertEquals(
                "Usuario Configuracion",
                usuario.getNombreCompleto()
        );
    }
}