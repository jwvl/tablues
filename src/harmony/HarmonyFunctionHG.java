/**
 * 
 */
package harmony;

import gen.candidate.Candidate;
import hierarchy.Hierarchy;
import hierarchy.StochasticConstraint;

/**
 * An implementation of HG harmony (i.e. with weighted constraints)
 * 
 * @author jwvl
 * @date 02/11/2014
 * 
 */
public class HarmonyFunctionHG extends HarmonyFunction {

	private static final HarmonyFunctionHG SINGLETON = new HarmonyFunctionHG();

	/*
	 * @see harmony.HarmonyFunction#assignHarmony(java.util.List, gen.Candidate)
	 */
	@Override
	Harmony assignHarmony(Hierarchy h, Candidate can) {
		HarmonyHG result = HarmonyHG.createInstance(h.size());
		result.constructVector(h, can);
		double currCost = 0.0;

		for (int i =0; i < h.size(); i++) {
			int m = result.vector[i];
			if (m > 0) {
				StochasticConstraint sc = h.getStochasticConstraintAt(i);
				double d = sc.getDisharmony();
				double weighted = d*m;
				result.weightsVector[i] = weighted;
				currCost += weighted;
			}
		}
		result.setCost(currCost);
		return result;
	}

	/**
	 * @return
	 */
	public static HarmonyFunction getInstance() {
		return SINGLETON;
	}

}
