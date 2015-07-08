/**
 * 
 */
package eval;

import gen.candidate.CandidateSet;
import gen.input.Input;
import harmony.Harmony;
import hierarchy.Hierarchy;

import java.util.Collections;
import java.util.List;

/**
 * @author jwvl
 * @date 02/11/2014
 *
 */
public class Evaluation {
	
	private List<EvaluatedCandidate> evaluated;
	private List<EvaluatedCandidate> winners;
	private Eval evaluator;
	private CandidateSet candidates;
	private Hierarchy hierarchy;
	
	private Evaluation(CandidateSet cs) {
		candidates = cs;
	}
	
	public static Evaluation createInstance(CandidateSet cs, Hierarchy h, Eval e) {
		Evaluation result = new Evaluation(cs);
		result.setEvaluator(e);
		result.setHierarchy(h);
		return result;
	}
	
	public void sampleHierarchy(double sigma) {
		hierarchy.sample(sigma);
	}
	
	public void run() {
		evaluated = evaluator.evaluateCandidates(hierarchy, candidates);
		winners = evaluator.getWinnersFromList(evaluated);
	}
	
	public int getMinimumViolation(int constraintIndex) {
		int result = Integer.MAX_VALUE;
		for (EvaluatedCandidate ec: evaluated) {
			Harmony currentHarmony = ec.getHarmony();
			result = Math.min(result,currentHarmony.getVectorValueAt(constraintIndex));
		}
		return result;
	}
	
	public List<EvaluatedCandidate> getEvaluatedCandidates() {
		return evaluated;
	}

	public List<EvaluatedCandidate> getWinners() {
		return winners;
	}

	public Eval getEvaluator() {
		return evaluator;
	}

	public CandidateSet getCandidates() {
		return candidates;
	}

	public Hierarchy getHierarchy() {
		return hierarchy;
	}

	public void setEvaluator(Eval evaluator) {
		this.evaluator = evaluator;
	}

	public void setCandidates(CandidateSet candidates) {
		this.candidates = candidates;
	}

	public void setHierarchy(Hierarchy h) {
		this.hierarchy = h;
	}
	
	public void sortEvaluatedCandidatesByHarmony() {
		Collections.sort(evaluated);
	}
	
	public Input getInput() {
		return candidates.getInput();
	}
	
	public boolean isWinner(EvaluatedCandidate ec) {
		return (winners.contains(ec));
	}
	
	public EvaluatedCandidate getEvaluatedByIndex(int index) {
		return evaluated.get(index);
	}

}
