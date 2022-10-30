package org.tinder.app.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private final static String URL = "postgres://ertwcqouskognp:fe5435ead3547487eb79c0dfd7d42ec0dec7e17e85ed4b4468f5713602de4dc4@ec2-52-23-131-232.compute-1.amazonaws.com:5432/ddohpjksno9atv";
    private final static String NAME = "ertwcqouskognp";
    private final static String PWD = "fe5435ead3547487eb79c0dfd7d42ec0dec7e17e85ed4b4468f5713602de4dc4";
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
