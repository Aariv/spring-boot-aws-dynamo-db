/**
 * 
 */
package org.elearn.dynamodb.utils;

import java.io.IOException;

import org.elearn.dynamodb.model.Sports;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author kloudone
 *
 */
public class SportsTypeConvertor implements DynamoDBTypeConverter<String, Sports> {

	@Override
	public String convert(Sports object) {
		ObjectMapper mapper = new ObjectMapper();
		String data = "";
		try {
			data = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public Sports unconvert(String object) {
		ObjectMapper mapper = new ObjectMapper();
		Sports sports = new Sports();
		try {
			sports = mapper.readValue(object, Sports.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sports;
	}

}
