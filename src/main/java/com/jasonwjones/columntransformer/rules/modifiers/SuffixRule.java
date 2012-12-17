package com.jasonwjones.columntransformer.rules.modifiers;

import com.jasonwjones.columntransformer.rules.ColumnModifierRule;

public class SuffixRule extends ColumnModifierRule {

	private String suffix;

	public SuffixRule(Integer column, String suffix) {
		super(column);
		this.suffix = suffix;
	}

	public String applyColumnModifier(String input) {
		return input + suffix;
	}

	@Override
	public String toString() {
		return "Suffix column " + column + " with " + suffix;
	}

}
