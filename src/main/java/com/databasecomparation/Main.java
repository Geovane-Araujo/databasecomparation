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
        povoahashTable();

        //Insert usando Atom sem thread
        //System.out.println("Utilizando Atom");
        coFirebird.insertAtom(firebird,"Serial");
        coPostgres.insertAtom(postgres,"Serial");
        comparationsMysql.insertAtom(mysql,"Serial");
        coSqlite.insertAtom(sqlite,"Serial");
        //Comparations.map();

        //Update usando Atom sem thread
        coFirebird.editingAtom(firebird,"Serial");
        coPostgres.editingAtom(postgres,"Serial");
        comparationsMysql.editingAtom(mysql,"Serial");
        coSqlite.editingAtom(sqlite,"Serial");
        //Comparations.map();

        //Delete usando Atom sem thread
        coFirebird.deletAtom(firebird,"Serial");
        coPostgres.deletAtom(postgres,"Serial");
        comparationsMysql.deletAtom(mysql,"Serial");
        coSqlite.deletAtom(sqlite,"Serial");
        //Comparations.map();

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


//        //***************************************Usando Threads*********************************
//        //Usando Threads
//        coFirebird.executParallel(firebird);
//        coSqlite.executParallel(sqlite);
//        comparationsMysql.executParallel(mysql);
//        coPostgres.executParallel(postgres);
//        //Comparations.map();
    }

    public static void povoahashTable(){
        Comparations.matTempos.put("Time_Insert_Atom_Serial_Postgres","");
        Comparations.matTempos.put("Time_Edit_Atom_Serial_Postgres","");
        Comparations.matTempos.put("Time_Delete_Atom_Serial_Postgres","");
        Comparations.matTempos.put("Time_Insert_Manual_Postgres","");
        Comparations.matTempos.put("Time_Edit_Manual_Postgres","");
        Comparations.matTempos.put("Time_Delete_Manual_Postgres","");
        Comparations.matTempos.put("Time_Insert_Atom_Thread_Postgres","");
        Comparations.matTempos.put("Time_Edit_Atom_Thread_Postgres","");
        Comparations.matTempos.put("Time_Delete_Atom_Thread_Postgres","");

        Comparations.matTempos.put("Time_Insert_Atom_Serial_Firebird","");
        Comparations.matTempos.put("Time_Edit_Atom_Serial_Firebird","");
        Comparations.matTempos.put("Time_Delete_Atom_Serial_Firebird","");
        Comparations.matTempos.put("Time_Insert_Manual_Firebird","");
        Comparations.matTempos.put("Time_Edit_Manual_Firebird","");
        Comparations.matTempos.put("Time_Delete_Manual_Firebird","");
        Comparations.matTempos.put("Time_Insert_Atom_Thread_Firebird","");
        Comparations.matTempos.put("Time_Edit_Atom_Thread_Firebird","");
        Comparations.matTempos.put("Time_Delete_Atom_Thread_Firebird","");

        Comparations.matTempos.put("Time_Insert_Atom_Serial_MySql","");
        Comparations.matTempos.put("Time_Edit_Atom_Serial_MySql","");
        Comparations.matTempos.put("Time_Delete_Atom_Serial_MySql","");
        Comparations.matTempos.put("Time_Insert_Manual_MySql","");
        Comparations.matTempos.put("Time_Edit_Manual_MySql","");
        Comparations.matTempos.put("Time_Delete_Manual_MySql","");
        Comparations.matTempos.put("Time_Insert_Atom_Thread_MySql","");
        Comparations.matTempos.put("Time_Edit_Atom_Thread_MySql","");
        Comparations.matTempos.put("Time_Delete_Atom_Thread_MySql","");

        Comparations.matTempos.put("Time_Insert_Atom_Serial_Sqlite","");
        Comparations.matTempos.put("Time_Edit_Atom_Serial_Sqlite","");
        Comparations.matTempos.put("Time_Delete_Atom_Serial_Sqlite","");
        Comparations.matTempos.put("Time_Insert_Manual_Sqlite","");
        Comparations.matTempos.put("Time_Edit_Manual_Sqlite","");
        Comparations.matTempos.put("Time_Delete_Manual_Sqlite","");
        Comparations.matTempos.put("Time_Insert_Atom_Thread_Sqlite","");
        Comparations.matTempos.put("Time_Edit_Atom_Thread_Sqlite","");
        Comparations.matTempos.put("Time_Delete_Atom_Thread_Sqlite","");
    }
}
