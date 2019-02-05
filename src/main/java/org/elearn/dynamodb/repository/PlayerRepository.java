/**
 * 
 */
package org.elearn.dynamodb.repository;

import org.elearn.dynamodb.model.Player;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

/**
 * @author kloudone
 *
 */
@EnableScan
public interface PlayerRepository extends CrudRepository<Player, String> {

}
