package com.atm.model;


import java.time.LocalDateTime;

public class Transaccion {
    private TipoTransaccion tipo;
    private Double monto;
    private LocalDateTime fechaHora;
    private String descripcion;
    
    public Transaccion(){
    }

    public Transaccion(TipoTransaccion tipo, Double monto, LocalDateTime fechaHora, String descripcion) {
        this.tipo = tipo;
        this.monto = monto;
        this.fechaHora = fechaHora;
        this.descripcion = descripcion;
    }

    public TipoTransaccion getTipo() {
        return tipo;
    }
    public void setTipo(TipoTransaccion tipo) {
        this.tipo = tipo;
    }

    public Double getMonto() {
        return monto;
    }
    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Transaccion(" +
                "monto=" + monto +
                ", fechaHora=" + fechaHora +
                ", descripcion='" + descripcion + '\'' +
                ')';
    }
}
