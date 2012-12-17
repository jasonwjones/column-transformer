package com.jasonwjones.columntransformer;

import java.util.Arrays;

/**
 * A convenience mode that represents the options that are passed in as program
 * arguments upon program invocation. Several methods are provided to extracted
 * the column or the first string value and such things. The point is to load
 * this class up with convenience methods if it makes parsing in other program
 * classes easier and more elegant.
 * 
 * @author Jason W. Jones
 * 
 */
public class Option {

	@SuppressWarnings("serial")
	public static class OptionException extends RuntimeException {

		public OptionException(String message) {
			super(message);
		}

		public OptionException(String message, Throwable throwable) {
			super(message, throwable);
		}
	}

	private String option;
	private int column;
	private String[] value;

	public Option(String optionText) {
		String[] split = optionText.split(",");
		option = split[0];
		column = Integer.valueOf(split[1]);
		if (split.length > 2) {
			value = Arrays.copyOfRange(split, 2, split.length);
		}
	}

	public boolean is(String optionName) {
		return option.equals(optionName);
	}

	public int col() {
		return column;
	}

	public int intVal() {
		try {
			return Integer.valueOf(value[0]);
		} catch (NumberFormatException e) {
			throw new OptionException("Could not get integer", e);
		}
	}

	public String sval() {
		return value[0];
	}

	public String getOption() {
		return option;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Option: key=" + option + ", col=" + column);
		if (value != null) {
			sb.append(", value=" + Arrays.asList(value));
		}
		return sb.toString();
	}

}
