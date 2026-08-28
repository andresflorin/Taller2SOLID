package co.edu.unicauca.solid.repository;

import co.edu.unicauca.solid.domain.EstadoUsuario;
import co.edu.unicauca.solid.domain.Rol;
import co.edu.unicauca.solid.domain.Usuario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SQLiteUsuarioRepositoryTest {

    private SQLiteUsuarioRepository repository;

    @BeforeEach
    void configurar() {
        repository = new SQLiteUsuarioRepository();
    }

    @Test
    void deberiaGuardarYBuscarUsuario() {

        Usuario usuario = new Usuario(
                "usuarioSQLite",
                "Usuario de Prueba",
                Rol.ESTUDIANTE,
                EstadoUsuario.ACTIVO,
                "hashDePrueba"
        );

        repository.guardar(usuario);

        var resultado =
                repository.buscarPorLogin("usuarioSQLite");

        assertTrue(resultado.isPresent());

        Usuario usuarioEncontrado = resultado.get();

        assertEquals(
                "usuarioSQLite",
                usuarioEncontrado.getLogin()
        );

        assertEquals(
                "Usuario de Prueba",
                usuarioEncontrado.getNombreCompleto()
        );

        assertEquals(
                Rol.ESTUDIANTE,
                usuarioEncontrado.getRol()
        );

        assertEquals(
                EstadoUsuario.ACTIVO,
                usuarioEncontrado.getEstado()
        );

        assertEquals(
                "hashDePrueba",
                usuarioEncontrado.getPasswordHash()
        );
    }

    @Test
    void deberiaRetornarVacioCuandoUsuarioNoExiste() {

        var resultado =
                repository.buscarPorLogin("usuarioQueNoExiste");

        assertTrue(resultado.isEmpty());
    }
}