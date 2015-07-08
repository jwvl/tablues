/**
 * 
 */
package eval;

import gen.candidate.CandidateSet;
import harmony.HarmonyFunction;
import harmony.HarmonyFunctionOT;
import hierarchy.Hierarchy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jwvl
 * @date 02/11/2014
 * 
 */
public class Eval {
	private HarmonyFunction hf;

	private Eval(HarmonyFunction hf) {
		this.hf = hf;
	}

	public static Eval createInstance(HarmonyFunction hf) {
		Eval result = new Eval(hf);
		return result;
	}

	public static Eval createOptimalityEval() {
		Eval result = new Eval(HarmonyFunctionOT.getInstance());
		return result;
	}

	public static Eval createHarmonicEval() {
		Eval result = new Eval(HarmonyFunctionOT.getInstance());
		return result;
	}

	public List<EvaluatedCandidate> evaluateCandidates(Hierarchy h,
			CandidateSet can) {
		return hf.evaluate(h, can);
	}

	public List<EvaluatedCandidate> getWinnersFromList(
			List<EvaluatedCandidate> list) {
		if (list == null) {
			System.err
					.println("Warning: list of candidates is null! Returning null.");
			return null;
		}
		if (list.isEmpty()) {
			System.err
					.println("Warning: list of candidates is empty! Returning null.");
			return null;
		}
		List<EvaluatedCandidate> currMin = new ArrayList<EvaluatedCandidate>();
		currMin.add(list.get(0));

		for (int i = 1; i < list.size(); i++) {
			EvaluatedCandidate toCheck = list.get(i);
			int comparison = toCheck.compareTo(currMin.get(0));
			if (comparison >= 0) {
				if (comparison > 0)
					currMin.clear();
				currMin.add(toCheck);
			}

		}

		return currMin; // TODO implement

	}

}
