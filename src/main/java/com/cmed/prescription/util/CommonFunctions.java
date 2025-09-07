package com.cmed.prescription.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface CommonFunctions {

	default Response getSuccessResponse(String message) {
		Response response = new Response();
		response.setSuccess(true);
		response.setMessage(message);
		return response;
	}

	default Response getSuccessResponse(String message, Response response) {
		response.setSuccess(true);
		response.setMessage(message);
		return response;
	}

	default Response getErrorResponse(String message) {
		Response response = new Response();
		response.setSuccess(false);
		response.setMessage(message);
		return response;
	}

	default Response getErrorResponse(String message, Response response) {
		response.setSuccess(false);
		response.setMessage(message);
		return response;
	}

	default Response getErrorResponse(String message, int errorCode) {
		Response response = new Response();
		response.setSuccess(false);
		response.setMessage(message);
		response.setErrorCode(errorCode);
		return response;
	}
	
	default <T> T objectMapperReadValue(String content, Class<T> valueType) {
		
		if(content == null) {
			return null;
		}
		
		ObjectMapper objectMapper = new ObjectMapper();

//		    JavaTimeModule module = new JavaTimeModule();
//		    
//	        LocalDateTimeDeserializer localDateTimeDeserializer = new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss"));
//	        LocalDateTimeDeserializer localDateTimeDeserializer1 = new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
//	        LocalDateTimeDeserializer localDateTimeDeserializer2 = new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
//	        
//	        module.addDeserializer(LocalDateTime.class, localDateTimeDeserializer);
//	        module.addDeserializer(LocalDateTime.class, localDateTimeDeserializer1);
//	        module.addDeserializer(LocalDateTime.class, localDateTimeDeserializer2);
//	        
//	        objectMapper.registerModule(module);

		try {

			return objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).readValue(content,
					valueType);

		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		// return null;
	}
}
