package co.edu.unicauca.solid.repository;

import co.edu.unicauca.solid.database.DatabaseConnection;
import co.edu.unicauca.solid.database.DatabaseConnectionProvider;
import co.edu.unicauca.solid.domain.EstadoUsuario;
import co.edu.unicauca.solid.domain.Rol;
import co.edu.unicauca.solid.domain.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class SQLiteUsuarioRepository implements UsuarioRepository {

    private final DatabaseConnectionProvider connectionProvider;

    public SQLiteUsuarioRepository(
            DatabaseConnectionProvider connectionProvider) {

        this.connectionProvider = connectionProvider;
        crearTabla();
    }

    private void crearTabla() {

        String sql = """
                CREATE TABLE IF NOT EXISTS usuarios (
                    login TEXT PRIMARY KEY,
                    nombre_completo TEXT NOT NULL,
                    rol TEXT NOT NULL,
                    estado TEXT NOT NULL,
                    password_hash TEXT NOT NULL
                )
                """;

        try (Connection connection =
                     connectionProvider.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Error al crear la tabla usuarios.", e
            );
        }
    }
    @Override
    public void guardar(Usuario usuario) {

        String sql = """
                INSERT INTO usuarios
                (login, nombre_completo, rol, estado, password_hash)
                VALUES (?, ?, ?, ?, ?)
                """;

       try (Connection connection =
             connectionProvider.getConnection();
     PreparedStatement statement =
             connection.prepareStatement(sql)) {

            statement.setString(1, usuario.getLogin());
            statement.setString(2, usuario.getNombreCompleto());
            statement.setString(3, usuario.getRol().name());
            statement.setString(4, usuario.getEstado().name());
            statement.setString(5, usuario.getPasswordHash());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Error al guardar el usuario.", e
            );
        }
    }

    @Override
    public Optional<Usuario> buscarPorLogin(String login) {

        String sql = """
                SELECT login, nombre_completo, rol, estado, password_hash
                FROM usuarios
                WHERE login = ?
                """;

        try (Connection connection =
             connectionProvider.getConnection();
     PreparedStatement statement =
             connection.prepareStatement(sql)) {

            statement.setString(1, login);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    Usuario usuario = new Usuario(
                            resultSet.getString("login"),
                            resultSet.getString("nombre_completo"),
                            Rol.valueOf(resultSet.getString("rol")),
                            EstadoUsuario.valueOf(
                                    resultSet.getString("estado")
                            ),
                            resultSet.getString("password_hash")
                    );

                    return Optional.of(usuario);
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Error al buscar el usuario.", e
            );
        }

        return Optional.empty();
    }
}