/**
 * 
 */
package eval;

import gen.candidate.Candidate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Ordering;

/** A constraint is implemented as in Riggle (2008): a (sparse) multiset (c \in C, m)
 * where m is a positive integer indicating the multiplicity of the constraint in C,
 * equating to the number of violations that candidate incurs for this constraint.
 * @author jwvl
 * @date 02/11/2014
 *
 */
public class Constraint {
	private String name;
	private Map<Candidate,Integer> violations;
	private List<String> symbols;
	private int id;
	private static int idCounter = 0;
	
	private Constraint(String name) {
		violations = new HashMap<Candidate,Integer>();
		this.name = name;
		this.id = idCounter++;
	}
	
	public static Constraint createInstance(String name) {
		Constraint result = new Constraint(name);
		return result;
	}

	@Override
	public String toString() {
		return name;
	}
	
	public int getNumViolations(Candidate c) {
		Integer result = violations.get(c);
		return result == null ? 0 : result;
	}
	
	public void setViolation(Candidate c, int nViolations) {
		if (nViolations == 0) {
			removeCandidate(c);
			return;
		}
		violations.put(c,nViolations);
	}

	/** Remove a candidate from the list of violators.
	 * @param c Candidate to remove
	 */
	private void removeCandidate(Candidate c) {
		violations.remove(c);
		
	}

	/**
	 * @return the symbols
	 */
	public List<String> getSymbols() {
		return symbols;
	}

	/**
	 * @param symbols the symbols to set
	 */
	public void setSymbols(List<String> symbols) {
		this.symbols = symbols;
	}
	
	public static Ordering<Constraint> idOrdering = new Ordering<Constraint>() {

		@Override
		public int compare(Constraint arg0, Constraint arg1) {
			return arg0.id - arg1.id;
		}
		
	};

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Constraint))
			return false;
		Constraint other = (Constraint) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
	

}
