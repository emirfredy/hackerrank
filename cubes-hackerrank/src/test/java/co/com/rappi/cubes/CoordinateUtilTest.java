package co.com.rappi.cubes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;


/**
 * For this validation prototype everything will be in one file since
 * hackerrank.com receives the source in one file
 * 
 * @author <a href="emirfredy@gmail.com">Emir Cortes Trujillo</a>
 */
public class CoordinateUtilTest {

	final private Coordinate FIRST_CORNER = new Coordinate(1, 1, 1);
	final private Coordinate LAST_CORNER = new Coordinate(10, 10, 10);


	@Test
	public void givenCoordinateInTheMiddle_isCoordinateInRange_ShouldSuccess() {
		
		Coordinate evaluated = new Coordinate(5, 5, 5);
		assertTrue(CoordinateUtil.isCoordinateInRange(evaluated, FIRST_CORNER, LAST_CORNER));
	}
	
	@Test
	public void givenTopCoordinateInRange_isCoordinateInRange_ShouldSuccess() {
		Coordinate evaluated = new Coordinate(1, 5, 5);
		assertTrue(CoordinateUtil.isCoordinateInRange(evaluated, FIRST_CORNER, LAST_CORNER));	
	}
	
	@Test
	public void givenBottomCoordinateInRange_isCoordinateInRange_ShouldSuccess() {
		Coordinate evaluated = new Coordinate(10, 5, 5);
		assertTrue(CoordinateUtil.isCoordinateInRange(evaluated, FIRST_CORNER, LAST_CORNER));	
	}
	
	@Test
	public void givenRightCoordinateInRange_isCoordinateInRange_ShouldSuccess() {
		Coordinate evaluated = new Coordinate(5, 1, 5);
		assertTrue(CoordinateUtil.isCoordinateInRange(evaluated, FIRST_CORNER, LAST_CORNER));	
	}
	
	@Test
	public void givenLeftCoordinateInRange_isCoordinateInRange_ShouldSuccess() {
		Coordinate evaluated = new Coordinate(5, 10, 5);
		assertTrue(CoordinateUtil.isCoordinateInRange(evaluated, FIRST_CORNER, LAST_CORNER));	
	}		
	
	@Test
	public void givenFrontCoordinateInRange_isCoordinateInRange_ShouldSuccess() {
		Coordinate evaluated = new Coordinate(5, 5, 1);
		assertTrue(CoordinateUtil.isCoordinateInRange(evaluated, FIRST_CORNER, LAST_CORNER));	
	}
	
	@Test
	public void givenBackCoordinateInRange_isCoordinateInRange_ShouldSuccess() {
		Coordinate evaluated = new Coordinate(5, 5, 10);
		assertTrue(CoordinateUtil.isCoordinateInRange(evaluated, FIRST_CORNER, LAST_CORNER));	
	}
	

	@Test
	public void givenTopCoordinateOutRange_isCoordinateInRange_ShouldFail() {
		Coordinate evaluated = new Coordinate(0, 5, 5);
		assertFalse(CoordinateUtil.isCoordinateInRange(evaluated, FIRST_CORNER, LAST_CORNER));	
	}
	
	@Test
	public void givenBottomCoordinateOutRange_isCoordinateInRange_ShouldFail() {
		Coordinate evaluated = new Coordinate(11, 5, 5);
		assertFalse(CoordinateUtil.isCoordinateInRange(evaluated, FIRST_CORNER, LAST_CORNER));	
	}
	
	@Test
	public void givenRightCoordinateOutRange_isCoordinateInRange_ShouldFail() {
		Coordinate evaluated = new Coordinate(5, 0, 5);
		assertFalse(CoordinateUtil.isCoordinateInRange(evaluated, FIRST_CORNER, LAST_CORNER));	
	}
	
	@Test
	public void givenLeftCoordinateOutRange_isCoordinateInRange_ShouldFail() {
		Coordinate evaluated = new Coordinate(5, 11, 5);
		assertFalse(CoordinateUtil.isCoordinateInRange(evaluated, FIRST_CORNER, LAST_CORNER));	
	}		
	
	@Test
	public void givenFrontCoordinateOutRange_isCoordinateInRange_ShouldFail() {
		Coordinate evaluated = new Coordinate(5, 5, 0);
		assertFalse(CoordinateUtil.isCoordinateInRange(evaluated, FIRST_CORNER, LAST_CORNER));	
	}
	
	@Test
	public void givenBackCoordinateOutRange_isCoordinateInRange_ShouldFail() {
		Coordinate evaluated = new Coordinate(5, 5, 11);
		assertFalse(CoordinateUtil.isCoordinateInRange(evaluated, FIRST_CORNER, LAST_CORNER));	
	}	
	
	@Test
	public void givenCoordinateInRangeSample1_sumInRange_ShouldSuccess() {
		Map<Coordinate, BigInteger> coordinateValues = new HashMap<>();
		
		Coordinate coordinate = new Coordinate(2, 2, 2);
		coordinateValues.put(coordinate , new BigInteger("4"));
		
		Coordinate firstCorner = new Coordinate(1, 1, 1);
		Coordinate lastCorner = new Coordinate(3, 3, 3);
		BigInteger sumInRange = CoordinateUtil.sumInRange(coordinateValues, firstCorner, lastCorner);
		assertEquals(new BigInteger("4"), sumInRange);
		
		coordinate = new Coordinate(1, 1, 1);
		coordinateValues.put(coordinate , new BigInteger("23"));

		firstCorner = new Coordinate(2, 2, 2);
		lastCorner = new Coordinate(4, 4, 4);
		sumInRange = CoordinateUtil.sumInRange(coordinateValues, firstCorner, lastCorner);
		assertEquals(new BigInteger("4"), sumInRange);
		
		firstCorner = new Coordinate(1, 1, 1);
		lastCorner = new Coordinate(3, 3, 3);
		sumInRange = CoordinateUtil.sumInRange(coordinateValues, firstCorner, lastCorner);
		assertEquals(new BigInteger("27"), sumInRange);
	}
	
	@Test
	public void givenCoordinateInRangeSample2_sumInRange_ShouldSuccess() {
		Map<Coordinate, BigInteger> coordinateValues = new HashMap<>();
		
		Coordinate coordinate = new Coordinate(2, 2, 2);
		coordinateValues.put(coordinate , new BigInteger("1"));
		
		Coordinate firstCorner = new Coordinate(1, 1, 1);
		Coordinate lastCorner = new Coordinate(1, 1, 1);
		BigInteger sumInRange = CoordinateUtil.sumInRange(coordinateValues, firstCorner, lastCorner);
		assertEquals(new BigInteger("0"), sumInRange);

		firstCorner = new Coordinate(1, 1, 1);
		lastCorner = new Coordinate(2, 2, 2);
		sumInRange = CoordinateUtil.sumInRange(coordinateValues, firstCorner, lastCorner);
		assertEquals(new BigInteger("1"), sumInRange);
		
		firstCorner = new Coordinate(2, 2, 2);
		lastCorner = new Coordinate(2, 2, 2);
		sumInRange = CoordinateUtil.sumInRange(coordinateValues, firstCorner, lastCorner);
		assertEquals(new BigInteger("1"), sumInRange);
	}	
	
}
