package com.jasonwjones.columntransformer.rules.modifiers;

import com.jasonwjones.columntransformer.rules.ColumnModifierRule;

public class SignFlipRule extends ColumnModifierRule {

	protected SignFlipRule(Integer column) {
		super(column);
	}

	@Override
	public String applyColumnModifier(String input) {
		return String.valueOf((Integer.valueOf(input) * -1));
	}

	@Override
	public String toString() {
		return "Sign flip on column " + column;
	}

}
