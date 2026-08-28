package co.edu.unicauca.solid.service;

import co.edu.unicauca.solid.domain.EstadoUsuario;
import co.edu.unicauca.solid.domain.Rol;
import co.edu.unicauca.solid.domain.Usuario;
import co.edu.unicauca.solid.repository.FakeUsuarioRepository;
import co.edu.unicauca.solid.security.Argon2PasswordHasher;
import co.edu.unicauca.solid.security.DefaultPasswordValidator;
import co.edu.unicauca.solid.security.PasswordHasher;
import co.edu.unicauca.solid.security.PasswordValidator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioServiceTest {

    private UsuarioService usuarioService;

    @BeforeEach
    void configurar() {

        FakeUsuarioRepository repository =
                new FakeUsuarioRepository();

        PasswordHasher passwordHasher =
                new Argon2PasswordHasher();

        PasswordValidator passwordValidator =
                new DefaultPasswordValidator();

        usuarioService = new UsuarioService(
                repository,
                passwordHasher,
                passwordValidator
        );
    }

    @Test
    void deberiaRegistrarUsuarioConPasswordValida() {

        usuarioService.registrarUsuario(
                "andres",
                "Andres Felipe",
                Rol.ESTUDIANTE,
                EstadoUsuario.ACTIVO,
                "Hola123!"
        );

        Usuario usuario =
                usuarioService.autenticar(
                        "andres",
                        "Hola123!"
                );

        assertEquals("andres", usuario.getLogin());
        assertEquals("Andres Felipe", usuario.getNombreCompleto());
        assertEquals(Rol.ESTUDIANTE, usuario.getRol());
        assertEquals(EstadoUsuario.ACTIVO, usuario.getEstado());
    }

    @Test
    void noDeberiaRegistrarPasswordInvalida() {

        assertThrows(
                IllegalArgumentException.class,
                () -> usuarioService.registrarUsuario(
                        "andres",
                        "Andres Felipe",
                        Rol.ESTUDIANTE,
                        EstadoUsuario.ACTIVO,
                        "hola"
                )
        );
    }

    @Test
    void noDeberiaPermitirLoginDuplicado() {

        usuarioService.registrarUsuario(
                "andres",
                "Andres Felipe",
                Rol.ESTUDIANTE,
                EstadoUsuario.ACTIVO,
                "Hola123!"
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> usuarioService.registrarUsuario(
                        "andres",
                        "Otro Usuario",
                        Rol.DOCENTE,
                        EstadoUsuario.ACTIVO,
                        "Otra123!"
                )
        );
    }

    @Test
    void noDeberiaAutenticarPasswordIncorrecta() {

        usuarioService.registrarUsuario(
                "andres",
                "Andres Felipe",
                Rol.ESTUDIANTE,
                EstadoUsuario.ACTIVO,
                "Hola123!"
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> usuarioService.autenticar(
                        "andres",
                        "Incorrecta123!"
                )
        );
    }

    @Test
    void noDeberiaAutenticarUsuarioInactivo() {

        usuarioService.registrarUsuario(
                "andres",
                "Andres Felipe",
                Rol.ESTUDIANTE,
                EstadoUsuario.INACTIVO,
                "Hola123!"
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> usuarioService.autenticar(
                        "andres",
                        "Hola123!"
                )
        );
    }
    @Test
void deberiaAutenticarUsuarioConRolDocente() {

    usuarioService.registrarUsuario(
            "docente",
            "Docente de Prueba",
            Rol.DOCENTE,
            EstadoUsuario.ACTIVO,
            "Docente123!"
    );

    Usuario usuario =
            usuarioService.autenticar(
                    "docente",
                    "Docente123!"
            );

    assertEquals(Rol.DOCENTE, usuario.getRol());
}
}