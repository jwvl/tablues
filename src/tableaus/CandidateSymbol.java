/**
 * 
 */
package tableaus;

/**
 * @author jwvl
 * @date 03/11/2014
 * 
 */
public class CandidateSymbol {
	public final static CandidateSymbol WINNER = createFromString("-->");
	public final static CandidateSymbol TARGET = createFromString(" v ");
	public final static CandidateSymbol SAD = createFromString(":-(");

	private String stringRepresentation;

	private CandidateSymbol() {
	}

	public static CandidateSymbol createFromString(String rep) {
		CandidateSymbol result = new CandidateSymbol();
		result.setStringRepresentation(rep);
		return result;
	}

	/**
	 * @return the stringRepresentation
	 */
	public String getStringRepresentation() {
		return stringRepresentation;
	}
	
	public String toString() {
		return stringRepresentation;
	}

	/**
	 * @param stringRepresentation
	 *            the stringRepresentation to set
	 */
	public void setStringRepresentation(String stringRepresentation) {
		this.stringRepresentation = stringRepresentation;
	}

}
