/**
 * 
 */
package hierarchy;

import java.text.DecimalFormat;
import java.util.Comparator;

import eval.Constraint;


/**
 * A StochasticConstraint is a composition class that is used to put a Constraint in
 * a real-valued stochastic Hierarchy, as proposed by Boersma (1998 et seq).
 * StochasticConstraints have a "ranking value" and "disharmony". They are sorted on
 * the latter, which is the former plus some Gaussian noise.
 * 
 * @author jwvl
 * @date May 3, 2014
 * 
 */
public class StochasticConstraint {
	private final Constraint constraint;
	private int stratum = 0;
	private double rankingValue;
	private double disharmony;
	private double defaultRankingValue = 100.0;
	private double plasticity = 1.0;
	private static final DecimalFormat df = new DecimalFormat("#.##");

	/** Public constructor. Ranking value is set to default.
	 * @param c Constraint that will be contained by this object.
	 */
	public StochasticConstraint(Constraint c) {
		constraint = c;
		rankingValue = defaultRankingValue;
		disharmony = rankingValue;
	}

	/**
	 * This method makes a "half-deep" copy of the StochasticConstraint: the
	 * Constraint is not copied, the values are.
	 * 
	 * @return A copy of this ranked constraint
	 */
	public StochasticConstraint copy() {
		StochasticConstraint result = new StochasticConstraint(constraint);
		result.setRankingValue(this.rankingValue);
		result.setDisharmony(this.disharmony);
		result.setStratum(this.stratum);
		return result;
	}

	public double getRankingValue() {
		return rankingValue;
	}

	/** Adds a value delta (which can be positive or negative) to this
	 * StochasticConstraint's ranking value.
	 * @param delta Value to add
	 */
	public void updateRankingValue(double delta) {
		this.rankingValue += delta;
		}

	public void setRankingValue(double rankingValue) {
		this.rankingValue = rankingValue;
	}

	public double getDisharmony() {
		return disharmony;
	}

	public void setDisharmony(double disharmony) {
		this.disharmony = disharmony;
	}

	/**
	 * Custom comparator. Compares by disharmony first. This would be the usual
	 * way of comparing in a Hierarchy.
	 */
	public static Comparator<StochasticConstraint> DisharmonyComparator = new Comparator<StochasticConstraint>() {
		public int compare(StochasticConstraint a, StochasticConstraint b) {
			if (a.stratum != b.stratum)
				return a.stratum - b.stratum;
			double aD = a.disharmony;
			double bD = b.disharmony;
			if (bD < aD)
				return -1;
			else if (bD > aD)
				return 1;
			else {
				double aR = a.rankingValue;
				double bR = b.rankingValue;
				if (bR < aR)
					return -1;
				else if (bR > aR)
					return 1;
			}
			return 0;
		}
	};

	/**
	 * Compares StochasticConstraints by ranking values.
	 */
	public static Comparator<StochasticConstraint> RankingComparator = new Comparator<StochasticConstraint>() {
		public int compare(StochasticConstraint a, StochasticConstraint b) {
			if (a.stratum != b.stratum)
				return a.stratum - b.stratum;
			double aR = a.rankingValue;
			double bR = b.rankingValue;
			if (bR < aR)
				return -1;
			else if (bR > aR)
				return 1;
			else {
				double aD = a.disharmony;
				double bD = b.disharmony;
				if (bD < aD)
					return -1;
				else if (bD > aD)
					return 1;
			}
			return 0;
		}
	};

	public Constraint getConstraint() {
		return constraint;
	}

	/**
	 * @param defaultRankingValue
	 *            the defaultRankingValue to set
	 */
	public void setDefaultRankingValue(double defaultRankingValue) {
		this.defaultRankingValue = defaultRankingValue;
	}

	/**
	 * @return the defaultRankingValue
	 */
	public double getDefaultRankingValue() {
		return defaultRankingValue;
	}

	public int getStratum() {
		return stratum;
	}

	public void setStratum(int stratum) {
		this.stratum = stratum;
	}

	/**
	 * @return A String of the constraint with the ranking value and disharmony, separated by a tab
	 */
	public String toStringWithRankings() {
		return constraint.toString() + "\t" + getFormattedRanking() + "\t"
				+ getFormattedDisharmony();
	}


	/**
	 * @return A String of the constraint with the ranking value, separated by a tab
	 */
	public String toStringWithRanking() {
		return constraint.toString() + "\t" + getFormattedRanking();
	}

	public String toString() {
		return constraint.toString();
	}

	public void resetRankingValue() {
		this.rankingValue = defaultRankingValue;

	}

	/** 
	 * @return Ranking value, formatted with two decimal places
	 */
	public String getFormattedRanking() {
		return df.format(rankingValue);
	}

	/**
	 * @return Disharmony, formatted with two decimal places
	 */
	public String getFormattedDisharmony() {
		return df.format(disharmony);
	}

	/**
	 * @param c
	 * @param ranking
	 * @return
	 */
	public static StochasticConstraint createWithRanking(Constraint c,
			double ranking) {
		StochasticConstraint result = new StochasticConstraint(c);
		result.setRankingValue(ranking);
		result.setDisharmony(ranking);
		result.setStratum(1);
		return result;
	}
	

}
