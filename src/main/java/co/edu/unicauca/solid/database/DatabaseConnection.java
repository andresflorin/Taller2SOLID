package co.edu.unicauca.solid.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection implements DatabaseConnectionProvider {

    private static final String URL = "jdbc:sqlite:solid.db";

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}