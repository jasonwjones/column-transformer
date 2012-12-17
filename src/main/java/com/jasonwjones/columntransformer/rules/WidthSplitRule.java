package com.jasonwjones.columntransformer.rules;

import java.util.Arrays;
import java.util.List;

/**
 * Rule that works by splitting the input column based on a certain width and
 * replacing the target with two columns: one with the substring and one with
 * everything else. If the column value length is not greater than the given
 * width then no transformation will occur.
 * 
 * @author Jason W. Jones
 * 
 */
public class WidthSplitRule extends AbstractRule {

	private int width;

	public WidthSplitRule(Integer column, Integer width) {
		super(column);
		this.width = width;
	}

	public void apply(List<String> input) {
		String value = original(input);
		if (value.length() > width) {
			String newValStart = value.substring(0, width);
			String newValEnd = value.substring(width);
			replace(input, Arrays.asList(newValStart, newValEnd));
		}
	}

}
