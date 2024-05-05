package ar.edu.unju.fi.ejercicio5.model;

import java.time.LocalDate;

import ar.edu.unju.fi.ejercicio5.interfaces.IPago;

public class PagoTarjeta implements IPago {
	private String numeroDeTarjeta;
	private LocalDate fechaDePago;
	private double montoPagado;
	public PagoTarjeta(String numeroDeTarjeta, LocalDate fechaDePago, double montoPagado) {
		this.numeroDeTarjeta = numeroDeTarjeta;
		this.fechaDePago = fechaDePago;
		this.montoPagado = montoPagado;
	}
	public String getNumeroDeTarjeta() {
		return numeroDeTarjeta;
	}
	public void setNumeroDeTarjeta(String numeroDeTarjeta) {
		this.numeroDeTarjeta = numeroDeTarjeta;
	}
	public LocalDate getFechaDePago() {
		return fechaDePago;
	}
	public void setFechaDePago(LocalDate fechaDePago) {
		this.fechaDePago = fechaDePago;
	}
	public double getMontoPagado() {
		return montoPagado;
	}
	public void setMontoPagado(double montoPagado) {
		this.montoPagado = montoPagado;
	}
	
	@Override
	public void realizarPago(double monto){
		setMontoPagado(this.montoPagado+(this.montoPagado*15/100));
	}
	@Override
	public void imprimirRecibo() {	
		System.out.println("------Recibo------");
		System.out.println("Numero de Tarjeta: "+getNumeroDeTarjeta());
		System.out.println("Fecha de pago: "+getFechaDePago());
		System.out.println("Monto pagado: $"+getMontoPagado());
	}
	

}
