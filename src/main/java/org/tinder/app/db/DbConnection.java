package org.tinder.app.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private final static String URL = "jdbc:postgres://nglxiuekikrqwe:e36c325b1e86cb17efa754ba765e8e0ce5d40e6e8e339832dcc0c9c787cf0f85@ec2-44-199-22-207.compute-1.amazonaws.com:5432/ddmnm5bm93ii53";
    private final static String NAME = "nglxiuekikrqwe";
    private final static String PWD = "e36c325b1e86cb17efa754ba765e8e0ce5d40e6e8e339832dcc0c9c787cf0f85";
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
