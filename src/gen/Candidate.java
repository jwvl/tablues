/**
 * 
 */
package gen;

/**
 * @author jwvl
 * @date 02/11/2014
 *
 */
public class Candidate {
	private String stringRepresentation;

	private Candidate(String stringRepresentation) {
		super();
		this.stringRepresentation = stringRepresentation;
		System.out.println("Created "+this);
	}
	
	

	/**	/** Public static constructor
	 * @param rep String representation of new Candidate
	 * @return a new Candidate
	 */
	public static Candidate createInstance(String rep) {
		Candidate result = new Candidate(rep);
		return result;
	}



	@Override
	public String toString() {
		return stringRepresentation;
	}
	
	

}
