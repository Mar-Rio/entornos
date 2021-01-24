package dawbank;

import java.time.LocalDateTime;

public class Movimiento {

    LocalDateTime fecha;
    private final double cantidad,
            saldoFinal;
    private static int contador = 0;
    private final int movimientoNumero;

    public Movimiento(double cantidad, double saldoFinal) {
        this.fecha = LocalDateTime.now();
        this.cantidad = cantidad;
        this.saldoFinal = saldoFinal;
        movimientoNumero = ++contador;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public double getCantidad() {
        return cantidad;
    }

    public double getSaldoFinal() {
        return saldoFinal;
    }

    @Override
    public String toString() {
        return movimientoNumero + ". " + "Fecha:" + fecha + ", Cantidad:"
                + cantidad + ", Saldo:" + saldoFinal + ".";
    }

}
