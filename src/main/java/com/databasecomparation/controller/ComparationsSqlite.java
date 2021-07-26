package com.databasecomparation.controller;

import com.atom.Atom;
import com.databasecomparation.Conections.FirebirdConnections;
import com.databasecomparation.Conections.SqliteConnections;
import com.databasecomparation.model.Empresas;

import java.sql.*;
import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.time.temporal.ChronoUnit.MILLIS;

public class ComparationsSqlite implements Runnable{

    public static Connection conSqlite;
    private static Atom at = new Atom();

    public void insertAtom(List<Empresas> emp,String origin){

        LocalTime tmini =  LocalTime.now();
        int i = 0;
        for (Empresas empresa: emp) {
            try{
                int scalar = at.insertedOne(empresa,Empresas.class,conSqlite);
                empresa.setId(scalar);
                System.out.println("Inserindo no Sqlite");
            }
            catch (SQLException | IllegalAccessException ex){
                System.out.println(ex.getMessage());
            }
            emp.set(i,empresa);
            i++;
        }

        LocalTime tmfim =  LocalTime.now();
        String dif = String.valueOf(MILLIS.between(tmini,tmfim));


        Comparations.matTempos.put("Time_Insert_Atom_"+origin+"_Sqlite", dif);
    }

    public void editingAtom(List<Empresas> emp,String origin){
        LocalTime tmini =  LocalTime.now();

        for (Empresas empresa: emp) {
            try{
                at.editingOne(empresa,Empresas.class,conSqlite,empresa.getId());
                System.out.println("Editando no Sqlite");
            }
            catch (SQLException | IllegalAccessException ex){
                System.out.println();
            }
        }

        LocalTime tmfim =  LocalTime.now();
        String dif = String.valueOf(MILLIS.between(tmini,tmfim));

        Comparations.matTempos.put("Time_Edit_Atom_"+origin+"_Sqlite", dif);
    }

    public void deletAtom(List<Empresas> emp,String origin){
        LocalTime tmini =  LocalTime.now();

        for (Empresas empresa: emp) {
            try{
                at.deleted(conSqlite,empresa.getId(),"empresas");
                System.out.println("Excluido no Sqlite" );
            }
            catch (SQLException ex){
                System.out.println();
            }
        }

        LocalTime tmfim =  LocalTime.now();
        String dif = String.valueOf(MILLIS.between(tmini,tmfim));

        Comparations.matTempos.put("Time_Delete_Atom_"+origin+"_Sqlite", dif);
    }

    public void insertManual(List<Empresas> emp){

        LocalTime tmini =  LocalTime.now();
        int i = 0;
        int scalar = 0;
        String sql = "INSERT INTO empresas( cnpj, tipo, fantasia, situacao, datasituacao, motivosituacao, nomecidadeexterior, pais," +
                "inicioatividade, cnaeprincipal, tipologradouro, logradouro, numero, complemento, bairro, cep, uf, municipio, " +
                "telefoneprincipal, telefonesecundario, email, situacaoespecial, datasituacaoespecial) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        try{
            PreparedStatement stmt = conSqlite.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            for (Empresas empresas: emp) {

                stmt.setString(1, empresas.getCnpj());
                stmt.setInt(2, empresas.getTipo());
                stmt.setString(3, empresas.getFantasia());
                stmt.setString(4, empresas.getSituacao());
                stmt.setDate(5, null);
                stmt.setString(6, empresas.getMotivosituacao());
                stmt.setString(7, empresas.getNomecidadeexterior());
                stmt.setString(8, empresas.getPais());
                stmt.setDate(9, empresas.getInicioatividade());
                stmt.setString(10, empresas.getCnaeprincipal());
                stmt.setString(11, empresas.getTipologradouro());
                stmt.setString(12, empresas.getLogradouro());
                stmt.setString(13, empresas.getNumero());
                if(empresas.getComplemento().length() > 50)
                    stmt.setString(14, empresas.getComplemento().substring(0,49));
                else
                    stmt.setString(14, empresas.getComplemento());
                stmt.setString(15, empresas.getBairro());
                stmt.setString(16, empresas.getCep());
                stmt.setString(17, empresas.getUf());
                stmt.setInt(18, empresas.getMunicipio());
                stmt.setString(19, empresas.getTelefoneprincipal());
                stmt.setString(20, empresas.getTelefonesecundario());
                stmt.setString(21, empresas.getEmail());
                stmt.setString(22, empresas.getSituacaoespecial());
                stmt.setDate(23, null);
                stmt.execute();

                ResultSet rs = stmt.getGeneratedKeys();
                while (rs.next()){
                    scalar = rs.getInt(1);
                }
                empresas.setId(scalar);
                emp.set(i,empresas);
                i++;
            }
            stmt.close();
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }

