package com.jasonwjones.columntransformer.rules;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.jasonwjones.columntransformer.Transformer;

public class WidthSplitRuleTest extends AbstractRuleTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testApply() {
		WidthSplitRule wsr = new WidthSplitRule(0, 2);
		Transformer transformer = new Transformer(wsr);
		transformer.transform(simpleDeptRow);
		assertEquals(4, simpleDeptRow.size());
	}
}
