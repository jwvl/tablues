/**
 * 
 */
package io;

import eval.Constraint;
import eval.ConstraintSet;
import gen.Candidate;
import gen.CandidateSet;
import gen.Grammar;
import gen.Input;

import java.util.List;

import util.StringCounter;

import com.google.common.collect.Lists;

/**
 * @author jwvl
 * @date Dec 20, 2014
 * 
 */
public class CSVReader {
	private String myFile;
	private MyStringTable contents;

	private CSVReader(String file, String sep) {
		myFile = file;
		contents = MyStringTable.fromFile(myFile, true, sep);
	}

	public static Grammar simpleGrammarFromFile(String file, String sep) {
		CSVReader tempReader = new CSVReader(file, sep);
		return tempReader.toSimpleGrammar();
	}

	private Grammar toSimpleGrammar() {
		// We have to get the constraint set first
		List<String> constraintNames = contents.colNames;
		List<Constraint> constraints = Lists.newArrayList();
		ConstraintSet cs = ConstraintSet.createInstance();
		for (int i = 1; i < constraintNames.size(); i++) {
			Constraint toAdd = Constraint
					.createInstance(constraintNames.get(i));
			cs.addConstraint(toAdd);
			constraints.add(toAdd);
		}
		List<String> candidateNames = contents.getColumnContents(0);
		Input input = Input.createInstance(contents.getColName(0));
		CandidateSet canSet = CandidateSet.createInstance(input);
		for (int i=0; i < contents.getNumRows(); i++) {
			String candidateString = candidateNames.get(i);
			Candidate iCandidate = Candidate.createInstance(candidateString);
			for (int j=0; j < constraints.size(); j++) {
				Constraint current = constraints.get(j);
				int colIndex = j+1;
				int nViolations = parseAsterisks(contents.getString(i, colIndex));
				current.setViolation(iCandidate, nViolations);
			}
			canSet.addCandidate(iCandidate);
		}
		Grammar result = Grammar.createInstance(cs);
		result.addCandidateSet(canSet);
		return result;

	}

	/**
	 * @param string
	 * @return
	 */
	private int parseAsterisks(String string) {
		return StringCounter.count(string, "*");
	}

}
