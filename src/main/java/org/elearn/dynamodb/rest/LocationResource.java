/**
 * 
 */
package org.elearn.dynamodb.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.elearn.dynamodb.model.Player;
import org.elearn.dynamodb.model.StateCityDistance;
import org.elearn.dynamodb.repository.PlayerRepository;
import org.elearn.dynamodb.repository.StateCityDistanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kloudone
 *
 */
@RestController
@RequestMapping("/api/v1")
public class LocationResource {

	@Autowired
	private StateCityDistanceRepository stateCityDistanceRepository;

	@Autowired
	private PlayerRepository playerRepository;

	/**
	 * 
	 * @param state
	 * @param city
	 * @param distance
	 * @return
	 */
	@GetMapping("/locateMe/{state}/{city}/{distance}")
	public List<Player> findByDistance(@PathVariable(name = "state") String state,
			@PathVariable(name = "city") String city, @PathVariable(name = "distance") Double distance) {
		List<Player> players = new ArrayList<>();
		List<StateCityDistance> location = stateCityDistanceRepository.findByStateAndCityAndDistance(state, city, distance);
		List<StateCityDistance> states = stateCityDistanceRepository.findByState(state);
		System.out.println(states.size());
		System.out.println(location.size());
		states.stream().forEach(l -> {
			players.addAll(l.getPlayer());
		});
		return players;
	}

	/**
	 * 
	 * @return
	 */
	@GetMapping("/saveLocation")
	public String saveLocation() {
		Player player = new Player();
		player.setId("PLAYER-01");
		player.setName("Karthik");
		playerRepository.save(player);

		StateCityDistance stateCityDistance = new StateCityDistance();
		stateCityDistance.setId("CITY-STATE-01");
		stateCityDistance.setState("Maharashtra");
		stateCityDistance.setCity("Mumbai");
		stateCityDistance.setDistance(1166.17);
		stateCityDistance.setPlayer(Arrays.asList(player));
		stateCityDistanceRepository.save(stateCityDistance);
		return "Done";
	}

	/**
	 * 
	 * @return
	 */
	@GetMapping("/findAll")
	public List<StateCityDistance> findAllLocation() {
		List<StateCityDistance> locations = (List<StateCityDistance>) stateCityDistanceRepository.findAll();
		return locations;
	}
}
