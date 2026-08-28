package co.edu.unicauca.solid.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseConnectionProvider {

    Connection getConnection() throws SQLException;
}