package com.databasecomparation.controller;

import com.atom.Atom;
import com.atom.Sql;
import com.databasecomparation.Conections.FirebirdConnections;
import com.databasecomparation.Conections.MysqlConnections;
import com.databasecomparation.Conections.PostgresConnections;
import com.databasecomparation.Conections.SqliteConnections;
import com.databasecomparation.model.Empresas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.ConnectException;
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.time.temporal.ChronoUnit.MILLIS;

public abstract class Comparations{


    public static Map<String, Object> matTempos = new LinkedHashMap<String, Object>();

    private static Atom at = new Atom();

    private static PostgresConnections pgcon = new PostgresConnections();
    private static MysqlConnections mysqlcon = new MysqlConnections();
    private static FirebirdConnections fbcon = new FirebirdConnections();
    private static SqliteConnections sqlitecon = new SqliteConnections();


    public static void openConections(){


        try{
            ComparationsSqlite.conSqlite = sqlitecon.openSessionConnections("sqlite_comparation");
            ComparationsFirebird.conFirebird = fbcon.openSessionConnections("firebird_comparation");
            ComparationsPostgres.conPostgres = pgcon.openSessionConnections("postgres_comparation");
            ComparationsMysql.conMysql = mysqlcon.openSessionConnections("mysql_comparation");
            System.out.println("Todas as conexões foram abertas");
        }
        catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }

    }

    public static List<Empresas> readArchive(){
        List<Empresas> listEmpresas = new ArrayList<>();
        Empresas empresas = new Empresas();
        LocalTime tmini =  LocalTime.now();
        String dir = System.getProperty("user.dir");

        String file = dir + "\\Arquivo\\empresas.ESTABELE";
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String linha = "";
            int i = 0;
            while((linha = br.readLine()) != null && i <= 10){

                String[] splitempresa = linha.split("\";");

                empresas.setCnpj(splitempresa[0].replace("\"", "") + splitempresa[1].replace("\"", "") + splitempresa[2].replace("\"", ""));
                empresas.setTipo(Integer.parseInt(splitempresa[3].replace("\"", "")));
                empresas.setFantasia(splitempresa[4].replace("\"", ""));
                empresas.setSituacao(splitempresa[5].replace("\"", ""));
                empresas.setMotivosituacao(splitempresa[7].replace("\"", ""));
                empresas.setNomecidadeexterior(splitempresa[8].replace("\"", ""));
                empresas.setPais(splitempresa[9].replace("\"", ""));
                empresas.setCnaeprincipal(splitempresa[11].replace("\"", ""));
                empresas.setTipologradouro(splitempresa[13].replace("\"", ""));
                empresas.setLogradouro(splitempresa[14].replace("\"", ""));
                empresas.setNumero(splitempresa[15].replace("\"", ""));
                empresas.setComplemento(splitempresa[16].replace("\"", ""));
                empresas.setBairro(splitempresa[17].replace("\"", ""));
                empresas.setCep(splitempresa[18].replace("\"", ""));
                empresas.setUf(splitempresa[19].replace("\"", ""));
                empresas.setMunicipio(Integer.parseInt(splitempresa[20].replace("\"", "")));
                empresas.setTelefoneprincipal(splitempresa[21].replace("\"", "") + splitempresa[22].replace("\"", ""));
                empresas.setTelefonesecundario(splitempresa[23].replace("\"", "") + splitempresa[24].replace("\"", ""));
                empresas.setEmail(splitempresa[27].replace("\"", ""));
                empresas.setSituacao(splitempresa[28].replace("\"", ""));

                listEmpresas.add(empresas);
                empresas = new Empresas();
                i++;
            }
        }catch (IOException iox){
            System.out.println(iox.getMessage());
        }
        LocalTime tmfim =  LocalTime.now();
        String dif = String.valueOf(MILLIS.between(tmini,tmfim));

        Comparations.matTempos.put("Leitura_Dados", dif);
        return listEmpresas;
    }

    public static void map(){
        System.out.println("Dados relativos ao Tempo (ms)");
        System.out.println("****************************************************************************************************************************************************|");
        System.out.println("                                  Programação Serial                             |                    Usando Threads Paralelas                      |");
        System.out.println("           Usando Atom                |           Método Manual                  |           Usando Atom           |          Método Manual         |");
        System.out.println("Banco           |Insert    |Update    |Delete    |Insert    |Update    |Delete   |Insert    |Update    |Delete     |Insert    |Update    |Delete    |");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("PostgreSql      |"+ retString54(matTempos.get("Time_Insert_Atom_Serial_Postgres").toString())+retString54(matTempos.get("Time_Edit_Atom_Serial_Postgres").toString())+retString54(matTempos.get("Time_Delete_Atom_Serial_Postgres").toString())+retString54(matTempos.get("Time_Insert_Manual_Postgres").toString())+retString54(matTempos.get("Time_Edit_Manual_Postgres").toString())+retString54(matTempos.get("Time_Delete_Manual_Postgres").toString())+retString54(matTempos.get("Time_Insert_Atom_Thread_Postgres").toString())+retString54(matTempos.get("Time_Edit_Atom_Thread_Postgres").toString())+retString54(matTempos.get("Time_Delete_Atom_Thread_Postgres").toString())+"          |          |          |");
        System.out.println("Firebird        |"+ retString54(matTempos.get("Time_Insert_Atom_Serial_Firebird").toString())+retString54(matTempos.get("Time_Edit_Atom_Serial_Firebird").toString())+retString54(matTempos.get("Time_Delete_Atom_Serial_Firebird").toString())+retString54(matTempos.get("Time_Insert_Manual_Firebird").toString())+retString54(matTempos.get("Time_Edit_Manual_Firebird").toString())+retString54(matTempos.get("Time_Delete_Manual_Firebird").toString())+retString54(matTempos.get("Time_Insert_Atom_Thread_Firebird").toString())+retString54(matTempos.get("Time_Edit_Atom_Thread_Firebird").toString())+retString54(matTempos.get("Time_Delete_Atom_Thread_Firebird").toString())+"          |          |          |");
        System.out.println("MySql           |"+ retString54(matTempos.get("Time_Insert_Atom_Serial_MySql").toString())+retString54(matTempos.get("Time_Edit_Atom_Serial_MySql").toString())+retString54(matTempos.get("Time_Delete_Atom_Serial_MySql").toString())+retString54(matTempos.get("Time_Insert_Manual_MySql").toString())+retString54(matTempos.get("Time_Edit_Manual_MySql").toString())+retString54(matTempos.get("Time_Delete_Manual_MySql").toString())+retString54(matTempos.get("Time_Insert_Atom_Thread_MySql").toString())+retString54(matTempos.get("Time_Edit_Atom_Thread_MySql").toString())+retString54(matTempos.get("Time_Delete_Atom_Thread_MySql").toString())+"          |          |          |");
        System.out.println("Sqlite          |"+ retString54(matTempos.get("Time_Insert_Atom_Serial_Sqlite").toString())+retString54(matTempos.get("Time_Edit_Atom_Serial_Sqlite").toString())+retString54(matTempos.get("Time_Delete_Atom_Serial_Sqlite").toString())+retString54(matTempos.get("Time_Insert_Manual_Sqlite").toString())+retString54(matTempos.get("Time_Edit_Manual_Sqlite").toString())+retString54(matTempos.get("Time_Delete_Manual_Sqlite").toString())+retString54(matTempos.get("Time_Insert_Atom_Thread_Sqlite").toString())+retString54(matTempos.get("Time_Edit_Atom_Thread_Sqlite").toString())+retString54(matTempos.get("Time_Delete_Atom_Thread_Sqlite").toString())+"          |          |          |");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------|\n");
    }

    public static String retString54(String replacement){

        String ret = "";
        int tamanho = replacement.length();
        String tab = "";
        if(tamanho < 10){
            for (int i = 0;i < (10 - replacement.length());i++){
                tab += " ";
            }
            tab +="|";
            ret = replacement + tab;
            return ret;
        } else{
            return replacement;
        }

    }
}
