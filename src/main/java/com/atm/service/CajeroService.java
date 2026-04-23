package com.atm.service;

import com.atm.exception.CuentaInactivaException;
import com.atm.exception.LimiteExtraccionExcedidoException;
import com.atm.exception.SaldoInsuficienteException;
import com.atm.model.CuentaBancaria;
import com.atm.model.TipoTransaccion;
import com.atm.util.FormatoUtil;

import java.time.LocalDateTime;
import java.util.List;

public class CajeroService {
    private static final double LimiteExtraccion = 10000.0;

    public void depositar(CuentaBancaria cuenta, double monto) throws CuentaInactivaException{
        validarCuentaActiva(cuenta)

        if(monto <= 0){
            throw new IllegalArgumentException("El monto a depositar debe ser positivo y mayor a 0")
        }

        cuenta.setSaldo(cuenta.getSaldo() + monto);
        registrarOperacion(cuenta, TipoTransaccion.DEPOSITO, monto, cuenta.getSaldo(), "Deposito realizado correctamente");
    }
    
}
