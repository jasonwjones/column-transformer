package com.jasonwjones.columntransformer.rules;

import java.util.List;

import com.jasonwjones.columntransformer.Rule;

/**
 * Convenience class where the result of the rule is that a single original
 * value in the original list is replaced with a new value.
 * 
 * @author Jason W. Jones
 * 
 */
public abstract class ColumnModifierRule extends AbstractRule implements Rule {

	protected ColumnModifierRule(Integer column) {
		super(column);
	}

	public void apply(List<String> input) {
		try {
			replace(input, applyColumnModifier(original(input)));
		} catch (IndexOutOfBoundsException e) {
			System.err.println("Index out of bounds exception: " + e.getMessage());
		}
	}

	public abstract String applyColumnModifier(String input);

}
