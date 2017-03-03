/**
 * 
 */
package co.com.rappi.cubes.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 * Represents a problem with its updates and queries 
 * 
 * @author <a href="emirfredy@gmail.com">Emir Cortes Trujillo</a>
 */
@Entity
public class Problem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/** The size of the cube */
	@Column
	@NotNull
	private int cubeSize;

	/** The amount of commands (updates and queries) to be executed in the problem*/
	@Column
	private int steps;

	/** The queries in the problem */
	@OneToMany(cascade = CascadeType.ALL)
	@OrderBy("step ASC")
	private List<Query> queries;
	
	/** The updates in the problem */
	@OneToMany(cascade = CascadeType.ALL)
	@OrderBy("step ASC")
	private List<Update> updates;
	
	/** The result of solving the problem*/
	@Transient
	private List<BigInteger> sums;

	public Problem() {
		updates = new ArrayList<>();
		queries = new ArrayList<>();
	}

	public Problem(int cubeSize) {
		this();
		this.cubeSize = cubeSize;
	}

	public int getCubeSize() {
		return cubeSize;
	}

	public List<Query> getQueries() {
		return queries;
	}

	public List<Update> getUpdates() {
		return updates;
	}

	public Long getId() {
		return id;
	}

	public List<BigInteger> getSums() {
		return sums;
	}

	public void setSums(List<BigInteger> sums) {
		this.sums = sums;
	}

	public int getSteps() {
		return steps;
	}

	/**
	 * Adds a {@link Query} to the problem
	 * @param query The {@link Query} to be added
	 */
	public void addQuery(final Query query) {
		if (queries.add(query)) {
			steps++;
			query.setStep(steps);
		}
	}

	/**
	 * Adds an {@link Update} to the problem
	 * @param query The {@link Update} to be added
	 */
	public void addUpdate(final Update update) {
		if (updates.add(update)) {
			steps++;
			update.setStep(steps);
		}
	}

}
