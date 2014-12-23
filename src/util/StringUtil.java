/**
 * 
 */
package util;

/**
 * @author jwvl
 * @date 03/11/2014
 * 
 */
public class StringUtil {

	public static String padToLength(String input, int length, String padder) {
		StringBuffer result = new StringBuffer(input);
		int diff = length - input.length();
		for (int i = 0; i < diff; i++) {
			result.append(padder);
		}

		return result.toString();
	}
	
	public static void appendPadded(StringBuffer building, String input, int length, String padder) {
		building.append(padToLength(input,length,padder));
	}

}
