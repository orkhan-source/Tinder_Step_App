package org.tinder.app.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private final static String URL = System.getenv("JDBC_DATABASE_URL");
    private final static String NAME = System.getenv("JDBC_DATABASE_USERNAME");
    private final static String PWD = System.getenv("JDBC_DATABASE_PASSWORD");
    private Connection connection=null;


    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, NAME, PWD);
    }


    public Connection connection() {
        if (connection == null) {
            try {
                connection = connect();
            } catch (SQLException e) {
                throw new IllegalStateException();
            }
        }

        return this.connection;
    }
}
