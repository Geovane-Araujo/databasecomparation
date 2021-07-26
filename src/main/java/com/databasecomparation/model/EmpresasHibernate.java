package com.databasecomparation.model;

import com.atom.Alias;
import com.atom.Ignore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;


@Entity
public class EmpresasHibernate implements Serializable {

    @Ignore
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String cnpj;

    private int tipo;

    private String fantasia;

    private String situacao;

    private Date datasituacao;

    private String motivosituacao;

    private String nomecidadeexterior;

    private String pais;

    private Date inicioatividade;

    private String cnaeprincipal;

    private String tipologradouro;

    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;

    private String cep;

    private String uf;

    private int municipio;

    private String telefoneprincipal;

    private String telefonesecundario;

    private String email;

    private String situacaoespecial;

    private Date datasituacaoespecial;

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }


    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public int getTipo() {
        return tipo;
    }


    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getFantasia() {
        return fantasia;
    }


    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }

    public String getSituacao() {
        return situacao;
    }


    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Date getDatasituacao() {
        return datasituacao;
    }


    public void setDatasituacao(Date datasituacao) {
        this.datasituacao = datasituacao;
    }

    public String getMotivosituacao() {
        return motivosituacao;
    }


    public void setMotivosituacao(String motivosituacao) {
        this.motivosituacao = motivosituacao;
    }

    public String getNomecidadeexterior() {
        return nomecidadeexterior;
    }


    public void setNomecidadeexterior(String nomecidadeexterior) {
        this.nomecidadeexterior = nomecidadeexterior;
    }

    public String getPais() {
        return pais;
    }


    public void setPais(String pais) {
        this.pais = pais;
    }

    public Date getInicioatividade() {
        return inicioatividade;
    }


    public void setInicioatividade(Date inicioatividade) {
        this.inicioatividade = inicioatividade;
    }

    public String getCnaeprincipal() {
        return cnaeprincipal;
    }


    public void setCnaeprincipal(String cnaeprincipal) {
        this.cnaeprincipal = cnaeprincipal;
    }

    public String getTipologradouro() {
        return tipologradouro;
    }


    public void setTipologradouro(String tipologradouro) {
        this.tipologradouro = tipologradouro;
    }

    public String getLogradouro() {
        return logradouro;
    }


    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }


    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }


    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }


    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }


    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getUf() {
        return uf;
    }


    public void setUf(String uf) {
        this.uf = uf;
    }

    public int getMunicipio() {
        return municipio;
    }


    public void setMunicipio(int municipio) {
        this.municipio = municipio;
    }

    public String getTelefoneprincipal() {
        return telefoneprincipal;
    }


    public void setTelefoneprincipal(String telefoneprincipal) {
        this.telefoneprincipal = telefoneprincipal;
    }

    public String getTelefonesecundario() {
        return telefonesecundario;
    }


    public void setTelefonesecundario(String telefonesecundario) {
        this.telefonesecundario = telefonesecundario;
    }

    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getSituacaoespecial() {
        return situacaoespecial;
    }


    public void setSituacaoespecial(String situacaoespecial) {
        this.situacaoespecial = situacaoespecial;
    }

    public Date getDatasituacaoespecial() {
        return datasituacaoespecial;
    }


    public void setDatasituacaoespecial(Date datasituacaoespecial) {
        this.datasituacaoespecial = datasituacaoespecial;
    }

}