        LocalTime tmfim =  LocalTime.now();
        String dif = String.valueOf(MILLIS.between(tmini,tmfim));

        Comparations.matTempos.put("Time_Insert_Manual_Sqlite", dif);
    }

    public void editManual(List<Empresas> emp){

        LocalTime tmini =  LocalTime.now();
        int i = 0;
        int scalar = 0;
        String sql = "UPDATE empresas SET cnpj = ?,tipo = ?,fantasia = ?,situacao = ?,datasituacao = ?,motivosituacao = ?," +
                "nomecidadeexterior = ?,pais = ?,inicioatividade = ?,cnaeprincipal = ?,tipologradouro = ?,logradouro = ?," +
                "numero = ?,complemento = ?,bairro = ?,cep = ?,uf = ?,municipio = ?,telefoneprincipal = ?," +
                "telefonesecundario = ?,email = ?,situacaoespecial = ?,datasituacaoespecial = ? WHERE id = ?";

        try{
            PreparedStatement stmt = conSqlite.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            for (Empresas empresas: emp) {

                stmt.setString(1, empresas.getCnpj());
                stmt.setInt(2, empresas.getTipo());
                stmt.setString(3, empresas.getFantasia());
                if(empresas.getSituacao().length() > 2)
                    stmt.setString(4, empresas.getSituacao().substring(0,1));
                else
                    stmt.setString(4, empresas.getSituacao());
                stmt.setDate(5, empresas.getDatasituacao());
                stmt.setString(6, empresas.getMotivosituacao());
                stmt.setString(7, empresas.getNomecidadeexterior());
                stmt.setString(8, empresas.getPais());
                stmt.setDate(9, empresas.getInicioatividade());
                stmt.setString(10, empresas.getCnaeprincipal());
                stmt.setString(11, empresas.getTipologradouro());
                stmt.setString(12, empresas.getLogradouro());
                stmt.setString(13, empresas.getNumero());
                if(empresas.getComplemento().length() > 50)
                    stmt.setString(14, empresas.getComplemento().substring(0,49));
                else
                    stmt.setString(14, empresas.getComplemento());
                stmt.setString(15, empresas.getBairro());
                stmt.setString(16, empresas.getCep());
                stmt.setString(17, empresas.getUf());
                stmt.setInt(18, empresas.getMunicipio());
                stmt.setString(19, empresas.getTelefoneprincipal());
                stmt.setString(20, empresas.getTelefonesecundario());
                stmt.setString(21, empresas.getEmail());
                stmt.setString(22, empresas.getSituacaoespecial());
                stmt.setDate(23, empresas.getDatasituacaoespecial());
                stmt.setInt(24, empresas.getId());
                stmt.execute();
            }
            stmt.close();

        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }

        LocalTime tmfim =  LocalTime.now();
        String dif = String.valueOf(MILLIS.between(tmini,tmfim));

        Comparations.matTempos.put("Time_Edit_Manual_Sqlite", dif);
    }

    public void deletManual(List<Empresas> emp){
        LocalTime tmini =  LocalTime.now();
        int i = 0;
        int scalar = 0;
        String sql = "delete from empresas where id = ?";
        try{

            PreparedStatement stmt = conSqlite.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            for (Empresas empresas: emp) {
                stmt.setInt(1, empresas.getId());
                stmt.execute();
            }
            stmt.close();
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }

        LocalTime tmfim =  LocalTime.now();
        String dif = String.valueOf(MILLIS.between(tmini,tmfim));

        Comparations.matTempos.put("Time_Delete_Manual_Sqlite", dif);
    }

    public void executParallel(List<Empresas> lsempresas){

        new Thread(){
            @Override
            public void run() {
                insertAtom(lsempresas,"Thread");
                editingAtom(lsempresas,"Thread");
                deletAtom(lsempresas,"Thread");
                Comparations.map();
            }
        }.start();
    }

    @Override
    public void run() {

    }
}
