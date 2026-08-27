package co.edu.unicauca.solid.service;

import co.edu.unicauca.solid.domain.EstadoUsuario;
import co.edu.unicauca.solid.domain.Rol;
import co.edu.unicauca.solid.domain.Usuario;
import co.edu.unicauca.solid.repository.UsuarioRepository;
import co.edu.unicauca.solid.security.PasswordHasher;
import co.edu.unicauca.solid.security.PasswordValidator;

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordHasher passwordHasher;
    private final PasswordValidator passwordValidator;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            PasswordHasher passwordHasher,
            PasswordValidator passwordValidator) {

        this.usuarioRepository = usuarioRepository;
        this.passwordHasher = passwordHasher;
        this.passwordValidator = passwordValidator;
    }

    public void registrarUsuario(
            String login,
            String nombreCompleto,
            Rol rol,
            EstadoUsuario estado,
            String password) {

        if (usuarioRepository.buscarPorLogin(login).isPresent()) {
            throw new IllegalArgumentException(
                    "El login ya está registrado."
            );
        }

        if (!passwordValidator.esValida(password)) {
            throw new IllegalArgumentException(
                    "La contraseña no cumple los requisitos de seguridad."
            );
        }

        String passwordHash = passwordHasher.hash(password);

        Usuario usuario = new Usuario(
                login,
                nombreCompleto,
                rol,
                estado,
                passwordHash
        );

        usuarioRepository.guardar(usuario);
    }

    public Usuario autenticar(String login, String password) {

        Usuario usuario = usuarioRepository
                .buscarPorLogin(login)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Credenciales incorrectas."
                        )
                );

        if (usuario.getEstado() != EstadoUsuario.ACTIVO) {
            throw new IllegalArgumentException(
                    "El usuario está inactivo."
            );
        }

        if (!passwordHasher.matches(
                password,
                usuario.getPasswordHash())) {

            throw new IllegalArgumentException(
                    "Credenciales incorrectas."
            );
        }

        return usuario;
    }
}