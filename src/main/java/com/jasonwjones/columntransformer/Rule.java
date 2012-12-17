package com.jasonwjones.columntransformer;

import java.util.List;

/**
 * A rule is very simple. It takes a List of String elements and then changes
 * the list in place according to its processing. For example, a rule might
 * remove a single column, in which case, the list object would be smaller after
 * the method in the rule is called.
 * 
 * @author Jason W. Jones
 * 
 */
public interface Rule {

	/**
	 * As stated above, the rule is given a List of String where the elements of
	 * the list correspond to the current columns as presently understood at the
	 * time of processing.
	 * 
	 * @param input a List of String elements representing the columns in their
	 *            current state for the current row
	 */
	void apply(List<String> input);

}
