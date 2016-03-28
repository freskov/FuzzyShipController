package hr.freskov.fuzzy.control;

import java.util.Map;

import hr.freskov.fuzzy.IFuzzySet;

/**
 * TODO
 * 
 * @author freskov
 * @version 1.0
 */
public interface IRuleBase {

	/**
	 * TODO
	 * @param input
	 * @return
	 */
	IFuzzySet conclusion(Map<String, Integer> input);

}
