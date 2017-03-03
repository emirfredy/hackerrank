package co.com.rappi.cubes.service;

import co.com.rappi.cubes.model.Problem;
import co.com.rappi.cubes.model.Query;
import co.com.rappi.cubes.model.Update;

/**
 * The service that provides problem solving capabilities
 * 
 * @author <a href="emirfredy@gmail.com">Emir Cortes Trujillo</a>
 */
public interface CubeService {

	/**
	 * Creates a new problem in the store
	 * 
	 * @param problem
	 *            The {@link Problem} to be created
	 * @return The id of the just created problem
	 */
	Long create(Problem problem);

	/**
	 * Retrieves a {@link Problem} given its id
	 * 
	 * @param id
	 *            The id of the {@link Problem}
	 * @return The {@link Problem}
	 */
	Problem find(Long id);

	/**
	 * Adds an {@link Update} to the {@link Problem}
	 * 
	 * @param id
	 *            The id of the {@link Problem}
	 * @param update
	 *            The {@link Update} to be added
	 */
	void addUpdate(Long id, Update update);

	/**
	 * Adds a {@link Query} to the {@link Problem}
	 * 
	 * @param id
	 *            The id of the {@link Problem}
	 * @param update
	 *            The {@link Query} to be added
	 */
	void addQuery(Long id, Query query);

	/**
	 * Returns a sample {@link Problem}
	 * 
	 * @return A sample {@link Problem}
	 */
	Problem getSample();

}