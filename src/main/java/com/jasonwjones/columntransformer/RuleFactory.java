package com.jasonwjones.columntransformer;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * Helper class that enables the generation of arbitrary rule classes based on a
 * semantic definition as provided in the main class. For example, a rule is
 * mapped in where by it is expected to receive an integer and string parameter.
 * That description is added to this class and then given a particular Option,
 * this class tries to return an instance of the rule.
 * 
 * @author Jason W. Jones
 * 
 */
public class RuleFactory {

	public static enum ExpectedArguments {
		NONE, COLUMN_ONLY, COLUMN_AND_STRING, COLUMN_AND_INT
	}

	private static class RuleMapping {

		private ExpectedArguments expectedArguments;
		private Class<Rule> classType;

		public RuleMapping(ExpectedArguments expectedArgs, Class<Rule> classType) {
			this.expectedArguments = expectedArgs;
			this.classType = classType;
		}

		public ExpectedArguments getExpectedArguments() {
			return expectedArguments;
		}

		public Class<Rule> getClassType() {
			return classType;
		}

	}

	private Map<String, RuleMapping> ruleMappings = new HashMap<String, RuleMapping>();

	@SuppressWarnings("unchecked")
	public void addMapping(String optionName, ExpectedArguments expectedArgs, Class<?> classType) {
		RuleMapping ruleMapping = new RuleMapping(expectedArgs, (Class<Rule>) classType);
		ruleMappings.put(optionName, ruleMapping);
	}

	public Rule ruleFromOption(Option option) {
		if (ruleMappings.containsKey(option.getOption())) {
			try {
				RuleMapping ruleMapping = ruleMappings.get(option.getOption());

				Class<Rule> ruleClass = ruleMapping.getClassType();
				Constructor<Rule> constructor;

				switch (ruleMapping.getExpectedArguments()) {
					case NONE:
						return ruleClass.newInstance();
					case COLUMN_ONLY:
						constructor = ruleClass.getConstructor(Integer.class);
						return constructor.newInstance(option.col());
					case COLUMN_AND_INT:
						constructor = ruleClass.getConstructor(Integer.class, Integer.class);
						return constructor.newInstance(option.col(), option.intVal());
					case COLUMN_AND_STRING:
						constructor = ruleClass.getConstructor(Integer.class, String.class);
						return constructor.newInstance(option.col(), option.sval());
				}
			} catch (Exception e) {
				System.out.println("Unable to create rule from option: " + e.getMessage());
			}
		}
		return null;
	}
}
