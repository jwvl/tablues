/**
 * 
 */
package test;

import eval.Eval;
import eval.Evaluation;
import gen.Grammar;
import gen.candidate.CandidateSet;
import hierarchy.Hierarchy;
import io.CSVReader;

import java.util.List;

import tableaus.Tableau;

/**
 * @author jwvl
 * @date 03/11/2014
 *
 */
public class TestTableauPrinting {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Grammar testGrammar = SimpleExampleCreator.createThesisExample();
		Grammar testGrammar = CSVReader.simpleGrammarFromFile("data/test.csv", "\t");
		Hierarchy toTest = testGrammar.createHierarchy();
		List<CandidateSet> allSets = testGrammar.getAllCandidateSets();
		Eval eval = Eval.createOptimalityEval();
		for (CandidateSet cs: allSets) {
			Evaluation current = Evaluation.createInstance(cs, toTest, eval);
			current.run();
			Tableau result = Tableau.createInstance(current);
			System.out.println(result.toPaddedText());
		}

	}

}
