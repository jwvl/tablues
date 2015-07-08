/**
 * 
 */
package harmony;

import gen.candidate.Candidate;
import gen.candidate.CandidateSet;
import hierarchy.Hierarchy;

import java.util.ArrayList;
import java.util.List;

import eval.EvaluatedCandidate;

/**
 * @author jwvl
 * @date 02/11/2014
 * 
 */
public abstract class HarmonyFunction {

	public List<EvaluatedCandidate> evaluate(Hierarchy h,
			CandidateSet candidates) {

		ArrayList<EvaluatedCandidate> result = new ArrayList<EvaluatedCandidate>(
				h.size());
		for (Candidate c : candidates) {
			Harmony curr = assignHarmony(h, c);
			result.add(EvaluatedCandidate.createInstance(c, curr));

		}
		return result;

	}

	abstract Harmony assignHarmony(Hierarchy h, Candidate c);

}
