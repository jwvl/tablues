/**
 * 
 */
package gen;

import hierarchy.Hierarchy;

import java.util.ArrayList;
import java.util.List;

import eval.Constraint;
import eval.ConstraintSet;

/** A CandidateSpace (better come up with more accurate name at some point)
 * wraps a constraint set with a (number of) candidate sets
 * @author jwvl
 * @date 03/11/2014
 *
 */
public class Grammar {
	String name;
	ConstraintSet con;
	List<CandidateSet> candidateSets;
	
	private Grammar(ConstraintSet con) {
		this.name = "Candidate space";
		this.con = con;
		this.candidateSets = new ArrayList<CandidateSet>();
	}
	
	
	public static Grammar createInstance(ConstraintSet con) {
		Grammar result = new Grammar(con);
		return result;
	}
	
	public void addCandidateSet(CandidateSet toAdd) {
		candidateSets.add(toAdd);
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("CandidateSpace with name " + name+"\n");
		result.append("Constraints:\n");
		for (Constraint c: con) {
			result.append("\t"+c.toString()+"\n");
		}
		
		result.append("Candidate sets:\n");
		for (CandidateSet cs: candidateSets) {
			result.append(cs.toString());
		}
		return result.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Hierarchy createHierarchy() {
		List<Constraint> conAsList = new ArrayList<Constraint>(con.size());
		conAsList.addAll(con.getSet());
		Hierarchy result = Hierarchy.createFromConstraintOrder(conAsList);
		return result;
	}


	/**
	 * @return
	 */
	public List<CandidateSet> getAllCandidateSets() {
		return candidateSets;
	}
	
	
	

}
