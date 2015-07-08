/**
 * 
 */
package io.latex;

import eval.Evaluation;
import io.string.TableauWriter;
import tableaus.Tableau;
import tableaus.TableauCell;
import tableaus.TableauRowLabel;
import util.StringUtil;

/**
 * @author jwvl
 * @date 03/11/2014
 *
 */
public class LatexTableauWriter implements TableauWriter {
	private Tableau tableau;
	// Settings
	private boolean SLANT_CONSTRAINTS = true;
	
	private LatexTableauWriter(Tableau inputTableau) {
		setTableau(tableau);
	}
	
	public static LatexTableauWriter createInstance(Tableau tableau) {
		return new LatexTableauWriter(tableau);
	}

	@Override
	public void setTableau(Tableau tableau) {
		this.tableau = tableau;
		
	}

	@Override
	public String printToString() {
		Evaluation evaluation = tableau.getEvaluation();
		
		String inputString = "Input: " + evaluation.getInput().toString();
		StringBuffer result = new StringBuffer();
		result.append(writePreAmble());
		result.append(writeColumnInfo());
		result.append(writeConstraintRow());
		result.append(writeRows());
		// String winnerString = "--> ";
		// Find width of col -1
		for (TableauRowLabel l : tableau.getRowLabels()) {
			String s = l.toString();
			rowLabelWidth = Math.max(rowLabelWidth,
					s.length() + winnerString.length());
		}

		// Start with col names
		StringUtil.appendPadded(result, inputString, rowLabelWidth
				+ extraPadding, " ");
		for (String currCol: tableau.getColNames()) {
			StringUtil.appendPadded(result, currCol, currCol.length()+extraPadding," ");
		}
		result.append("\n");

		for (int row = 0; row < tableau.getNumRows(); row++) {
			// First do row label
			StringUtil.appendPadded(result, tableau.getRowLabels()[row].toString(),
					rowLabelWidth + extraPadding, " ");
			
			for (int col = 0; col < tableau.getNumCols(); col++) {
				int cellWidth = tableau.getColNames()[col].length()+extraPadding;
				// Get violations
				TableauCell current = tableau.getCellAt(row,col);
				String toPrint = current.toString();
				StringUtil.appendPadded(result, toPrint, cellWidth, " ");
				

			}
			result.append("\n");
		}
		return result.toString();
	}
	
	private String writeRows() {
		StringBuffer result =
		return null;
	}

	private String writeConstraintRow() {
		StringBuffer result = new StringBuffer("\\multicolumn{3}{|c||}{}");
		for (String colName: tableau.getColNames()) {
			result.append("& ");
			result.append("*\\textsc{");
			result.append(colName);
			result.append("} ");
		}
		result.append("\\\\[0.5ex]");
		result.append("\n\\hline ");
		return result.toString();
	}

	private String writeColumnInfo() {
		StringBuffer result = new StringBuffer("\\scalebox{1}[1]{\\begin{tabular}[t]{|rrl|");
		for (int i = 0; i < tableau.getNumCols(); i++) {
			result.append("c|");
		}
		result.append("} \\hline \n");
		return result.toString();
	}

	private String writePreAmble() {
		StringBuffer result = new StringBuffer("\\begin{figure}[htp]\n");
		result.append("\\begin{center} \\renewcommand*\\arraystretch{1.2}\n");
		return result.toString();
	}
	

	
	

}
