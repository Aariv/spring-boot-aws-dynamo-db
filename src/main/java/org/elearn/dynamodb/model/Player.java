/**
 * 
 */
package org.elearn.dynamodb.model;

import org.elearn.dynamodb.utils.SportsTypeConvertor;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author kloudone
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@DynamoDBTable(tableName = "Player")
public class Player {

	@DynamoDBHashKey(attributeName = "Id")
	private String id;

	@DynamoDBAttribute(attributeName = "Name")
	private String name;

	@DynamoDBTypeConverted(converter = SportsTypeConvertor.class)
	@DynamoDBAttribute(attributeName = "Sports")
	private Sports sports;
}
