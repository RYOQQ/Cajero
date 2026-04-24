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
        validarCuentaActiva(cuenta);

        if(monto <= 0){
            throw new IllegalArgumentException("El monto a depositar debe ser positivo y mayor a 0");
        }

        cuenta.setSaldo(cuenta.getSaldo() + monto);
        registrarOperacion(cuenta, TipoTransaccion.DEPOSITO, monto, cuenta.getSaldo(), "Deposito realizado correctamente");
    }

    public void extraer(CuentaBancaria cuenta, double monto)
        throws CuentaInactivaException, SaldoInsuficienteException, LimiteExtraccionExcedidoException{

        validarCuentaActiva(cuenta);
    
        if(monto <= 0){
            throw new IllegalArgumentException("El monto a extraer debe ser positivo y mayor a 0");
        }

        if (monto > LimiteExtraccion) {
            throw new LimiteExtraccionExcedidoException("No se puede extraer mas de $10.000 por operacion.");
        }

        if(cuenta.getSaldo() < monto){
            throw new SaldoInsuficienteException("Saldo insuficiente para la extraccion.");
        }

        cuenta.setSaldo(cuenta.getSaldo() - monto);
        registrarOperacion(cuenta, TipoTransaccion.EXTRACCION, monto, cuenta.getSaldo(), "Extraccion realizada correctamente");
    }

    public void transferir(CuentaBancaria origen, CuentaBancaria destino, double monto)
            throws CuentaInactivaException, SaldoInsuficienteException, LimiteExtraccionExcedidoException{
        
        validarCuentaActiva(origen);
        validarCuentaActiva(destino);

        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a extraer debe ser positivo y mayor a 0");
        }

        if (origen.getSaldo() < monto) {
            throw new SaldoInsuficienteException("Saldo insuficiente.");
        }

        origen.setSaldo(origen.getSaldo() - monto);
        destino.setSaldo(destino.getSaldo() + monto);

        registrarOperacion(origen, TipoTransaccion.TRANSFERENCIA, monto, origen.getSaldo(), "Transferencia enviada." + destino.getNumeroCuenta());

        registrarOperacion(destino, TipoTransaccion.TRANSFERENCIA, monto, destino.getSaldo(), "Transferencia recibida." + origen.getNumeroCuenta());
        }

        public double consultarSaldo(CuentaBancaria cuenta) throws CuentaInactivaException{
            validarCuentaActiva(cuenta);

            registrarOperacion(cuenta, TipoTransaccion.CONSULTA, 0, cuenta.getSaldo(), "Consulta de saldo");
            return cuenta.getSaldo();
        }

        public void mostrarUltimasTransacciones(CuentaBancaria cuenta){
            List<String> historial = cuenta.getHistorialTransacciones();

            int inicio = Math.max(0, historial.size() - 10);

            for(int i = inicio; i < historial.size(); i++){
                System.out.println(historial.get(i));
            }
        }

        private void validarCuentaActiva(CuentaBancaria cuenta) throws CuentaInactivaException{
            if (!cuenta.isActiva()) {
                throw new CuentaInactivaException("La cuenta esta incativa.");
            }
        }
        private void registrarOperacion(CuentaBancaria cuenta, TipoTransaccion tipo, double monto, double saldoResultante, String descripcion){
            LocalDateTime ahora = LocalDateTime.now();
            StringBuilder sb = new StringBuilder();
            sb.append("[")
                .append(FormatoUtil.formatearFechaHora(ahora))
                .append("] ")
                .append(tipo)
                .append(": ")
                .append(FormatoUtil.formatearMoneda(monto))
                .append(" | Saldo: ")
                .append(FormatoUtil.formatearMoneda(saldoResultante))
                .append(" | ")
                .append(descripcion);
            cuenta.agregarTransaccion(sb.toString());
        }
}

