/**
 * 
 */
package eval;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author jwvl
 * @date 25/09/2014
 * 
 */

public class ConstraintSet implements Iterable<Constraint> {

	private ConstraintSet() {
		this.constraints = new TreeSet<Constraint>(Constraint.idOrdering);
	}
	
	/*
	 * Static constructor method.
	 */
	public static ConstraintSet createInstance() {
		ConstraintSet result = new ConstraintSet();
		return result;
	}

	private SortedSet<Constraint> constraints;
	
	
	
	public void addConstraint(Constraint c) {
		constraints.add(c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Constraint> iterator() {
		return constraints.iterator();
	}

	/**
	 * @return Size of this CON
	 */
	public int size() {
		return constraints.size();
	}

	/**
	 * @return Constraint set as Set
	 */
	public SortedSet<Constraint> getSet() {
		return constraints;
	}

}
