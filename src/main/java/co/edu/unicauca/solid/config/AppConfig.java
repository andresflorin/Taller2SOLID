        package co.edu.unicauca.solid.config;

        import co.edu.unicauca.solid.database.DatabaseConnection;
        import co.edu.unicauca.solid.database.DatabaseConnectionProvider;
        import co.edu.unicauca.solid.repository.SQLiteUsuarioRepository;
        import co.edu.unicauca.solid.repository.UsuarioRepository;
        import co.edu.unicauca.solid.security.Argon2PasswordHasher;
        import co.edu.unicauca.solid.security.DefaultPasswordValidator;
        import co.edu.unicauca.solid.security.PasswordHasher;
        import co.edu.unicauca.solid.security.PasswordValidator;
        import co.edu.unicauca.solid.service.UsuarioService;

        public class AppConfig {

        private AppConfig() {
        }

        public static UsuarioService crearUsuarioService() {

        DatabaseConnectionProvider connectionProvider =
                new DatabaseConnection();

        UsuarioRepository usuarioRepository =
                new SQLiteUsuarioRepository(connectionProvider);

        PasswordHasher passwordHasher =
                new Argon2PasswordHasher();

        PasswordValidator passwordValidator =
                new DefaultPasswordValidator();

        return new UsuarioService(
                usuarioRepository,
                passwordHasher,
                passwordValidator
        );
        }
        }