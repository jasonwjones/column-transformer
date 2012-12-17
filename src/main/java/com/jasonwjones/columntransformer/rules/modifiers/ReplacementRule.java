package com.jasonwjones.columntransformer.rules.modifiers;

import java.util.List;

import com.jasonwjones.columntransformer.rules.ColumnModifierRule;

public class ReplacementRule extends ColumnModifierRule {

	public class Replacement {

		private String searchText;
		private String replaceText;
		private boolean replaceAll;
		private boolean matchWholeWord;
		private boolean caseInsensitive;

		public Replacement(String searchText, String replaceText, boolean replaceAll,
				boolean caseInsensitive) {
			this.searchText = searchText;
			this.replaceText = replaceText;
			this.replaceAll = replaceAll;
			this.caseInsensitive = caseInsensitive;
		}

		public String getSearchText() {
			return searchText;
		}

		public String getReplaceText() {
			return replaceText;
		}

		public boolean isReplaceAll() {
			return replaceAll;
		}

		public boolean isCaseInsensitive() {
			return caseInsensitive;
		}

		public boolean isMatchWholeWord() {
			return matchWholeWord;
		}

		public String apply(String original) {
			return original;
		}

	}

	private List<Replacement> replacements;

	public ReplacementRule(Integer column, List<Replacement> replacements) {
		super(column);
		this.replacements = replacements;
	}

	@Override
	public String applyColumnModifier(String input) {
		for (Replacement replacement : replacements) {
			input = replacement.apply(input);
		}
		return input;
	}

}
