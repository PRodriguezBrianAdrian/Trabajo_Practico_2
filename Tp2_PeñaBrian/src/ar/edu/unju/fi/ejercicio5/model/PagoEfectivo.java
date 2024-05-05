package ar.edu.unju.fi.ejercicio5.model;

import java.time.LocalDate;

import ar.edu.unju.fi.ejercicio5.interfaces.IPago;

public class PagoEfectivo implements IPago {
	private double montoPagado;
	private LocalDate fechaDePago;
	public PagoEfectivo(double montoPagado, LocalDate fechaDePago) {
		this.montoPagado = montoPagado;
		this.fechaDePago = fechaDePago;
	}
	public double getMontoPagado() {
		return montoPagado;
	}
	public void setMontoPagado(double montoPagado) {
		this.montoPagado = montoPagado;
	}
	public LocalDate getFechaDePago() {
		return fechaDePago;
	}
	public void setFechaDePago(LocalDate fechaDePago) {
		this.fechaDePago = fechaDePago;
	}
	public void realizarPago(double monto) {
		setMontoPagado(this.montoPagado-(this.montoPagado*10/100));
		
	}
	@Override
	public void imprimirRecibo() {
		System.out.println("------Recibo------");
		System.out.println("Fecha de pago: "+getFechaDePago());
		System.out.println("Monto pagado: $"+getMontoPagado());
	}
}
