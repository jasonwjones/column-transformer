package com.jasonwjones.columntransformer.rules;

import java.util.Arrays;
import java.util.List;

import com.jasonwjones.columntransformer.Rule;

public class DelimiterSplitRule extends AbstractRule implements Rule {

	private String delimiter;

	public DelimiterSplitRule(Integer column) {
		this(column, ",");
	}

	public DelimiterSplitRule(Integer column, String delimiter) {
		super(column);
		this.delimiter = delimiter;
	}

	public void apply(List<String> input) {
		String inputText = input.remove(column);
		List<String> resultColumns = Arrays.asList(inputText.split(delimiter));
		input.addAll(column, resultColumns);
	}

	@Override
	public String toString() {
		String format = "Split rule: column=%d, delimiter=%s";
		return String.format(format, column, delimiter);
	}
}
