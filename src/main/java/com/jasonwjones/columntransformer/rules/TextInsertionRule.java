package com.jasonwjones.columntransformer.rules;

import java.util.List;

public class TextInsertionRule extends AbstractRule {

	private String insertionText;

	public TextInsertionRule(Integer column, String insertionText) {
		super(column);
		this.insertionText = insertionText;
	}

	public void apply(List<String> input) {
		input.add(column, insertionText);
	}

}
