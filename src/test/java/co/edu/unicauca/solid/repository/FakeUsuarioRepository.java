package co.edu.unicauca.solid.repository;

import co.edu.unicauca.solid.domain.Usuario;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeUsuarioRepository implements UsuarioRepository {

    private final Map<String, Usuario> usuarios = new HashMap<>();

    @Override
    public void guardar(Usuario usuario) {
        usuarios.put(usuario.getLogin(), usuario);
    }

    @Override
    public Optional<Usuario> buscarPorLogin(String login) {
        return Optional.ofNullable(usuarios.get(login));
    }
}