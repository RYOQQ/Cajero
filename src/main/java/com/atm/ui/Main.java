package com.atm.ui;

import com.atm.model.CuentaBancaria;
import com.atm.service.CajeroService;

public class Main {
    public static void main( String[] args )
    {
        CajeroService cajeroService = new CajeroService();

        CuentaBancaria cuenta1 = new CuentaBancaria("123456", 50000.0, "Roque", true);

        CuentaBancaria cuenta2 = new CuentaBancaria("998877", 20000.0, "Martin", true);

        CuentaBancaria cuenta3 = new CuentaBancaria("114455", 10000.0, "Elias", false);

        try {
            cajeroService.depositar(cuenta1, 5000);
            cajeroService.extraer(cuenta1, 2000);
            cajeroService.transferir(cuenta1, cuenta2, 3000);
            cajeroService.consultarSaldo(cuenta1);

            cajeroService.depositar(cuenta2, 1000);
            cajeroService.extraer(cuenta2, 500);
            cajeroService.transferir(cuenta2, cuenta1, 2000);
            cajeroService.consultarSaldo(cuenta2);

            cajeroService.depositar(cuenta1, 7000);
            cajeroService.extraer(cuenta1, 10000);
            cajeroService.consultarSaldo(cuenta1);

            cajeroService.depositar(cuenta2, 3000);
            cajeroService.extraer(cuenta2, 1500);

            try {
                cajeroService.extraer(cuenta1, 999999); // saldo insuficiente
            } catch (Exception e) {
                System.out.println("Excepción: " + e.getMessage());
            }

            try {
                cajeroService.depositar(cuenta3, 1000); // cuenta inactiva
            } catch (Exception e) {
                System.out.println("Excepción: " + e.getMessage());
            }

            // Mostrar historial
            System.out.println("\n--- HISTORIAL CUENTA 1 ---");
            cajeroService.mostrarUltimasTransacciones(cuenta1);

            System.out.println("\n--- HISTORIAL CUENTA 2 ---");
            cajeroService.mostrarUltimasTransacciones(cuenta2);

        } catch (Exception e) {
        System.out.println("Error general: " + e.getMessage());
        }
    } 
}  
