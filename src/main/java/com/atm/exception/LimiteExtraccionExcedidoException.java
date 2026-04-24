package com.atm.exception;

public class LimiteExtraccionExcedidoException extends Exception{
    public LimiteExtraccionExcedidoException(String mensaje){
        super(mensaje);
    }
}
