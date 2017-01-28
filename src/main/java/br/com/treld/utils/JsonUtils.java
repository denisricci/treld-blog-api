package br.com.treld.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("PMD")
public class JsonUtils {
	
	public static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	} 
}
