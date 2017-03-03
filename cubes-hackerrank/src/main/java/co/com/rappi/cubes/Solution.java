package co.com.rappi.cubes;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import co.com.rappi.cubes.model.Coordinate;
import co.com.rappi.cubes.util.CoordinateUtil;

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
	 *            The arguments sent from command line. Not used in this stage
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
