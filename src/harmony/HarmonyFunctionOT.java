/**
 * 
 */
package harmony;

import gen.Candidate;
import hierarchy.Hierarchy;

/**
 * An implementation of optimality-theoretic harmony
 * (i.e. with absolute constraint domination)
 * @author jwvl
 * @date 02/11/2014
 *
 */
public class HarmonyFunctionOT extends HarmonyFunction {

	private static final HarmonyFunctionOT SINGLETON = new HarmonyFunctionOT();
	
	/* 
	 * @see harmony.HarmonyFunction#assignHarmony(java.util.List, gen.Candidate)
	 */
	@Override
	Harmony assignHarmony(Hierarchy h, Candidate can) {
		HarmonyOT result = HarmonyOT.createInstance(h.size());
		result.constructVector(h, can);
		return result;
	}

	/**
	 * @return
	 */
	public static HarmonyFunction getInstance() {
		return SINGLETON;
	}

}
