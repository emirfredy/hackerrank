package co.com.rappi.cubes.service;

import java.io.InputStream;
import java.math.BigInteger;
import java.util.List;

import co.com.rappi.cubes.model.Problem;
import co.com.rappi.cubes.model.Query;
import co.com.rappi.cubes.model.Update;

/**
 * The service for problem related duties
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

	/**
	 * Solves a {@link Problem} identified by its id
	 * 
	 * @param id
	 *            The id of the {@link Problem}
	 * @return A list of the sums resulting from {@link Query} the of the
	 *         {@link Problem}
	 */
	List<BigInteger> solve(Long id);

	/**
	 * Solves a problem set read from a {@link InputStream} given in the format
	 * described at:
	 * https://www.hackerrank.com/contests/101jan14/challenges/cube-summation
	 * 
	 * @param inputStream
	 *            The {@link InputStream} containing the problem set
	 * @return A list of the sums resulting sums given the queries
	 */
	List<BigInteger> solveInBatch(InputStream inputStream);

}