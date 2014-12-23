/**
 * 
 */
package hierarchy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import eval.Constraint;

/**
 * @author jwvl
 * @date 02/11/2014
 * 
 */
public class Hierarchy implements Iterable<StochasticConstraint> {
	List<StochasticConstraint> constraints;
	private static Random rnd = new Random();

	private Hierarchy() {
		constraints = new ArrayList<StochasticConstraint>();
	}

	public static Hierarchy createInstance() {
		Hierarchy result = new Hierarchy();
		return result;
	}

	public static Hierarchy createFromList(List<StochasticConstraint> list) {
		Hierarchy result = new Hierarchy();
		result.constraints.addAll(list);
		return result;
	}

	/**
	 * Turns an ordered list of Constraints into a stochastic Hierarchy, all in
	 * same stratum, with ranking inversely correlating w/ index in input
	 * 
	 * @param list
	 *            A list of unranked Constraints
	 * @return A Hierarchy thus ranked
	 */
	public static Hierarchy createFromConstraintOrder(List<Constraint> list) {
		Hierarchy result = new Hierarchy();
		int n = list.size();
		for (int i = 0; i < n; i++) {
			Constraint c = list.get(i);
			double ranking = (double) n - i;
			StochasticConstraint toAdd = StochasticConstraint
					.createWithRanking(c, ranking);
			result.add(toAdd);
		}
		result.sortByDisharmony();
		return result;
	}

	public void add(StochasticConstraint c) {
		constraints.add(c);
	}

	public List<StochasticConstraint> getList() {
		return constraints;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<StochasticConstraint> iterator() {
		// TODO Auto-generated method stub
		return constraints.iterator();
	}

	public int size() {
		return constraints.size();
	}

	public void sortByRankingValue() {
		Collections.sort(constraints, StochasticConstraint.RankingComparator);
	}

	public void sortByDisharmony() {
		Collections
				.sort(constraints, StochasticConstraint.DisharmonyComparator);
	}

	/**
	 * @param i
	 * @return
	 */
	public Constraint getConstraintAtIndex(int i) {
		return constraints.get(i).getConstraint();
	}

	/**
	 * @param i
	 * @return
	 */
	public StochasticConstraint getStochasticConstraintAt(int i) {
		return constraints.get(i);
	}

	/**
	 * Swaps the ranking values of two constraints.
	 * 
	 * @param a
	 *            First constraint to swap
	 * @param b
	 *            Second constraint to swap
	 * @return True if operation was successful.
	 */
	public boolean swapConstraintsThroughRanking(StochasticConstraint a,
			StochasticConstraint b) {
		if (a.getStratum() != b.getStratum()) // Not a legal operation if not in
												// same stratum...
			return false;
		double aRanking = a.getRankingValue();
		double aDisharmony = a.getDisharmony();
		double bRanking = b.getRankingValue();
		double bDisharmony = b.getDisharmony();
		a.setDisharmony(bDisharmony);
		a.setRankingValue(bRanking);
		b.setDisharmony(aDisharmony);
		b.setRankingValue(aRanking);
		sortByDisharmony();
		return true;
	}

	public boolean swapConstraintsByIndex(int aIndex, int bIndex) {
		if (aIndex < 0 || aIndex >= size())
			throw new ArrayIndexOutOfBoundsException(
					"Index of first constraint out of range: " + aIndex);
		if (bIndex < 0 || bIndex >= size())
			throw new ArrayIndexOutOfBoundsException(
					"Index of second constraint out of range: " + bIndex);
		StochasticConstraint a = constraints.get(aIndex);
		StochasticConstraint b = constraints.get(bIndex);
		return swapConstraintsThroughRanking(a, b);
	}

	public void sample(double sigma) {
		for (StochasticConstraint sc : constraints) {
			double noise = rnd.nextGaussian() * sigma;
			sc.setDisharmony(sc.getRankingValue() + noise);
		}
	}

}
