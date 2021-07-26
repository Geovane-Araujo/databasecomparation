package com.databasecomparation.Conections;

import com.databasecomparation.controller.Comparations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalTime;

import static java.time.temporal.ChronoUnit.MILLIS;

public class PostgresConnections {

    public String masterUrl = "jdbc:postgresql://localhost:5432/";


    public Connection openSessionConnections(String db) throws ClassNotFoundException, SQLException {
        Connection con = null;
        LocalTime tmini =  LocalTime.now();

        String url = masterUrl+db;
        Class.forName("org.postgresql.Driver");
        String senha = "1816";
        con = DriverManager.getConnection(url, "postgres", senha);

        LocalTime tmfim =  LocalTime.now();
        String dif = String.valueOf(MILLIS.between(tmini,tmfim));

        Comparations.matTempos.put("Abertura_Postgres", dif);

        return con;
    }

    public Connection getNewConnections(String db) throws SQLException {

        Connection con = null;
        String url = masterUrl+db;
        try {
            String senha = "1816";
            con = DriverManager.getConnection(url,"postgres",senha);
        }
        catch(SQLException e) {
            System.out.println(e);
        }
        return con;
    }
}
