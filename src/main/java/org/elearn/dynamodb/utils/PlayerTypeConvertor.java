/**
 * 
 */
package org.elearn.dynamodb.utils;

import org.elearn.dynamodb.model.Player;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

/**
 * @author kloudone
 *
 */
public class PlayerTypeConvertor implements DynamoDBTypeConverter<String, Player> {

	@Override
	public String convert(Player p) {
		Player player = (Player) p;
		String playerDetails = "";
		try {
			if (player != null) {
				playerDetails = String.format("%s x %s", p.getId(), p.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return playerDetails;
	}

	@Override
	public Player unconvert(String s) {
		Player player = new Player();
		try {
			if (s != null && s.length() != 0) {
				String[] data = s.split("x");
				player.setId(data[0].trim());
				player.setName(data[1].trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return player;
	}

}
