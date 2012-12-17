package com.jasonwjones.columntransformer.rules;

import java.util.List;

public class JoinerRule extends AbstractRule {

	private int endColumn;

	public JoinerRule(Integer column, Integer endColumn) {
		super(column);
		this.endColumn = endColumn;
	}

	public void apply(List<String> input) {
		String newValue = joined(input.subList(column, endColumn + 1));
		for (int index = column; index <= endColumn; index++) {
			input.remove(column);
		}
		input.add(column, newValue);
	}

	private String joined(List<String> input) {
		StringBuilder sb = new StringBuilder();
		for (String element : input) {
			sb.append(element);
		}
		return sb.toString();
	}
}
