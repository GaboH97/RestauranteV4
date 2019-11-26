package com.restaurante.app.global.config;

import java.util.Arrays;

public class NetConstants {
	
	private static final String HTTP_PREFIX = "http://";

	public static final String MESERO_HOST_IP = "localhost";
	public static final Integer MESERO_HOST_PORT = 8080;
	public static final String COCINA_HOST_IP = "192.168.0.2";
	public static final Integer COCINA_HOST_PORT = 8080;
	public static final String CAJA_HOST_IP = "192.168.43.236";
	public static final Integer CAJA_HOST_PORT = 8080;
	public static final String MESA_HOST_IP = "192.168.0.2";
	public static final Integer MESA_HOST_PORT = 8080;
	public static final String CLIENTE_HOST_IP = "192.168.0.2";
	public static final Integer CLIENTE_HOST_PORT = 8080;

	public static final String MESERO_URL_ENDPOINT = buildURL(HTTP_PREFIX,MESERO_HOST_IP,":",String.valueOf(MESERO_HOST_PORT),"/");
	public static final String COCINA_URL_ENDPOINT =buildURL(HTTP_PREFIX,COCINA_HOST_IP,":",String.valueOf(COCINA_HOST_PORT),"/");
	public static final String CAJA_URL_ENDPOINT = buildURL(HTTP_PREFIX,CAJA_HOST_IP,":",String.valueOf(CAJA_HOST_PORT),"/");
	public static final String MESA_URL_ENDPOINT = buildURL(HTTP_PREFIX,MESA_HOST_IP,":",String.valueOf(MESA_HOST_PORT),"/");
	public static final String CLIENTE_URL_ENDPOINT = buildURL(HTTP_PREFIX,CLIENTE_HOST_IP,":",String.valueOf(CLIENTE_HOST_PORT),"/");
	
	
	public static String buildURL(String...args) {
		StringBuilder builder = new StringBuilder();
		Arrays.asList(args).forEach(builder::append);
		return builder.toString();
	}
}
