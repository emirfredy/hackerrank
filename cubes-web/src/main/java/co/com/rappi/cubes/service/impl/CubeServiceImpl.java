package co.com.rappi.cubes.service.impl;

import java.io.InputStream;
import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.rappi.cubes.dao.ProblemRepository;
import co.com.rappi.cubes.model.Problem;
import co.com.rappi.cubes.model.Query;
import co.com.rappi.cubes.model.Update;
import co.com.rappi.cubes.service.CubeService;
import co.com.rappi.cubes.service.util.SolverUtil;

/**
 * Default implementation of {@link CubeService} interface
 * 
 * @author <a href="emirfredy@gmail.com">Emir Cortes Trujillo</a>
 */
@Service
public class CubeServiceImpl implements CubeService {
	
	/** The repository that provides access to {@link Problem} */
	@Autowired
	private ProblemRepository problemRepository;
	
	/** The utility that helps to solve problems */
	@Autowired
	SolverUtil solverUtil;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.rappi.cubes.service.impl.CubeService#create(co.com.rappi.cubes.
	 * model.Problem)
	 */
	@Override
	public Long create(Problem problem) {
		final Problem stored = problemRepository.save(problem);
		return stored.getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.rappi.cubes.service.impl.CubeService#find(java.lang.Long)
	 */
	@Override
	public Problem find(Long id) {
		return problemRepository.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.rappi.cubes.service.impl.CubeService#addUpdate(java.lang.Long,
	 * co.com.rappi.cubes.model.Update)
	 */
	@Override
	public void addUpdate(Long id, Update update) {
		Problem problem = problemRepository.findOne(id);
		problem.addUpdate(update);
		problemRepository.save(problem);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.rappi.cubes.service.impl.CubeService#addQuery(java.lang.Long,
	 * co.com.rappi.cubes.model.Query)
	 */
	@Override
	public void addQuery(Long id, Query query) {
		Problem problem = problemRepository.findOne(id);
		problem.addQuery(query);
		problemRepository.save(problem);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.rappi.cubes.service.impl.CubeService#getSample(java.lang.Long)
	 */
	@Override
	public Problem getSample() {
		Problem problem = new Problem(5);
		problem.addUpdate(new Update(1, 1, 1, new BigInteger("21")));
		problem.addQuery(new Query(1, 1, 1, 4, 4, 4));
		problem.addUpdate(new Update(2, 1, 1, new BigInteger("50")));
		problem.addQuery(new Query(2, 2, 2, 5, 5, 5));
		problem.addQuery(new Query(1, 1, 1, 3, 3, 3));
		return problem;
	}

	/*
	 * (non-Javadoc)
	 * @see co.com.rappi.cubes.service.CubeService#solve(java.lang.Long)
	 */
	@Override
	public List<BigInteger> solve(Long id) {
		Problem problem = problemRepository.findOne(id);
		List<BigInteger> sums = solverUtil.solve(problem);
		problemRepository.delete(problem);
		return sums;
	}

	/*
	 * (non-Javadoc)
	 * @see co.com.rappi.cubes.service.CubeService#solveInBatch(java.io.InputStream)
	 */
	@Override
	public List<BigInteger> solveInBatch(InputStream inputStream) {

		return solverUtil.solveInBatch(inputStream);
	}
}
