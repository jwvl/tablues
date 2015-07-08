/**
 * 
 */
package test;

import eval.Constraint;
import eval.ConstraintSet;
import gen.Grammar;
import gen.candidate.Candidate;
import gen.candidate.CandidateSet;
import gen.candidate.StringCandidate;
import gen.input.Input;
import gen.input.StringInput;

/**
 * @author jwvl
 * @date 03/11/2014
 *
 */
public class SimpleExampleCreator {
	
	public static Grammar createThesisExample() {
		ConstraintSet con = ConstraintSet.createInstance();
		
		Constraint c_a = Constraint.createInstance("*2A-3B");
		Constraint c_b = Constraint.createInstance("*2B-3C");
		Constraint c_c = Constraint.createInstance("*2A");
		Constraint c_d = Constraint.createInstance("*2B");
		
		Input inp = StringInput.createInstance("(1A)");
		Candidate can_a = StringCandidate.createInstance("(1A,2A,3A)");
		Candidate can_b = StringCandidate.createInstance("(1A,2A,3B)");
		Candidate can_c = StringCandidate.createInstance("(1A,2B,3B)");
		Candidate can_d = StringCandidate.createInstance("(1A,2B,3C)");
		
		CandidateSet cs = CandidateSet.createInstance(inp);
		cs.addCandidates(can_a, can_b, can_c, can_d);
		
		c_a.setViolation(can_b,1);
		c_b.setViolation(can_d,1);
		c_c.setViolation(can_a,1);
		c_c.setViolation(can_b,1);
		c_d.setViolation(can_c,1);
		c_d.setViolation(can_d,1);
		
		con.addConstraint(c_a);
		con.addConstraint(c_b);
		con.addConstraint(c_c);
		con.addConstraint(c_d);
		
		Grammar result = Grammar.createInstance(con);
		result.addCandidateSet(cs);
		
		return result;
	}

}
