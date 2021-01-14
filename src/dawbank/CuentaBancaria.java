/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dawbank;

import java.time.LocalDateTime;
import java.util.*;

/**
 *
 * @author 52659401g
 */
public class CuentaBancaria {

    private String iban,
            titular;
    private double saldo;
    private Deque<Movimiento> historial = new ArrayDeque<>();

    public CuentaBancaria(String iban, String titular) {
        this.iban = iban;
        this.titular = titular;
        this.saldo = 0;
    }

    public void setHistorial(Movimiento ultimo) {
        if (historial.size() < 100) {
            historial.push(ultimo);
        } else {
            historial.removeLast();
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
        boolean mayor0 = false;
        if (cantidad > 0) {
            if (cantidad > 3000) {
                System.out.println("AVISO: Notificar a hacienda.");
            }
            saldo = saldo + cantidad;
            mayor0 = true;
            setHistorial(new Movimiento(cantidad, saldo));
        } 
        return mayor0;
    }

    public boolean reintegro(double cantidad) {

    }
}
