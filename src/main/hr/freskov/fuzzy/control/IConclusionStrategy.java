package hr.freskov.fuzzy.control;

import java.util.Map;

import hr.freskov.fuzzy.IFuzzySet;

/**
 * TODO
 * @author freskov
 * @version 1.0
 */
public interface IConclusionStrategy {

	/**
	 * TODO
	 * @param input
	 * @param rule
	 * @return
	 */
	IFuzzySet conclude(Map<String, Integer> input, Rule rule);

}
