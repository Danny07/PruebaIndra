package com.co.prueba.utils;

import java.util.Objects;

public class Utils {

	public static boolean validateString(String mensaje) {
		return Objects.nonNull(mensaje) && !mensaje.trim().isEmpty();
	}
}
