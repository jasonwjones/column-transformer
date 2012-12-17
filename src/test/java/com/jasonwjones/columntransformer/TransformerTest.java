package com.jasonwjones.columntransformer;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.jasonwjones.columntransformer.Rule;
import com.jasonwjones.columntransformer.Transformer;
import com.jasonwjones.columntransformer.rules.ColumnRemoverRule;
import com.jasonwjones.columntransformer.rules.DelimiterSplitRule;
import com.jasonwjones.columntransformer.rules.TextInsertionRule;
import com.jasonwjones.columntransformer.rules.modifiers.PrefixRule;
import com.jasonwjones.columntransformer.rules.modifiers.SuffixRule;

public class TransformerTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	@Ignore
	public void testTransformString() {
		List<Rule> rules = new ArrayList<Rule>();
		rules.add(new DelimiterSplitRule(0, ","));
		rules.add(new PrefixRule(0, "Dt."));
		rules.add(new SuffixRule(1, "_SCEN"));
		rules.add(new TextInsertionRule(2, "Sales"));
		rules.add(new ColumnRemoverRule(0));

		Transformer transformer = new Transformer(rules);

		transformer.transform("Jan,Actual");

		fail("Not yet implemented");
	}

}
