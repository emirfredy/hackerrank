package co.com.rappi.cubes.model;
/**
 * Represents a 3D coordinate
 * 
 * @author <a href="emirfredy@gmail.com">Emir Cortes Trujillo</a>
 */
public class Coordinate {
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