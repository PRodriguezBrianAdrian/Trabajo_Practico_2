package ar.edu.unju.fi.ejercicio2.model;
import ar.edu.unju.fi.ejercicio2.constantes.Mes;

public class Efemeride {
	private int codigo;
	private int dia;
	private String detalle;
	private Mes mes;
	
public Efemeride(int codigo, Mes mes, int dia, String detalle ) {
	this.codigo = codigo;
	this.mes = mes;
	this.dia = dia;
	this.detalle = detalle;
}

public int getCodigo() {
	return codigo;
}

public void setCodigo(int codigo) {
	this.codigo = codigo;
}

public int getDia() {
	return dia;
}

public void setDia(int dia) {
	this.dia = dia;
}

public String getDetalle() {
	return detalle;
}

public void setDetalle(String detalle) {
	this.detalle = detalle;
}

public Mes getMes() {
	return mes;
}

public void setMes(Mes mes) {
	this.mes = mes;
}
	
}
