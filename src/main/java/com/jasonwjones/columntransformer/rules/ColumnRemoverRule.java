package com.jasonwjones.columntransformer.rules;

import java.util.List;

import com.jasonwjones.columntransformer.Rule;

public class ColumnRemoverRule extends AbstractRule implements Rule {

	public ColumnRemoverRule(Integer column) {
		super(column);
	}

	public void apply(List<String> input) {
		input.remove(column);
	}

	@Override
	public String toString() {
		return "Remove column " + column;
	}

}
