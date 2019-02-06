/**
 * 
 */
package org.elearn.dynamodb.model;

import org.elearn.dynamodb.utils.PlayerTypeConvertor;

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
@DynamoDBTable(tableName = "Sports")
public class Sports {

	@DynamoDBHashKey(attributeName = "Id")
	private String id;

	@DynamoDBAttribute(attributeName = "Name")
	private String name;

//	@DynamoDBTypeConverted(converter = PlayerTypeConvertor.class)
//	@DynamoDBAttribute(attributeName = "Player")
//	private Player player;
}
