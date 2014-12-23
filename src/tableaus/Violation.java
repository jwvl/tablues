/**
 * 
 */
package tableaus;

/** An abstract class presenting any kind of violation.
 * @author jwvl
 * @date 06/10/2014
 * 
 */
public abstract class Violation {
	private boolean isFatal;

	protected Violation() {
	}

	public boolean isFatal() {
		return isFatal;
	}

	public void setFatal(boolean isFatal) {
		this.isFatal = isFatal;
	}

}
