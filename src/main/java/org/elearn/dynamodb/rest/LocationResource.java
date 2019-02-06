/**
 * 
 */
package org.elearn.dynamodb.rest;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.elearn.dynamodb.model.Player;
import org.elearn.dynamodb.model.Sports;
import org.elearn.dynamodb.model.StateCityDistance;
import org.elearn.dynamodb.repository.PlayerRepository;
import org.elearn.dynamodb.repository.SportsRepository;
import org.elearn.dynamodb.repository.StateCityDistanceRepository;
import org.elearn.dynamodb.service.LocationService;
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

	@Autowired
	private SportsRepository sportsRepository;

	@Autowired
	private LocationService locationService;

	/**
	 * 
	 * @param state
	 * @param city
	 * @param distance
	 * @return
	 */
	@GetMapping("/locateMe/{sportName}/{state}/{city}/{latitude}/{longitude}/{distance}")
	public List<Player> findByDistance(
			@PathVariable(name = "sportName") String sportName,
			@PathVariable(name = "state") String state, 
			@PathVariable(name = "city") String city,
			@PathVariable(name = "latitude") Double latitude, 
			@PathVariable(name = "longitude") Double longitude,
			@PathVariable(name = "distance") Double distance) {
		return locationService.getPlayersWithinDistance(sportName, state, city, latitude, longitude, distance);
	}

	/**
	 * 
	 * @return
	 */
	@GetMapping("/saveLocation")
	public String saveLocation() {
		createDummyRecords();
		return "Done";
	}

	private void createDummyRecords() {
		Sports batminton = new Sports(UUID.randomUUID().toString().substring(0, 5), "Batminton");
		Sports tennis = new Sports(UUID.randomUUID().toString().substring(0, 5), "Tennis");

		sportsRepository.save(Arrays.asList(batminton, tennis));

		Player karthik = new Player(UUID.randomUUID().toString().substring(0, 5), "Karthik", batminton);
		Player sekar = new Player(UUID.randomUUID().toString().substring(0, 5), "Sekar", batminton);
		Player pavithra = new Player(UUID.randomUUID().toString().substring(0, 5), "Pavithra", tennis);
		Player senthil = new Player(UUID.randomUUID().toString().substring(0, 5), "Senthil", tennis);
		playerRepository.save(Arrays.asList(karthik, pavithra, senthil, sekar));

		StateCityDistance karthikLocation = new StateCityDistance(UUID.randomUUID().toString().substring(0, 5),
				"Coimbatore", "TN", 11.0168, 76.9558, 0.0, karthik);
		
		StateCityDistance sekarLocation = new StateCityDistance(UUID.randomUUID().toString().substring(0, 5),
				"Coimbatore", "TN", 11.1510, 76.9355, 0.0, sekar);
		
		StateCityDistance pavithraLocation = new StateCityDistance(UUID.randomUUID().toString().substring(0, 5),
				"Coimbatore", "TN", 11.0210, 76.9663, 0.0, pavithra);
		StateCityDistance senthilLocation = new StateCityDistance(UUID.randomUUID().toString().substring(0, 5),
				"Coimbatore", "TN", 11.0764, 77.0030, 0.0, pavithra);
		stateCityDistanceRepository.save(Arrays.asList(karthikLocation, pavithraLocation, senthilLocation, sekarLocation));
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
