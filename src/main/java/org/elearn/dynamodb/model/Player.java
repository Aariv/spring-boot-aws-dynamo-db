/**
 * 
 */
package org.elearn.dynamodb.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

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
@DynamoDBTable(tableName = "Player")
public class Player {

	@DynamoDBHashKey(attributeName = "Id")
	private String id;

	@DynamoDBAttribute(attributeName = "Name")
	private String name;

}
