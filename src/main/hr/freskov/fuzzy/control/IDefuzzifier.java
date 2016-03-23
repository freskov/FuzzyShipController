package hr.freskov.fuzzy.control;

import hr.freskov.fuzzy.IFuzzySet;

/**
 * Interface specifies a defuzzifier. Defuzzifier takes a fuzzy set that is
 * result of inference and determines a crisp value that represents this fuzzy
 * set.
 * 
 * @author freskov
 * @version 1.0
 */
public interface IDefuzzifier {

	/**
	 * Returns a crisp value that represents specified fuzzy set.
	 * 
	 * @param fuzzySet
	 *            fuzzy set
	 * @return crisp value that represents specified fuzzy set.
	 */
	int defuzzify(IFuzzySet fuzzySet);

}
