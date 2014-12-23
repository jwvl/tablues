/**
 * 
 */
package harmony;

/**
 * @author jwvl
 * @date 02/11/2014
 * 
 */
public class HarmonyOT extends Harmony {

	private HarmonyOT(int size) {
		vector = new int[size];
	}
	
	public static HarmonyOT createInstance(int size) {
		return new HarmonyOT(size);
	}

	@Override
	public int compareTo(Harmony o) {
		if (!(o instanceof HarmonyOT)) {
			System.err.println("Harmonies are not comparable!");
			return 0;
		}
		HarmonyOT other = (HarmonyOT) o;
		if (other.vector.length != this.vector.length) {
			System.err.println("Harmonies are not of equal length!");
			return 0;
		}

		for (int i = 0; i < vector.length; i++) {
			if (vector[i] != other.vector[i]) {
				return (other.vector[i] - vector[i]);
			}
		}
		return 0;

	}



	/* (non-Javadoc)
	 * @see harmony.Harmony#add(harmony.Harmony)
	 */
	@Override
	public Harmony add(Harmony toAdd) {
		if (!(toAdd instanceof HarmonyOT)) {
			System.err.println("Harmonies are not comparable!");
			return null;
		}
		HarmonyOT other = (HarmonyOT) toAdd;

		int minLength = Math.min(vector.length, other.vector.length);
		int maxLength = Math.max(vector.length, other.vector.length);
		HarmonyOT result = createInstance(maxLength);
		for (int i=0; i< minLength; i++) {
			result.vector[i] = this.vector[i]+other.vector[i];
		}
		return result;
	}

}
