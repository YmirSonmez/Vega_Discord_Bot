package me.ymir.vega.database;


import java.sql.*;

public class Connect {
    private Connection connection;
    private Statement statement;


    public Connect() {
        try {
             this.connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/vega","root","password");
             this.statement = this.connection.createStatement();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Statement getStatement() {
        return statement;
    }
    public Connection getConnection(){return this.connection;}
}
