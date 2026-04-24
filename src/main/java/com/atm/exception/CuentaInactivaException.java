package com.atm.exception;

public class CuentaInactivaException extends Exception{
    public CuentaInactivaException(String mensaje){
        super(mensaje);
    }
}
