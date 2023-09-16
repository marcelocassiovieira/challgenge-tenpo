package com.marcelo.backend.apirest.utils;

import java.text.DecimalFormat;

public class Uteis {

	/**
	 * Metodo para dar formato: Apenas dois decimais
	 * @param numero
	 * @return
	 */
	public static  Double darFormato(Double numero) {
		DecimalFormat df = new DecimalFormat("#.##");
		return Double.valueOf(df.format(numero));
	}
}
