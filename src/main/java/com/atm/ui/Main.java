package com.atm.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.atm.exception.CuentaInactivaException;
import com.atm.exception.LimiteExtraccionExcedidoException;
import com.atm.exception.SaldoInsuficienteException;
import com.atm.model.CuentaBancaria;
import com.atm.service.CajeroService;
import com.atm.util.FormatoUtil;

public class Main {
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);
        CajeroService cajeroService = new CajeroService();

        CuentaBancaria cuenta1 = new CuentaBancaria("123456", 50000.0, "Roque", true);

        CuentaBancaria cuenta2 = new CuentaBancaria("998877", 20000.0, "RYOQ", true);

        int opcion;
        boolean salir = false;

        do{
            System.out.println("\n==== CAJERO AUTOMATICO ====");  
            System.out.println("Seleccione una opción: ");
            System.out.println("1. Depositar");
            System.out.println("2. Extraer");
            System.out.println("3. Transferir");
            System.out.println("4. Consultar saldo");
            System.out.println("5. Ver últimas 10 transacciones");
            System.out.println("6. Salir");

            try{
                opcion = sc.nextInt();

                switch (opcion) {
                    case 1 :{
                        System.out.print("Ingrese monto a depositar: ");
                        double monto = sc.nextDouble();
                        cajeroService.depositar(cuenta1, monto);
                        System.out.println("Deposito realizado.");
                        break;
                    }

                    case 2 :{
                        System.out.print("Ingrese monto a extraer");;
                        double monto = sc.nextDouble();
                        cajeroService.extraer(cuenta1, monto);
                        System.out.println("Extraccion realizada.");
                        break;
                    }

                   case 3 :{
                       System.out.print("Ingrese monto a transferir.");
                       double monto = sc.nextDouble();
                       cajeroService.transferir(cuenta1, cuenta2, monto);
                       System.out.println("Transferencia realizada.");
                       break;
                    }

                   case 4 :{
                       double saldo = cajeroService.consultarSaldo(cuenta1);
                       System.out.println("Saldo actual: " + FormatoUtil.formatearMoneda(saldo));
                       break;
                    }

                   case 5 :{
                       cajeroService.mostrarUltimasTransacciones(cuenta1);
                       break;
                    }

                   case 6 :{
                       salir = true;
                       System.out.println("Hasta la proxima.");
                       break;
                    }

                    default :{ 
                        System.out.println("Opcion invalida.");
                        break;
                    }
                }
            }
                
            catch (InputMismatchException e){
                System.out.println("Error: debe ingresar un valor valido.");
                sc.nextLine();
            }

            catch(CuentaInactivaException | SaldoInsuficienteException | LimiteExtraccionExcedidoException e){
                System.out.println("Error: " + e.getMessage());  
            }

            catch(IllegalArgumentException e){
                System.out.println("Error: " + e.getMessage());
            }
            
        } while (!salir);
        sc.close();
    }
}

