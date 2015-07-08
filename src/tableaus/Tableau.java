/**
 * 
 */
package tableaus;

import harmony.Harmony;

import java.util.Arrays;
import java.util.List;

import eval.Constraint;
import eval.EvaluatedCandidate;
import eval.Evaluation;

/**
 * @author jwvl
 * @date 06/10/2014
 * 
 */
public class Tableau {

	private Evaluation evaluation;

	private TableauCell[][] contents;
	private TableauRowLabel[] rowLabels;
	private String[] colNames;
	private int[] columnsWithFatalViolations;
	private int numRows;
	private int numCols;

	private Tableau(Evaluation e) {
		evaluation = e;
		numRows = e.getEvaluatedCandidates().size();
		numCols = e.getHierarchy().size();
		columnsWithFatalViolations = new int[numRows];
		fillCells();
		setRowLabels();
		setColNames();
	}

	public static Tableau createInstance(Evaluation e) {
		Tableau result = new Tableau(e);
		return result;
	}

	/**
	 * Sets row labels for the tableau
	 */
	private void setRowLabels() {
		rowLabels = new TableauRowLabel[numRows];
		for (int i = 0; i < numRows; i++) {
			EvaluatedCandidate current = evaluation.getEvaluatedByIndex(i);
			boolean isWinner = evaluation.isWinner(current);
			TableauRowLabel toSet = TableauRowLabel.createInstance(
					current.getCandidate(), i);
			if (isWinner) {
				toSet.addSymbol(CandidateSymbol.WINNER);
			}
			// Assign to labels
			rowLabels[i] = toSet;
		}

	}

	/**
	 * Sets column names for the tableau
	 */
	private void setColNames() {
		colNames = new String[numCols];
		for (int i = 0; i < numCols; i++) {
			Constraint c = evaluation.getHierarchy().getConstraintAtIndex(i);
			colNames[i] = c.toString();
		}
	}

	private void fillCells() {

		List<EvaluatedCandidate> evaluatedCandidates = evaluation
				.getEvaluatedCandidates();

		contents = new TableauCell[numRows][numCols];

		Arrays.fill(columnsWithFatalViolations, numCols);
		for (int col = 0; col < numCols; col++) {

			// Get min. violations in this column
			int smallestM = evaluation.getMinimumViolation(col);
			for (int row = 0; row < numRows; row++) {
				EvaluatedCandidate current = evaluation.getEvaluatedByIndex(row);
				boolean isWinner = evaluation.isWinner(current);
				Harmony currentHarmony = current.getHarmony();
				int currentM = currentHarmony.getVectorValueAt(col);
				// Is candidate contending?
				boolean isContending = (col <= columnsWithFatalViolations[row]);
				// Is candidate fatally violating here?
				int fatalViolationIndex;
				if (!isWinner && isContending && currentM > smallestM) {
					fatalViolationIndex = smallestM;
					columnsWithFatalViolations[row] = col;
				} else {
					fatalViolationIndex = -1;
				}
				TableauCell toAdd = TableauCell.createWithDefaultViolations(
						currentM, fatalViolationIndex, !isContending);
				contents[row][col] = toAdd;
			}
		}
	}


	public Evaluation getEvaluation() {
		return evaluation;
	}

	public TableauRowLabel[] getRowLabels() {
		return rowLabels;
	}

	public String[] getColNames() {
		return colNames;
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumCols() {
		return numCols;
	}

	public TableauCell getCellAt(int row, int col) {
		return contents[row][col];
	}
}
