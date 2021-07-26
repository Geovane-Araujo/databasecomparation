package com.databasecomparation.Conections;

import com.databasecomparation.controller.Comparations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalTime;

import static java.time.temporal.ChronoUnit.MILLIS;

public class SqliteConnections {

    String dir = System.getProperty("user.dir");
    private String masterurl = "jdbc:sqlite:"+ dir + "\\databases\\";


    public Connection openSessionConnections(String db) throws SQLException {

        LocalTime tmini =  LocalTime.now();

        Connection con = null;
        String url = masterurl+db + ".db";
        con = DriverManager.getConnection(url);

        LocalTime tmfim =  LocalTime.now();
        String dif = String.valueOf(MILLIS.between(tmini,tmfim));

        Comparations.matTempos.put("Abertura_Sqlite", dif);
        return con;
    }

}
