package com.marcelo.backend.apirest.utils;


/**
 * Constantes usadas en el API para las excepciones
 * @author marcelo
 *
 */
public final class ExceptionsConstantes {
	public static final String REQUEST_LIMIT = "Excedido el límite de solicitudes por minuto!";
	public static final String ERROR_HISTORY = "Error al guardar history...";
	public static final String ERROR_OBTENER_PORCENTAGE = "Error al obtener el porcentage externo";
	public static final String ERROR_OBTENER_PORCENTAGE_BASE = "Error al obtener el porcentage de la base de datos";
	public static final String ERROR_AL_OBTENER_HISTORIAS_PAGINADAS = "Error al obtener historias paginadas.";
	public static final String ERROR_AL_CONVERTIR_LISTA_DE_HISTORIAS_A_LISTA_DE_DTOS = "Error al convertir lista de historias a lista de DTOs.";

}
