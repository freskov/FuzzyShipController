package hr.freskov.fuzzy.control;

import java.util.Map;

import hr.freskov.fuzzy.IFuzzySet;

/**
 * Defines a fuzzy rule conclusion strategy. Conclusion can be calculated via
 * {@link #conclude(Map, hr.freskov.fuzzy.control.AbstractRuleBase.Rule)}
 * method.
 * 
 * @author freskov
 * @version 1.0
 */
public interface IConclusionStrategy {

	/**
	 * Calculates a fuzzy set representing conclusion of the rule for the
	 * specified input.
	 * 
	 * @param input
	 *            variable name -> value mapping
	 * @param rule
	 *            fuzzy rule
	 * @return conclusion of the rule for the specified input.
	 */
	IFuzzySet conclude(Map<String, Integer> input, AbstractRuleBase.Rule rule);

}
