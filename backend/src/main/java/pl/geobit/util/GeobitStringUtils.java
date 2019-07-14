package pl.geobit.util;

import java.security.SecureRandom;

public final class GeobitStringUtils {

	private GeobitStringUtils() {
	}

	public static String generateToken() {
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[20];
		random.nextBytes(bytes);
		return bytes.toString();
	}
}
