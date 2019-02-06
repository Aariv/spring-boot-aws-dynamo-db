/**
 * 
 */
package org.elearn.dynamodb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.elearn.dynamodb.model.Player;
import org.elearn.dynamodb.model.Sports;
import org.elearn.dynamodb.model.StateCityDistance;
import org.elearn.dynamodb.repository.PlayerRepository;
import org.elearn.dynamodb.repository.SportsRepository;
import org.elearn.dynamodb.repository.StateCityDistanceRepository;
import org.elearn.dynamodb.service.LocationService;
import org.elearn.dynamodb.utils.DistanceCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kloudone
 *
 */
@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private StateCityDistanceRepository stateCityDistanceRepository;

	@Autowired
	private PlayerRepository playerRepository;
	

	@Autowired
	private SportsRepository sportsRepository;
	
	@Override
	public List<Player> getPlayersWithinDistance(String sportName, String state, String city, Double latitude,
			Double longitude, Double distance) {
		// Filter players by sports
		Sports sports = sportsRepository.findByName(sportName);
		List<Player> sportPlayersFromDb = (List<Player>) playerRepository.findAll();
		List<Player> sportPlayers = new ArrayList<>();
		List<Player> distancePlayers = new ArrayList<>();
		for (Player player : sportPlayersFromDb) {
			if(player.getSports().getName().equals(sports.getName())) {
				sportPlayers.add(player);
			}
		}
		// filter by city/state/distance
		List<StateCityDistance> cities = stateCityDistanceRepository.findByStateAndCity(state, city);
		for (StateCityDistance stateCityDistance : cities) {
			Double distanceCalculation = DistanceCalculator.distance(stateCityDistance.getLatitude(),
					stateCityDistance.getLongitude(), latitude, longitude, "M");
			if (distanceCalculation < distance) {
				distancePlayers.add(stateCityDistance.getPlayer());
			}
		}
		return getPlayersWithinGivenSports(sportPlayers, distancePlayers);
	}

	private List<Player> getPlayersWithinGivenSports(List<Player> players1, List<Player> players2) {
		List<Player> sportsPlayers = new ArrayList<>();
		for (Player player : players1) {
			for (Player playerFiltered : players2) {
				if(player.getId().equals(playerFiltered.getId())) {
					sportsPlayers.add(player);
				}
			}
		}
		return sportsPlayers;
	}

}
