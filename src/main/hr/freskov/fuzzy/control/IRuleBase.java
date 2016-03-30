package hr.freskov.fuzzy.control;

import java.util.Map;

import hr.freskov.fuzzy.IFuzzySet;

/**
 * Defines a base of fuzzy IF-THEN rules. Offers a method {@link #conclude(Map)}
 * which calculates the output specified by the rules.
 * 
 * @author freskov
 * @version 1.0
 */
public interface IRuleBase {

	/**
	 * Returns a fuzzy set that is a result of conclusion of all rules.
	 * 
	 * @param input
	 *            variable name -> value mapping
	 * @return fuzzy set that is a result of conclusion of all rules.
	 */
	IFuzzySet conclude(Map<String, Integer> input);

}
