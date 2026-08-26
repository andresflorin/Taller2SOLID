package co.edu.unicauca.solid.repository;

import co.edu.unicauca.solid.domain.Usuario;

import java.util.Optional;

public interface UsuarioRepository {

    void guardar(Usuario usuario);

    Optional<Usuario> buscarPorLogin(String login);
}