package com.atm.model;

import java.util.ArrayList;

public class CuentaBancaria {
    private final String numeroCuenta;
    private Double saldo;
    private String titular;
    private Boolean activa;
    private ArrayList<String> historialTransacciones;

    
    public CuentaBancaria() {
        this.numeroCuenta = "NADA";
        this.historialTransacciones = new ArrayList<>();
    }

    public CuentaBancaria(String numeroCuenta, Double saldo, String titular, Boolean activa) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.titular = titular;
        this.activa = activa;
        this.historialTransacciones = new ArrayList<>();
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public Double getSaldo() {
        return saldo;
    }
    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getTitular() {
        return titular;
    }
    public void setTitular(String titular) {
        this.titular = titular;
    }

    public Boolean isActiva() {
        return activa;
    }
    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    public ArrayList<String> getHistorialTransacciones(String registro){
        historialTransacciones.add(registro);
    }

    @Override
    public String toString() {
        return "CuentaBancaria(" +
                "numeroCuenta='" + numeroCuenta + '\'' +
                ", saldo=" + saldo +
                ", titular='" + titular + '\'' +
                ", activa=" + activa +
                ')';
    }

    
}
