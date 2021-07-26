package com.databasecomparation.Conections;

import com.databasecomparation.controller.Comparations;

import java.sql.*;
import java.time.LocalTime;
import static java.time.temporal.ChronoUnit.MILLIS;

public class FirebirdConnections {


    String dir = System.getProperty("user.dir");
    private final String masterurl = "jdbc:firebirdsql:localhost/3050:"+ dir + "\\databases\\";

    public Connection openSessionConnections(String db) throws ClassNotFoundException, SQLException {

        LocalTime tmini =  LocalTime.now();

        Connection con = null;
        String url = masterurl+db + ".fdb";
        Class.forName("org.firebirdsql.jdbc.FBDriver");
        con = DriverManager.getConnection(url, "sysdba", "1816");

        LocalTime tmfim =  LocalTime.now();
        String dif = String.valueOf(MILLIS.between(tmini,tmfim));

        Comparations.matTempos.put("Abertura_Firebird", dif);

        return con;
    }

    public Connection getNewConnections(String db) throws SQLException {

        Connection con = null;
        String url = masterurl+db;
        try {
            con = DriverManager.getConnection(url,"sysdba","1816");
        }
        catch(SQLException e) {
            System.out.println(e);

        }
        return con;
    }
}
