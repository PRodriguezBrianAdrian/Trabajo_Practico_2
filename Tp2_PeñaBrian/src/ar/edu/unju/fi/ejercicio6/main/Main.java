package ar.edu.unju.fi.ejercicio6.main;

import ar.edu.unju.fi.ejercicio6.interfaces.funcionales.Converter;
import ar.edu.unju.fi.ejercicio6.model.FelinoDomestico;
import ar.edu.unju.fi.ejercicio6.model.FelinoSalvaje;

public class Main {

	public static void main(String[] args) {

		FelinoSalvaje tanner = new FelinoSalvaje();
		tanner.setNombre("Tanner");
		tanner.setEdad((byte) 20);
		tanner.setPeso(186f);


		if (Converter.isNotNul(tanner)) {
		
			Converter<FelinoSalvaje, FelinoDomestico> converter = x -> {
				FelinoDomestico felinoDomestico = new FelinoDomestico();
				felinoDomestico.setNombre(x.getNombre());
				felinoDomestico.setEdad(x.getEdad());
				felinoDomestico.setPeso(x.getPeso());
				return felinoDomestico;
			};


			FelinoDomestico felinoDomestico = converter.convert(tanner);


			converter.mostrarObjeto(felinoDomestico);
		}
	}

}