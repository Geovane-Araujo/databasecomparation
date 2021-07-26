package com.databasecomparation.Conections;

import com.databasecomparation.controller.Comparations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalTime;

import static java.time.temporal.ChronoUnit.MILLIS;

public class MysqlConnections {

    public final String masterurl = "jdbc:mysql://localhost:3306/";


    public Connection openSessionConnections(String db) throws ClassNotFoundException,SQLException {

        LocalTime tmini =  LocalTime.now();

        Connection con = null;
        String url = masterurl+db;
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, "root", "1816");

        LocalTime tmfim =  LocalTime.now();
        String dif = String.valueOf(MILLIS.between(tmini,tmfim));

        Comparations.matTempos.put("Abertura_MySql", dif);
        return con;
    }

    public Connection getNewConnections(String db) throws SQLException {

        Connection con = null;
        String url = masterurl+db;
        try {
            con = DriverManager.getConnection(url,"root","1816");
        }
        catch(SQLException e) {
            System.out.println(e);

        }
        return con;
    }
}
