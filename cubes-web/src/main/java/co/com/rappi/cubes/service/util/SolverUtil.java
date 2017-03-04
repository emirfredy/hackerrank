package co.com.rappi.cubes.service.util;

import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import org.springframework.stereotype.Service;

import co.com.rappi.cubes.model.Coordinate;
import co.com.rappi.cubes.model.Problem;
import co.com.rappi.cubes.model.Query;
import co.com.rappi.cubes.model.Update;

/**
 * A utility that provides problem solving capabilities
 * 
 * @author <a href="emirfredy@gmail.com">Emir Cortes Trujillo</a>
 */
@Service
public class SolverUtil {

	private static final Coordinate MIN = new Coordinate(1, 1, 1);
	private static final Coordinate MAX = new Coordinate(100, 100, 100);

	/**
	 * Solves a {@link Problem} and returns the list of resulting sums
	 * 
	 * @param problem
	 *            The {@link Problem} to solve
	 * @return A list of the sums after solving the problem
	 */
	public List<BigInteger> solve(Problem problem) {
		int steps = problem.getSteps();
		List<Query> queries = problem.getQueries();
		List<Update> updates = problem.getUpdates();
		int q = 0;
		int u = 0;
		boolean operationFound = false;

		List<BigInteger> sums = new ArrayList<BigInteger>();
		Map<Coordinate, BigInteger> coordinateValues = new HashMap<>();

		for (int i = 1; i <= steps; i++) {
			operationFound = false;
			if (q < queries.size()) {
				Query query = queries.get(q);
				if (query.getStep() == i) {
					q++;
					operationFound = true;
					Coordinate firstCorner = new Coordinate(query.getX1(), query.getY1(), query.getZ1());
					Coordinate lastCorner = new Coordinate(query.getX2(), query.getY2(), query.getZ2());
					if (isCoordinateInRange(firstCorner, MIN, MAX) && isCoordinateInRange(lastCorner, MIN, MAX)
							&& firstCorner.lessOrEqualThan(lastCorner)) {
						BigInteger sum = sumInRange(coordinateValues, firstCorner, lastCorner);
						sums.add(sum);
					}
				}
			}
			if (!operationFound && u < updates.size()) {
				Update update = updates.get(u);
				if (update.getStep() == i) {
					u++;
					Coordinate coordinate = new Coordinate(update.getX(), update.getY(), update.getZ());
					if (isCoordinateInRange(coordinate, MIN, MAX)) {
						coordinateValues.put(coordinate, update.getValue());
					}
				}
			}
		}
		return sums;
	}

	/**
	 * Adds up the values in a given range
	 * 
	 * @param coordinateValues
	 *            A map containing {@link Coordinate} and its associated values
	 * @param firstCorner
	 *            The {@link Coordinate} defining where the range for summation
	 *            starts
	 * @param lastCorner
	 *            The {@link Coordinate} defining where the range for summation
	 *            ends
	 * @return The result of the summation
	 */
	public BigInteger sumInRange(Map<Coordinate, BigInteger> coordinateValues, Coordinate firstCorner,
			Coordinate lastCorner) {
		BigInteger total = BigInteger.ZERO;
		Set<Entry<Coordinate, BigInteger>> entrySet = coordinateValues.entrySet();

		for (Entry<Coordinate, BigInteger> entry : entrySet) {
			if (isCoordinateInRange(entry.getKey(), firstCorner, lastCorner)) {
				total = total.add(entry.getValue());
			}
		}
		return total;
	}

	/**
	 * Checks whether a {@link Coordinate} is a range
	 * 
	 * @param evaluated
	 *            The {@link Coordinate} to be evaluated
	 * @param firstCorner
	 *            The {@link Coordinate} defining where the range for validation
	 *            starts
	 * @param lastCorner
	 *            The {@link Coordinate} defining where the range for validation
	 *            ends
	 * @return
	 */
	public boolean isCoordinateInRange(Coordinate evaluated, Coordinate firstCorner, Coordinate lastCorner) {
		return evaluated.greaterOrEqualThan(firstCorner) && evaluated.lessOrEqualThan(lastCorner);
	}

	/**
	 * Solves a problem set read from a {@link InputStream} given in the format
	 * described at:
	 * https://www.hackerrank.com/contests/101jan14/challenges/cube-summation
	 * 
	 * @param inputStream
	 *            The {@link InputStream} containing the problem set
	 * @return A list of the sums resulting sums given the queries
	 */
	public List<BigInteger> solveInBatch(InputStream inputStream) {
		Scanner scanner = new Scanner(inputStream);
		int t = scanner.nextInt();

		List<BigInteger> sums = new ArrayList<BigInteger>();

		for (int i = 0; i < t; i++) {
			scanner.nextInt();

			Map<Coordinate, BigInteger> coordinateValues = new HashMap<>();

			int m = scanner.nextInt();

			for (int j = 0; j < m; j++) {
				String operation = scanner.next();
				if ("UPDATE".equalsIgnoreCase(operation)) {
					int x = scanner.nextInt();
					int y = scanner.nextInt();
					int z = scanner.nextInt();
					BigInteger w = new BigInteger(scanner.next());
					Coordinate coordinate = new Coordinate(x, y, z);
					if (isCoordinateInRange(coordinate, MIN, MAX)) {
						coordinateValues.put(coordinate, w);
					}
				} else if ("QUERY".equalsIgnoreCase(operation)) {
					int x1 = scanner.nextInt();
					int y1 = scanner.nextInt();
					int z1 = scanner.nextInt();
					int x2 = scanner.nextInt();
					int y2 = scanner.nextInt();
					int z2 = scanner.nextInt();
					Coordinate c1 = new Coordinate(x1, y1, z1);
					Coordinate c2 = new Coordinate(x2, y2, z2);
					if (isCoordinateInRange(c1, MIN, MAX)
							&& isCoordinateInRange(c2, MIN, MAX) && c1.lessOrEqualThan(c2)) {
						BigInteger sum = sumInRange(coordinateValues, c1, c2);
						sums.add(sum);
					}
				}
			}
		}

		scanner.close();
		
		return sums;
	}

}
