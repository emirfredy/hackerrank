package co.com.rappi.cubes.service.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import co.com.rappi.cubes.model.Coordinate;
import co.com.rappi.cubes.model.Problem;
import co.com.rappi.cubes.model.Query;
import co.com.rappi.cubes.model.Update;

/**
 * Test for {@link SolverUtil}
 * 
 * @author <a href="emirfredy@gmail.com">Emir Cortes Trujillo</a>
 */

public class SolverUtilTest {

	final private Coordinate FIRST_CORNER = new Coordinate(1, 1, 1);
	final private Coordinate LAST_CORNER = new Coordinate(10, 10, 10);
	
	/** The class under test*/
	SolverUtil solverUtil = new SolverUtil();

	@Test
	public void givenCoordinateInTheMiddle_isCoordinateInRange_ShouldSuccess() {
		
		Coordinate evaluated = new Coordinate(5, 5, 5);
		assertTrue(solverUtil.isCoordinateInRange(evaluated, FIRST_CORNER, LAST_CORNER));
	}
	
	@Test
	public void givenTopCoordinateInRange_isCoordinateInRange_ShouldSuccess() {
		Coordinate evaluated = new Coordinate(1, 5, 5);
		assertTrue(solverUtil.isCoordinateInRange(evaluated, FIRST_CORNER, LAST_CORNER));	
	}
	
	@Test
	public void givenBottomCoordinateInRange_isCoordinateInRange_ShouldSuccess() {
		Coordinate evaluated = new Coordinate(10, 5, 5);
		assertTrue(solverUtil.isCoordinateInRange(evaluated, FIRST_CORNER, LAST_CORNER));	
	}
	
	@Test
	public void givenRightCoordinateInRange_isCoordinateInRange_ShouldSuccess() {
		Coordinate evaluated = new Coordinate(5, 1, 5);
		assertTrue(solverUtil.isCoordinateInRange(evaluated, FIRST_CORNER, LAST_CORNER));	
	}
	
	@Test
	public void givenLeftCoordinateInRange_isCoordinateInRange_ShouldSuccess() {
		Coordinate evaluated = new Coordinate(5, 10, 5);
		assertTrue(solverUtil.isCoordinateInRange(evaluated, FIRST_CORNER, LAST_CORNER));	
	}		
	
	@Test
	public void givenFrontCoordinateInRange_isCoordinateInRange_ShouldSuccess() {
		Coordinate evaluated = new Coordinate(5, 5, 1);
		assertTrue(solverUtil.isCoordinateInRange(evaluated, FIRST_CORNER, LAST_CORNER));	
	}
	
	@Test
	public void givenBackCoordinateInRange_isCoordinateInRange_ShouldSuccess() {
		Coordinate evaluated = new Coordinate(5, 5, 10);
		assertTrue(solverUtil.isCoordinateInRange(evaluated, FIRST_CORNER, LAST_CORNER));	
	}
	

	@Test
	public void givenTopCoordinateOutRange_isCoordinateInRange_ShouldFail() {
		Coordinate evaluated = new Coordinate(0, 5, 5);
		assertFalse(solverUtil.isCoordinateInRange(evaluated, FIRST_CORNER, LAST_CORNER));	
	}
	
	@Test
	public void givenBottomCoordinateOutRange_isCoordinateInRange_ShouldFail() {
		Coordinate evaluated = new Coordinate(11, 5, 5);
		assertFalse(solverUtil.isCoordinateInRange(evaluated, FIRST_CORNER, LAST_CORNER));	
	}
	
	@Test
	public void givenRightCoordinateOutRange_isCoordinateInRange_ShouldFail() {
		Coordinate evaluated = new Coordinate(5, 0, 5);
		assertFalse(solverUtil.isCoordinateInRange(evaluated, FIRST_CORNER, LAST_CORNER));	
	}
	
	@Test
	public void givenLeftCoordinateOutRange_isCoordinateInRange_ShouldFail() {
		Coordinate evaluated = new Coordinate(5, 11, 5);
		assertFalse(solverUtil.isCoordinateInRange(evaluated, FIRST_CORNER, LAST_CORNER));	
	}		
	
	@Test
	public void givenFrontCoordinateOutRange_isCoordinateInRange_ShouldFail() {
		Coordinate evaluated = new Coordinate(5, 5, 0);
		assertFalse(solverUtil.isCoordinateInRange(evaluated, FIRST_CORNER, LAST_CORNER));	
	}
	
