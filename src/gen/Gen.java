/**
 * 
 */
package gen;

import gen.candidate.CandidateSet;
import gen.input.Input;

/**
 * @author jwvl
 * @date 02/11/2014
 *
 */
public abstract class Gen {
	
	public abstract CandidateSet getCandidates(Input in);

}
