package com.jasonwjones.columntransformer;

import java.util.ArrayList;
import java.util.List;

import com.jasonwjones.columntransformer.RuleFactory.ExpectedArguments;
import com.jasonwjones.columntransformer.rules.ColumnRemoverRule;
import com.jasonwjones.columntransformer.rules.DelimiterSplitRule;
import com.jasonwjones.columntransformer.rules.modifiers.PrefixRule;

/**
 * Main class for program that reads stdin and program arguments, then outputs
 * to stdout after the transformations are applied row by row.
 * 
 * @author Jason W. Jones
 * 
 */
public class ColumnTransformer {

	private static RuleFactory ruleFactory = createRuleFactory();

	/**
	 * Pretty straightforward main section that sets up shop and processed the
	 * input
	 * 
	 * @param args program arguments
	 */
	public static void main(String[] args) {

		List<Option> options = parseOptions(args);
		for (Option option : options) {
			System.err.println("Parsed option: " + option);
		}

		List<Rule> rules = new ArrayList<Rule>();
		for (Option option : options) {
			Rule rule = ruleFactory.ruleFromOption(option);
			if (rule != null) {
				rules.add(rule);
			} else {
				System.err.println("Unable to make rule for option: " + option);
			}
		}

		for (Rule rule : rules) {
			System.err.println("Rule: " + rule);
		}

		System.err.println("Transforming stdin...");

		Transformer transformer = new Transformer(rules);
		transformer.transform(System.in, System.out);
	}

	/**
	 * Quick and dirty parser that considers anything that starts with a dash to
	 * be a possible option.
	 * 
	 * @param args array of program arguments
	 * @return a List of Option objects parsed from the array
	 */
	private static List<Option> parseOptions(String[] args) {
		List<Option> options = new ArrayList<Option>();
		for (String arg : args) {
			if (arg.startsWith("-")) {
				Option option = new Option(arg.substring(1));
				options.add(option);
			} else {
				System.err.println("Not an option: " + arg);
			}
		}
		return options;
	}

	/**
	 * Creates the map of recognizable options when we go to parse
	 * 
	 * @return a rule factory that can generate Rules based on Options that are
	 *         passed in
	 */
	private static RuleFactory createRuleFactory() {
		RuleFactory ruleFactory = new RuleFactory();
		ruleFactory.addMapping("X", ExpectedArguments.COLUMN_ONLY, ColumnRemoverRule.class);
		ruleFactory.addMapping("P", ExpectedArguments.COLUMN_AND_STRING, PrefixRule.class);
		ruleFactory.addMapping("S", ExpectedArguments.COLUMN_ONLY, DelimiterSplitRule.class);
		return ruleFactory;
	}

}
