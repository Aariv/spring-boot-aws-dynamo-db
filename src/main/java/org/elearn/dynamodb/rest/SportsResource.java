/**
 * 
 */
package org.elearn.dynamodb.rest;

import java.util.List;

import org.elearn.dynamodb.model.Sports;
import org.elearn.dynamodb.repository.SportsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kloudone
 *
 */
@RestController
@RequestMapping("/api/v1")
public class SportsResource {
	
	@Autowired
	private SportsRepository sportsRepository;

	/**
	 * 
	 * @return
	 */
	@GetMapping("/sports")
	public List<Sports> players() {
		return (List<Sports>) sportsRepository.findAll();
	}
}
