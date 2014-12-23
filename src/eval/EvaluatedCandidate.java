/**
 * 
 */
package eval;

import gen.Candidate;
import harmony.Harmony;

/**
 * @author jwvl
 * @date 02/11/2014
 * 
 */
public class EvaluatedCandidate implements Comparable<EvaluatedCandidate> {
	Candidate can;
	Harmony h;
	
	public Harmony getHarmony() {
		return h;
	}
	
	private EvaluatedCandidate(Candidate can, Harmony h) {
		this.can = can;
		this.h = h;
	}

	public static EvaluatedCandidate createInstance(Candidate can, Harmony h) {
		return new EvaluatedCandidate(can, h);
	}

	/*
	 * Basically wraps to the CompareTo method of this Evaluated Candidate's
	 * Harmony object.
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(EvaluatedCandidate o) {
		if (!(o instanceof EvaluatedCandidate)) {
			System.err.println("Error: cannot compare");
			return 0;
		}
		EvaluatedCandidate other = (EvaluatedCandidate) o;
		return h.compareTo(other.h);
	}
	
	/**
	 * @return The Candidate contained in this candidate evaluation
	 */
	public Candidate getCandidate() {
		return can;
	}

}
