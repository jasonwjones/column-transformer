package com.jasonwjones.columntransformer.rules;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class for setting som etest data for testing rules and whatnot.
 * 
 * @author Jason W. Jones
 * 
 */
public class AbstractRuleTest {

	protected List<String> simpleDeptRow;

	private static String simpleDeptRowRaw = "2010,01,001";

	public AbstractRuleTest() {
		simpleDeptRow = delimitedStringToList(simpleDeptRowRaw);
	}

	/**
	 * Helper method turns a comma-delimited String into a list. Probably a
	 * cleaner way to do this but it gets the job done and using Arrays.asList
	 * would give an immutable list which definitely will not work.
	 * 
	 * @param input the source string delimited by commas
	 * @return a List of String (mutable: backed by an ArrayList)
	 */
	private static List<String> delimitedStringToList(String input) {
		List<String> result = new ArrayList<String>();
		for (String current : input.split(",")) {
			result.add(current);
		}
		return result;
	}

}
