/**
 * 
 */
package tableaus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jwvl
 * @date 06/10/2014
 * 
 */
public class TableauCell {
	boolean greyedOut;
	List<Violation> violations;

	private TableauCell() {
		violations = new ArrayList<Violation>();
		greyedOut = false;
	}

	public static TableauCell createEmpty() {
		TableauCell result = new TableauCell();
		return result;
	}

	public static TableauCell createWithDefaultViolations(int nViolations,
			int fatalAt, boolean greyedOut) {
		TableauCell result = new TableauCell();
		for (int i = 0; i < nViolations; i++) {
			boolean fatal = (i == fatalAt);
			result.addViolation(AsteriskViolation.getInstance(fatal));
		}
		result.greyedOut = greyedOut;
		return result;
	}

	public void addViolation(Violation v) {
		violations.add(v);
	}
	
	public String toString() {
		StringBuffer result = new StringBuffer();
		for (Violation v: violations) {
			result.append(v.toString());
		}
		return result.toString();
	}

}
