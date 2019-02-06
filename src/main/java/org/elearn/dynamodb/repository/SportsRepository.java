/**
 * 
 */
package org.elearn.dynamodb.repository;

import org.elearn.dynamodb.model.Sports;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

/**
 * @author kloudone
 *
 */
@EnableScan
public interface SportsRepository extends CrudRepository<Sports, String> {

	Sports findByName(String name);
}
