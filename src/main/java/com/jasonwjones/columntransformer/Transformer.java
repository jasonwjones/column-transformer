package com.jasonwjones.columntransformer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Performs the actual transformation of input according to the set of rules
 * that are associated with this transformer.
 * 
 * @author Jason W. Jones
 * 
 */
public class Transformer {

	/**
	 * List of rules that the transformer will iterate through in order to
	 * process transformations. While a public accessor is not provided for
	 * getting or setting this list (must be set in the constructor), it is not
	 * a consistency violation to modify the list on your own after the
	 * transformer gets this. Just frowned upon.
	 */
	private List<Rule> rules;

	/**
	 * Convenience constructor that in general just aids in unit tests.
	 * Generally this class will be instantiated with an entire list of rules
	 * (as seen in the next constructor)
	 * 
	 * @param rule a single Rule to use as the basis of transformations
	 */
	public Transformer(Rule rule) {
		rules = new ArrayList<Rule>();
		rules.add(rule);
	}

	/**
	 * Preferred and most common constructor that takes an entire set of Rules
	 * that will be used to process a given line of text passed in or from the
	 * passed in InputStream.
	 * 
	 * @param rules list of rules to apply to the text to process. Note that
	 *            order is, quite naturally, very important and thus a list is
	 *            used rather than a generic collection or even a set.
	 */
	public Transformer(List<Rule> rules) {
		this.rules = rules;
	}

	/**
	 * Transform (given the set of Rules associated with this Transformer) from
	 * the given input stream and output to the given output stream. Can be used
	 * with, for example, System.in and System.out.
	 * 
	 * @param inputStream input stream to read from
	 * @param outputStream output stream to write results to
	 */
	public void transform(InputStream inputStream, OutputStream outputStream) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			PrintStream ps = new PrintStream(System.out);

			String line;
			while ((line = br.readLine()) != null) {
				ps.println(transform(line));
			}
		} catch (IOException e) {
			throw new RuntimeException("Error generating output.", e);
		}
	}

	/**
	 * Transforms a single line of text (exactly the same as if had been
	 * processed from the InputStream variation of this method).
	 * 
	 * @param input the line of input (like from a file) to process
	 * @return the processed results from the input string
	 */
	public String transform(String input) {
		List<String> items = new ArrayList<String>();
		items.add(input);
		transform(items);
		return collectionToDelimitedString(items, ",");
	}

	/**
	 * Modifies the given list in place
	 * 
	 * @param input the list of elements to modify (can be only one, which will
	 *            be split)
	 */
	public void transform(List<String> input) {
		System.out.println("Original input: " + input);
		for (Rule rule : rules) {
			System.out.println("Applying rule: " + rule);
			rule.apply(input);
			System.out.println("   --> " + input);
		}
	}

	/**
	 * Convenience method to return a Collection as a delimited (e.g. CSV)
	 * String. E.g. useful for <code>toString()</code> implementations.
	 * 
	 * @param coll the Collection to display
	 * @param delim the delimiter to use (probably a ",")
	 * @param prefix the String to start each element with
	 * @param suffix the String to end each element with
	 * @return the delimited String
	 */
	public static String collectionToDelimitedString(Collection<?> coll, String delim,
			String prefix, String suffix) {
		if (coll.size() == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		Iterator<?> it = coll.iterator();
		while (it.hasNext()) {
			sb.append(prefix).append(it.next()).append(suffix);
			if (it.hasNext()) {
				sb.append(delim);
			}
		}
		return sb.toString();
	}

	/**
	 * Convenience method to return a Collection as a delimited (e.g. CSV)
	 * String. E.g. useful for <code>toString()</code> implementations.
	 * 
	 * @param coll the Collection to display
	 * @param delim the delimiter to use (probably a ",")
	 * @return the delimited String
	 */
	public static String collectionToDelimitedString(Collection<?> coll, String delim) {
		return collectionToDelimitedString(coll, delim, "", "");
	}
}
