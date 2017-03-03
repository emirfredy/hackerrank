package co.com.rappi.cubes;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

/**
 * For this validation prototype everything will be in one file since
 * hackerrank.com receives the source in one file
 * 
 * @author <a href="emirfredy@gmail.com">Emir Cortes Trujillo</a>
 */
public class Solution {

	/** The smallest coordinate that can be used in a cube */
	private static final Coordinate MIN = new Coordinate(1, 1, 1);
	/** The greatest coordinate that can be used in a cube */
	private static final Coordinate MAX = new Coordinate(100, 100, 100);

	/**
	 * Entry Point
	 * 
	 * @param args
	 *            The arguments send since command line. Not used in this stage
	 */
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
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
					if (CoordinateUtil.isCoordinateInRange(coordinate, MIN, MAX)) {
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
					if (CoordinateUtil.isCoordinateInRange(c1, MIN, MAX)
							&& CoordinateUtil.isCoordinateInRange(c2, MIN, MAX) && c1.lessOrEqualThan(c2)) {
						BigInteger sum = CoordinateUtil.sumInRange(coordinateValues, c1, c2);
						sums.add(sum);
					}
				}
			}
		}

		for (BigInteger sum : sums) {
			System.out.println(sum);
		}
		scanner.close();
	}

}

/**
 * Represents a 3D coordinate
 * 
 * @author <a href="emirfredy@gmail.com">Emir Cortes Trujillo</a>
 */
class Coordinate {
	/** The position in the first axe*/ 
	private int x;
	/** The position in the second axe*/
	private int y;
	/** The position in the third axe*/
	private int z;

	public Coordinate(int x, int y, int z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		result = prime * result + z;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		if (z != other.z)
			return false;
		return true;
	}

	/**
	 * Checks whether a {@link Coordinate} is less or equal than another one
	 * 
	 * @param other
	 *            The {@link Coordinate} to compare with
	 * @return true if the {@link Coordinate} is less or equal than the other
	 *         {@link Coordinate}, false otherwise
	 */
	public boolean lessOrEqualThan(Coordinate other) {
		return x <= other.getX() && y <= other.getY() && z <= other.getZ();
	}

	/**
	 * Checks whether a {@link Coordinate} is greater or equal than another one
	 * 
	 * @param other
	 *            The {@link Coordinate} to compare with
	 * @return true if the {@link Coordinate} is greater or equal than the other
	 *         {@link Coordinate}, false otherwise
	 */
	public boolean greaterOrEqualThan(Coordinate other) {
		return x >= other.getX() && y >= other.getY() && z >= other.getZ();
	}
}

/**
 * A utility for calculations and {@link Coordinate} validations
 * 
 * @author <a href="emirfredy@gmail.com">Emir Cortes Trujillo</a>
 */
class CoordinateUtil {

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