	@Test
	public void givenBackCoordinateOutRange_isCoordinateInRange_ShouldFail() {
		Coordinate evaluated = new Coordinate(5, 5, 11);
		assertFalse(solverUtil.isCoordinateInRange(evaluated, FIRST_CORNER, LAST_CORNER));	
	}	
	
	@Test
	public void givenCoordinateInRangeSample1_sumInRange_ShouldSuccess() {
		Map<Coordinate, BigInteger> coordinateValues = new HashMap<>();
		
		Coordinate coordinate = new Coordinate(2, 2, 2);
		coordinateValues.put(coordinate , new BigInteger("4"));
		
		Coordinate firstCorner = new Coordinate(1, 1, 1);
		Coordinate lastCorner = new Coordinate(3, 3, 3);
		BigInteger sumInRange = solverUtil.sumInRange(coordinateValues, firstCorner, lastCorner);
		assertEquals(new BigInteger("4"), sumInRange);
		
		coordinate = new Coordinate(1, 1, 1);
		coordinateValues.put(coordinate , new BigInteger("23"));

		firstCorner = new Coordinate(2, 2, 2);
		lastCorner = new Coordinate(4, 4, 4);
		sumInRange = solverUtil.sumInRange(coordinateValues, firstCorner, lastCorner);
		assertEquals(new BigInteger("4"), sumInRange);
		
		firstCorner = new Coordinate(1, 1, 1);
		lastCorner = new Coordinate(3, 3, 3);
		sumInRange = solverUtil.sumInRange(coordinateValues, firstCorner, lastCorner);
		assertEquals(new BigInteger("27"), sumInRange);
	}
	
	@Test
	public void givenCoordinateInRangeSample2_sumInRange_ShouldSuccess() {
		Map<Coordinate, BigInteger> coordinateValues = new HashMap<>();
		
		Coordinate coordinate = new Coordinate(2, 2, 2);
		coordinateValues.put(coordinate , new BigInteger("1"));
		
		Coordinate firstCorner = new Coordinate(1, 1, 1);
		Coordinate lastCorner = new Coordinate(1, 1, 1);
		BigInteger sumInRange = solverUtil.sumInRange(coordinateValues, firstCorner, lastCorner);
		assertEquals(new BigInteger("0"), sumInRange);

		firstCorner = new Coordinate(1, 1, 1);
		lastCorner = new Coordinate(2, 2, 2);
		sumInRange = solverUtil.sumInRange(coordinateValues, firstCorner, lastCorner);
		assertEquals(new BigInteger("1"), sumInRange);
		
		firstCorner = new Coordinate(2, 2, 2);
		lastCorner = new Coordinate(2, 2, 2);
		sumInRange = solverUtil.sumInRange(coordinateValues, firstCorner, lastCorner);
		assertEquals(new BigInteger("1"), sumInRange);
	}	
	
	@Test
	public void givenSampleProblem1_solve_ShouldSuccess() {
		Problem problem = new Problem(4);
		problem.addUpdate(new Update(2, 2, 2, new BigInteger("4")));
		problem.addQuery(new Query(1, 1, 1, 3, 3, 3));
		problem.addUpdate(new Update(1, 1, 1, new BigInteger("23")));
		problem.addQuery(new Query(2, 2, 2, 4, 4, 4));
		problem.addQuery(new Query(1, 1, 1, 3, 3, 3));

		List<BigInteger> sumInRange = solverUtil.solve(problem);
		
		assertEquals(new BigInteger("4"), sumInRange.get(0));
		assertEquals(new BigInteger("4"), sumInRange.get(1));
		assertEquals(new BigInteger("27"), sumInRange.get(2));
		
	}
	
	@Test
	public void givenSampleProblem2_solve_ShouldSuccess() {
		Problem problem = new Problem(2);
		problem.addUpdate(new Update(2, 2, 2, new BigInteger("1")));
		problem.addQuery(new Query(1, 1, 1, 1, 1, 1));
		problem.addQuery(new Query(1, 1, 1, 2, 2, 2));
		problem.addQuery(new Query(2, 2, 2, 2, 2, 2));

		List<BigInteger> sumInRange = solverUtil.solve(problem);
		
		assertEquals(new BigInteger("0"), sumInRange.get(0));
		assertEquals(new BigInteger("1"), sumInRange.get(1));
		assertEquals(new BigInteger("1"), sumInRange.get(2));
		
	}	

}
