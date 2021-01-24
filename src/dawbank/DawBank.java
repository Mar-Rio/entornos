package dawbank;

import java.util.Scanner;

public class DawBank {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String IBAN, titular;
        boolean cuentaCreada = false;
        do {
            System.out.println("CREAR CUENTA:\nIntroducir datos...\nIBAN:");
            IBAN = sc.nextLine();
            System.out.println("TITULAR:");
            titular = sc.nextLine();
            //COMPROBAR QUE IBAN TIENE 2 MAYUSCULAS Y 22 NUMEROS.
            //EL NOMBRE CONTIENE 3 PALABRAS SEPARADAS POR 2 ESPACIOS.
            if (IBAN.matches("[A-Z]{2}\\d{22}") && titular.matches("[a-zA-Z]+"
                    + "\\s[a-zA-Z]+\\s[a-zA-Z]+")) {
                CuentaBancaria cuenta = new CuentaBancaria(IBAN, titular);
                cuentaCreada = true;

//               PRUEBA 100 MOVIMIENTOS MAXIMOS        
//                for (int i = 0; i < 102; i++) {
//                    cuenta.ingreso(i);                    
//                }
                String respuesta;
                do {
                    respuesta = menu();
                    switch (respuesta) {
                        case "1":
                            datosCuenta(cuenta);
                            break;
                        case "2":
                            iban(cuenta);
                            break;
                        case "3":
                            titular(cuenta);
                            break;
                        case "4":
                            saldo(cuenta);
                            break;
                        case "5":
                            ingresar(cuenta);
                            break;
                        case "6":
                            retirar(cuenta);
                            break;
                        case "7":
                            movimientos(cuenta);
                            break;
                        case "8":
                            System.out.println("Gracias por usar nuestra"
                                    + " aplicacion");
                            return;
                        default:
                            System.out.println("Debe seleccionar un numero"
                                    + " correcto");
                    }
                } while (true);

            } else {    //CUENTA NO CREADA.
                System.out.println("\033[031mAVISO: Datos introducidos"
                        + " incorrectos");
            }
        } while (!cuentaCreada);

    }

    public static String menu() {

        String respuesta;
        System.out.println("BANCO");
        System.out.println("1-Datos de la cuenta.");
        System.out.println("2-IBAN.");
        System.out.println("3-Titular.");
        System.out.println("4-Saldo.");
        System.out.println("5-Ingreso.");
        System.out.println("6-Retirada.");
        System.out.println("7-Movimientos");
        System.out.println("8-Salir.\n");
        respuesta = sc.nextLine();

        return respuesta;
    }

    public static void datosCuenta(CuentaBancaria cuenta) {
        System.out.println(cuenta);
    }

    public static void iban(CuentaBancaria cuenta) {
        System.out.println("El IBAN es: " + cuenta.getIban());
    }

    public static void titular(CuentaBancaria cuenta) {
        System.out.println("El titular es: " + cuenta.getTitular());
    }

    public static void saldo(CuentaBancaria cuenta) {
        System.out.println("El saldo es: " + cuenta.getSaldo());
    }

    public static void ingresar(CuentaBancaria cuenta) {
        double cantidad = 0;
        boolean numeroCorrecto = false, ingresoCorrecto;
        do {
            System.out.println("Dinero a ingresar:");
            try {
                cantidad = Double.parseDouble(sc.nextLine());
                numeroCorrecto = true;// ENTRADA NUMERICA CORRECTA
            } catch (NumberFormatException e) {
                System.out.println("Introduzca numericamente los euros");
            }
        } while (!numeroCorrecto);
        ingresoCorrecto = cuenta.ingreso(cantidad);
        if (ingresoCorrecto) {  //NUMERO MAYOR QUE CERO.
            System.out.println("Ingresado correctamente " + cantidad
                    + " euros.");
        } else {
            System.out.println(cuenta.RED + "Ingrese cifra positiva.");
        }
    }

    public static void retirar(CuentaBancaria cuenta) {
        double cantidad = 0;
        boolean numeroCorrecto = false, retiradaCorrecta;
        do {
            System.out.println("Dinero a retirar:");
            try {
                cantidad = Double.parseDouble(sc.nextLine());
                numeroCorrecto = true; // ENTRADA NUMERICA CORRECTA
            } catch (NumberFormatException e) {
                System.out.println(cuenta.RED + "Introduzca numericamente "
                        + "los euros");
            }
        } while (!numeroCorrecto);
        if (cantidad > 0) {//NO SE ACEPTA NUMERO NEGATIVO.
            retiradaCorrecta = cuenta.reintegro(cantidad);
            if (retiradaCorrecta) {//SALDO SUFICIENTE
                System.out.println("Reintegro correcto de " + cantidad
                        + " euros.");
            } else {
                System.out.println(cuenta.RED + "No tiene suficiente saldo.");
            }
        } else {
            System.out.println(cuenta.RED + "Introduzca cifra positiva.");
        }
    }

    public static void movimientos(CuentaBancaria cuenta) {
        for (Movimiento movimiento : cuenta.getHistorial()) {
            System.out.println(movimiento);
        }
    }
}
