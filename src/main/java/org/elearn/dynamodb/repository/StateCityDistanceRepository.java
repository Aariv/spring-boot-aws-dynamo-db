/**
 * 
 */
package org.elearn.dynamodb.repository;

import java.util.List;

import org.elearn.dynamodb.model.StateCityDistance;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

/**
 * @author kloudone
 *
 */
@EnableScan
public interface StateCityDistanceRepository extends CrudRepository<StateCityDistance, String> {

	List<StateCityDistance> findByStateAndCityAndDistance(String state, String city, Double distance);
	
	List<StateCityDistance> findByStateAndCity(String state, String city);
	
	List<StateCityDistance> findByState(String state);
	
}
