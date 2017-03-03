/**
 * 
 */
package co.com.rappi.cubes.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.rappi.cubes.model.Problem;
import co.com.rappi.cubes.model.Query;
import co.com.rappi.cubes.model.Update;
import co.com.rappi.cubes.service.CubeService;

/**
 * The controller that exposes the problem end points
 * 
 * @author <a href="emirfredy@gmail.com">Emir Cortes Trujillo</a>
 */
@RestController
@RequestMapping("/api/problem")
public class CubeController {

	/**
	 * The Service that provides solving problems capabilities and storage
	 * services
	 */
	@Autowired
	private CubeService cubeService;

	/**
	 * Creates a new {@link Problem} to which updates and queries can be added
	 * later
	 * 
	 * @param problem
	 *            A {@link Problem}
	 * @return The id of the problem just created
	 */
	@PostMapping()
	public ResponseEntity<Long> createProblem(@RequestBody Problem problem) {
		Long id = cubeService.create(problem);
		return new ResponseEntity<Long>(id, HttpStatus.OK);
	}

	/**
	 * Recovers a {@link Problem} given its id
	 * 
	 * @param id
	 *            The id of the problem to be retrieved
	 * @return A {@link Problem} corresponding to the given id
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Problem> getProblem(@PathVariable("id") Long id) {

		Problem problem = cubeService.find(id);

		if (problem != null) {
			return new ResponseEntity<Problem>(problem, HttpStatus.OK);
		} else {
			return new ResponseEntity<Problem>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Adds a {@link Query} to the {@link Problem} identified by id
	 * 
	 * @param id
	 *            The id of the {@link Problem}
	 * @param query
	 *            A {@link Query} to be added to the problem
	 * @return
	 */
	@PutMapping("{id}/query")
	public ResponseEntity<Void> addQuery(@PathVariable("id") Long id, @RequestBody Query query) {
		cubeService.addQuery(id, query);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Adds a {@link Update} to the {@link Problem} identified by id
	 * 
	 * @param id
	 *            The id of the {@link Problem}
	 * @param update
	 *            An {@link Update} to be added to the problem
	 * @return
	 */
	@PutMapping("{id}/update")
	public ResponseEntity<Void> addUpdate(@PathVariable("id") Long id, @RequestBody Update update) {
		cubeService.addUpdate(id, update);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Solves the {@link Problem} identified by id
	 * 
	 * @param id
	 *            The id of the {@link Problem}
	 * @return A list of summations
	 */
	@DeleteMapping("/{id}")
	public List<BigInteger> solveProblem(@PathVariable("id") Long id) {

		return cubeService.solve(id);
	}

	/**
	 * Returns a sample {@link Problem}
	 * 
	 * @return An example {@link Problem}
	 */
	@GetMapping()
	public ResponseEntity<Problem> getSampleProblem() {

		Problem problem = cubeService.getSample();
		return new ResponseEntity<Problem>(problem, HttpStatus.OK);
	}

}
