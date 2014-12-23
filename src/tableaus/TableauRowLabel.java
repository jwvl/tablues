/**
 * 
 */
package tableaus;

import gen.Candidate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jwvl
 * @date 03/11/2014 A Tableau Row label contains a candidate (name) and some
 *       extra info
 */
public class TableauRowLabel {
	private Candidate candidate;
	private int indexInTableau;
	private List<CandidateSymbol> symbols;

	private TableauRowLabel() {
		symbols = new ArrayList<CandidateSymbol>();
	};

	public static TableauRowLabel createInstance(Candidate can, int i) {
		TableauRowLabel result = new TableauRowLabel();
		result.candidate = can;
		result.indexInTableau = i;
		return result;
	}
	
	public void addSymbol(CandidateSymbol toAdd) {
		symbols.add(toAdd);
	}

	@Override
	public String toString() {
		String result = "";
		for (CandidateSymbol cs : symbols) {
			result += (cs.toString() + " ");
		}
		result += (indexInTableau + "  ");
		return result + candidate.toString();
	}

}
