/**
 * 
 */
package tableaus;

/**
 * @author jwvl
 * @date 03/11/2014
 *
 */
public class AsteriskViolation extends Violation {

	public static Violation getInstance(boolean isFatal) {
		Violation result = new AsteriskViolation();
		result.setFatal(isFatal);
		return result;
	}
	
	@Override
	public String toString() {
		String result = "*";
		if (isFatal()) {
			result+="!";
		}
		return result;
	}
	
	
	

}
