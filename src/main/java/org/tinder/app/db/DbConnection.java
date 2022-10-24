package org.tinder.app.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private final static String URL = "jdbc:postgresql://ec2-52-48-159-67.eu-west-1.compute.amazonaws.com:5432/d8cv8h9ddsee4o";
    private final static String NAME = "rhtdbelqzpdlct";
    private final static String PWD = "042ccad811cd31834ad81346d815da0a3a33a11f9b8c5f107457d586a9d43101";
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
