package co.com.rappi.cubes.util;


import java.math.BigInteger;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import co.com.rappi.cubes.model.Coordinate;

/**
 * A utility for calculations and {@link Coordinate} validations
 * 
 * @author <a href="emirfredy@gmail.com">Emir Cortes Trujillo</a>
 */
public class CoordinateUtil {

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
	public static BigInteger sumInRange(Map<Coordinate, BigInteger> coordinateValues, Coordinate firstCorner,
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
	 * @param coordinate
	 *            The {@link Coordinate} to be evaluated
	 * @param firstCorner
	 *            The {@link Coordinate} defining where the range for validation
	 *            starts
	 * @param lastCorner
	 *            The {@link Coordinate} defining where the range for validation
	 *            ends
	 * @return
	 */
	public static boolean isCoordinateInRange(Coordinate coordinate, Coordinate firstCorner, Coordinate lastCorner) {
		return coordinate.greaterOrEqualThan(firstCorner) && coordinate.lessOrEqualThan(lastCorner);
	}
}