package co.edu.unicauca.solid.domain;

public class Usuario {

    private String login;
    private String nombreCompleto;
    private Rol rol;
    private EstadoUsuario estado;
    private String password;

    public Usuario(String login, String nombreCompleto, Rol rol,
                   EstadoUsuario estado, String password) {
        this.login = login;
        this.nombreCompleto = nombreCompleto;
        this.rol = rol;
        this.estado = estado;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public Rol getRol() {
        return rol;
    }

    public EstadoUsuario getEstado() {
        return estado;
    }

    public String getPassword() {
        return password;
    }
}
