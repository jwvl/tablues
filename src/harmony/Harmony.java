/**
 * 
 */
package harmony;

import eval.Constraint;
import gen.Candidate;
import hierarchy.Hierarchy;

/**
 * @author jwvl
 * @date 02/11/2014 Harmony is an abstract, orderable value that can be assigned
 *       to some candidate.
 */
public abstract class Harmony implements Comparable<Harmony> {
	protected int[] vector;

	public void constructVector(Hierarchy h, Candidate can) {
		vector = new int[h.size()];
		for (int i = 0; i < h.size(); i++) {
			Constraint c = h.getConstraintAtIndex(i);
			int m = c.getNumViolations(can);
			vector[i] = m;
		}
	}
	
	public int[] getVector() {
		return vector;
	}
	
	/**
	 * @param i
	 * @param m
	 */
	public void setVectorValueAtIndex(int i, int m) {
		vector[i] = m;
	}

	public abstract Harmony add(Harmony toAdd);

	/**
	 * @param constraintIndex
	 * @return
	 */
	public int getVectorValueAt(int i) {
		return vector[i];
	}

}
