package com.jasonwjones.columntransformer.rules.modifiers;

import com.jasonwjones.columntransformer.rules.ColumnModifierRule;

public class PrefixRule extends ColumnModifierRule {

	private String prefix;

	public PrefixRule(Integer column, String prefix) {
		super(column);
		this.prefix = prefix;
	}

	public String applyColumnModifier(String input) {
		return prefix + input;
	}

	@Override
	public String toString() {
		return "Prefix column " + column + " with " + prefix;
	}

}
