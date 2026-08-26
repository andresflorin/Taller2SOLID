package co.edu.unicauca.solid.security;

public class DefaultPasswordValidator implements PasswordValidator {

    @Override
    public boolean esValida(String password) {

        if (password == null || password.length() < 6) {
            return false;
        }

        boolean tieneMayuscula = false;
        boolean tieneDigito = false;
        boolean tieneCaracterEspecial = false;

        for (char caracter : password.toCharArray()) {

            if (Character.isUpperCase(caracter)) {
                tieneMayuscula = true;
            } else if (Character.isDigit(caracter)) {
                tieneDigito = true;
            } else if (!Character.isLetterOrDigit(caracter)) {
                tieneCaracterEspecial = true;
            }
        }

        return tieneMayuscula
                && tieneDigito
                && tieneCaracterEspecial;
    }
}