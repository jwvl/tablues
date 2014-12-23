/**
 * 
 */
package harmony;

/**
 * @author jwvl
 * @date 02/11/2014
 *
 */
public class HarmonyHG extends Harmony {
	private double summedCost;
	double[] weightsVector;

	
	private HarmonyHG(int size) {
		vector = new int[size];
		weightsVector = new double[size];
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Harmony o) {
		if (!(o instanceof HarmonyHG)) {
			System.err.println("Harmonies are not comparable!");
			return 0;
		}
		HarmonyHG other = (HarmonyHG) o;
		if (other.vector.length != this.vector.length) {
			System.err.println("Harmonies are not of equal length!");
			return 0;
		}

		return Double.compare(summedCost, other.summedCost);
	}

	/* (non-Javadoc)
	 * @see harmony.Harmony#add(harmony.Harmony)
	 */
	@Override
	public Harmony add(Harmony toAdd) {
		if (!(toAdd instanceof HarmonyHG)) {
			System.err.println("Harmonies are not comparable!");
			return null;
		}
		HarmonyHG other = (HarmonyHG) toAdd;

		int minLength = Math.min(vector.length, other.vector.length);
		int maxLength = Math.max(vector.length, other.vector.length);
		HarmonyHG result = createInstance(maxLength);
		for (int i=0; i< minLength; i++) {
			result.vector[i] = this.vector[i]+other.vector[i];
		}
		return result;
	}

	/**
	 * @param maxLength
	 * @return
	 */
	public static HarmonyHG createInstance(int size) {
		return new HarmonyHG(size);
	}
	
	public void setCost(double c) {
		this.summedCost = c;
	}

}
