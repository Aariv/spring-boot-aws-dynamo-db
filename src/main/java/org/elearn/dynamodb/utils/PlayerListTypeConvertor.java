/**
 * 
 */
package org.elearn.dynamodb.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.elearn.dynamodb.model.Player;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

/**
 * @author kloudone
 *
 */
public class PlayerListTypeConvertor implements DynamoDBTypeConverter<String, List<Player>> {

	@Override
	public String convert(List<Player> object) {
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
	public List<Player> unconvert(String object) {
		ObjectMapper mapper = new ObjectMapper();
		List<Player> players = new ArrayList<>();
		CollectionType javaType = mapper.getTypeFactory().constructCollectionType(List.class, Player.class);
		try {
			players = mapper.readValue(object, javaType);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return players;
	}

}
