package co.com.rappi.cubes.dao;

import org.springframework.data.repository.CrudRepository;

import co.com.rappi.cubes.model.Problem;

/**
 * The repository that provides access to the {@link Problem}  store
 * In these case an in-memory data base
 * 
 * @author <a href="emirfredy@gmail.com">Emir Cortes Trujillo</a>
 */
public interface ProblemRepository extends CrudRepository<Problem, Long> {

}