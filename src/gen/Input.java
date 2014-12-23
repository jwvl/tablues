/**
 * 
 */
package gen;

/**
 * @author jwvl
 * @date 02/11/2014
 *
 */
public class Input {
	private String stringRepresentation;

	private Input(String stringRepresentation) {
		super();
		this.stringRepresentation = stringRepresentation;
	}
	
	public static Input createInstance(String rep) {
		Input result = new Input(rep);
		return result;
	}
	
	public String toString() {
		return stringRepresentation;
	}

}
