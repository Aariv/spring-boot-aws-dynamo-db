/**
 * 
 */
package org.elearn.dynamodb.service;

import java.util.List;

import org.elearn.dynamodb.model.Player;

/**
 * @author kloudone
 *
 */
public interface LocationService {

	List<Player> getPlayersWithinDistance(String sportName, String state, String city, Double latitude, Double longitude, Double distance);
}
