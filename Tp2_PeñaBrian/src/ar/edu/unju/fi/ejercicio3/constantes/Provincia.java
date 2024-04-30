package ar.edu.unju.fi.ejercicio3.constantes;


public enum Provincia {
 JUJUY(500000, 53200),
 SALTA(1200000, 155488),
 TUCUMAN(1700000, 222524),
 CATAMARCA(400000, 102606),
 LARIOJA(350000, 89680),
 SANTIAGODELESTERO(900000, 136351);

 private int cantidadPoblacion;
 private int superficie;

 private Provincia(int cantidadPoblacion, int superficie) {
     this.cantidadPoblacion = cantidadPoblacion;
     this.superficie = superficie;
 }


 public int getCantidadPoblacion() {
	return cantidadPoblacion;
}


public void setCantidadPoblacion(int cantidadPoblacion) {
	this.cantidadPoblacion = cantidadPoblacion;
}


public int getSuperficie() {
	return superficie;
}


public void setSuperficie(int superficie) {
     this.superficie = superficie;
 }

 public double calcularDensidadPoblacional() {
     return (double) cantidadPoblacion / superficie;
 }
}
