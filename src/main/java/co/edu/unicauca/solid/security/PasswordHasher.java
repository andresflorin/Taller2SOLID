package co.edu.unicauca.solid.security;

public interface PasswordHasher {

    String hash(String password);

    boolean matches(String password, String passwordHash);
}