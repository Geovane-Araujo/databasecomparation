package com.databasecomparation;

import com.databasecomparation.controller.*;
import com.databasecomparation.model.Empresas;

import java.sql.SQLException;
import java.util.*;

public class Main {




    public static void main(String[] args) throws InterruptedException {

        Comparations.openConections();

        ComparationsFirebird coFirebird = new ComparationsFirebird();
        ComparationsSqlite coSqlite = new ComparationsSqlite();
        ComparationsPostgres coPostgres = new ComparationsPostgres();
        ComparationsMysql comparationsMysql = new ComparationsMysql();

        List<Empresas> postgres = Comparations.readArchive();
        List<Empresas> mysql = Comparations.readArchive();
        List<Empresas> sqlite = Comparations.readArchive();
        List<Empresas> firebird = Comparations.readArchive();


        //Insert usando Atom sem thread
        System.out.println("Utilizando Atom");
        coFirebird.insertAtom(firebird,"Serial");
        coPostgres.insertAtom(postgres,"Serial");
        comparationsMysql.insertAtom(mysql,"Serial");
        coSqlite.insertAtom(sqlite,"Serial");
        Comparations.map();

        //Update usando Atom sem thread
        coFirebird.editingAtom(firebird,"Serial");
        coPostgres.editingAtom(postgres,"Serial");
        comparationsMysql.editingAtom(mysql,"Serial");
        coSqlite.editingAtom(sqlite,"Serial");
        Comparations.map();

        //Delete usando Atom sem thread
        coFirebird.deletAtom(firebird,"Serial");
        coPostgres.deletAtom(postgres,"Serial");
        comparationsMysql.deletAtom(mysql,"Serial");
        coSqlite.deletAtom(sqlite,"Serial");
        Comparations.map();

        //***************************************Modo Manual*********************************
        System.out.println("Modo Manual");
        //Insert modo Manual sem thread
        coFirebird.insertManual(firebird);
        coPostgres.insertManual(postgres);
        comparationsMysql.insertManual(mysql);
        coSqlite.insertManual(sqlite);

        //Update usando Manual sem thread
        coFirebird.editManual(firebird);
        coPostgres.editManual(postgres);
        comparationsMysql.editManual(mysql);
        coSqlite.editManual(sqlite);

        //Delete usando Manual sem thread
        coFirebird.deletManual(firebird);
        coPostgres.deletManual(postgres);
        comparationsMysql.deletManual(mysql);
        coSqlite.deletManual(sqlite);
        Comparations.map();


        //***************************************Usando Threads*********************************
        //Usando Threads
        coFirebird.executParallel(firebird);
        coSqlite.executParallel(sqlite);
        comparationsMysql.executParallel(mysql);
        coPostgres.executParallel(postgres);
        Comparations.map();
    }
}
