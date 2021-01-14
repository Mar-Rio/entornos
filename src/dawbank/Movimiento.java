/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dawbank;

import java.time.LocalDateTime;

/**
 *
 * @author 52659401g
 */
public class Movimiento {
    LocalDateTime fecha;
    double cantidad, saldoFinal;

    public Movimiento( double cantidad, double saldoFinal) {
        this.fecha = LocalDateTime.now();
        this.cantidad = cantidad;
        this.saldoFinal = saldoFinal;
    }
    
}
