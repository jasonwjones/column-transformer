package com.jasonwjones.columntransformer.rules;

import java.util.List;

import com.jasonwjones.columntransformer.Rule;

public abstract class AbstractRule implements Rule {

	protected int column;

	public AbstractRule(Integer column) {
		this.column = column;
	}

	protected void replaceColumn(int index, List<String> input, String newContent) {
		input.remove(index);
		input.add(index, newContent);
	}

	protected void replace(List<String> input, String newContent) {
		replaceColumn(column, input, newContent);
	}

	/**
	 * Convenience method that replaces a single column in the original list
	 * with all of the new columns in the given list.
	 * 
	 * @param sourceList
	 * @param newColumns
	 */
	protected void replace(List<String> sourceList, List<String> newColumns) {
		sourceList.remove(column);
		sourceList.addAll(column, newColumns);
	}

	/**
	 * Convenience method that provides the value for the column number
	 * associated with this rule
	 * 
	 * @param the data source
	 * @return the value for the current column of this rule
	 */
	protected String original(List<String> input) {
		return input.get(column);
	}

	protected String toRuleString(String ruleName, String foo) {
		return "foo";
	}

}
