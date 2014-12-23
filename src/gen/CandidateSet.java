/**
 * 
 */
package gen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author jwvl
 * @date 25/09/2014
 *
 */
public class CandidateSet implements Iterable<Candidate>{
	private Input input;
	private List<Candidate> candidates;
	
	

	private CandidateSet(Input input) {
		super();
		this.input = input;
		candidates = new ArrayList<Candidate>();
	}
	
	public static CandidateSet createInstance(Input input) {
		CandidateSet result = new CandidateSet(input);
		return result;
	}
	
	public void addCandidate(Candidate can) {
			candidates.add(can);
	}
	
	public void addCandidates(Candidate... c) {
		for (Candidate can: c) {
			candidates.add(can);
		}
	}

	@Override
	public String toString() {
		String result =("Input: "+input);
		for (Candidate c: candidates) {
			result+=("\n"+c.toString());
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Candidate> iterator() {
		// TODO Auto-generated method stub
		return candidates.iterator();
	}

	/**
	 * @return Size of this candidate set
	 */
	public int size() {
		return candidates.size();
	}

	/**
	 * @return the Input for this candidate set
	 */
	public Input getInput() {
		return input;
	}
	
	

}
