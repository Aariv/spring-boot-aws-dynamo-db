/**
 * 
 */
package org.elearn.dynamodb.model;

import java.util.List;

import org.elearn.dynamodb.utils.PlayerListTypeConvertor;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kloudone
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "StateCityDistance")
public class StateCityDistance {

	@DynamoDBHashKey(attributeName = "Id")
	private String id;

	@DynamoDBAttribute(attributeName = "City")
	private String city;

	@DynamoDBAttribute(attributeName = "State")
	private String state;

	@DynamoDBAttribute(attributeName = "Distance")
	private Double distance;

	@DynamoDBTypeConverted(converter = PlayerListTypeConvertor.class)
	@DynamoDBAttribute(attributeName = "Player")
	private List<Player> player;

}
