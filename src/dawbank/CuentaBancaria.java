package dawbank;

import java.util.*;

public class CuentaBancaria {

    public final String RED = "\033[31m";//IMPRIME EN ROJO LOS MENSAJES.
    private final String iban,
            titular;
    private double saldo;
    private final Deque<Movimiento> historial = new ArrayDeque<>();

    public CuentaBancaria(String iban, String titular) {
        this.iban = iban;
        this.titular = titular;
        this.saldo = 0;
    }

    public void setHistorial(Movimiento ultimo) {
        if (historial.size() < 100) {
            historial.push(ultimo);//INTRODUCE EN PRIMERA POSICION.
        } else {
            historial.removeLast();//ELIMINA EL ULTIMO.
            historial.push(ultimo);
        }
    }

    public String getIban() {
        return iban;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public Deque<Movimiento> getHistorial() {
        return historial;
    }

    public boolean ingreso(double cantidad) {
        boolean mayor0 = false;//INGRESA SI CANTIDAD MAYOR QUE CERO.
        if (cantidad > 0) {
            if (cantidad > 3000) {
                System.out.println(RED + "AVISO: Notificar a hacienda.");
            }
            saldo = saldo + cantidad;
            mayor0 = true;
            setHistorial(new Movimiento(cantidad, saldo));
        }
        return mayor0;
    }

    public boolean reintegro(double cantidad) {
        boolean reintegroOK = false;//SALDO RESTANTE MAYOR QUE -50.
        if (cantidad > 0) {
            if (saldo - cantidad > -50) {
                if (saldo - cantidad < 0) {
                    System.out.println(RED + "AVISO: Saldo negativo.");
                }
                if (cantidad > 3000) {
                    System.out.println(RED + "AVISO: Notificar a hacienda.");
                }
                saldo = saldo - cantidad;
                reintegroOK = true;
                setHistorial(new Movimiento(-cantidad, saldo));
            }
        }
        return reintegroOK;
    }

    @Override
    public String toString() {
        return "CuentaBancaria{" + "IBAN:" + iban + ", Titular:" + titular 
                + ", Saldo:" + saldo + '}';
    }

}
