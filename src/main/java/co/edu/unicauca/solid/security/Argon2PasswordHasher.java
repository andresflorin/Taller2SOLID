package co.edu.unicauca.solid.security;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class Argon2PasswordHasher implements PasswordHasher {

    private final Argon2 argon2;

    public Argon2PasswordHasher() {
        this.argon2 = Argon2Factory.create();
    }

    @Override
    public String hash(String password) {
        return argon2.hash(3, 65536, 1, password.toCharArray());
    }

    @Override
    public boolean matches(String password, String passwordHash) {
        return argon2.verify(passwordHash, password.toCharArray());
    }
}