package hr.freskov.fuzzy.control;

import java.util.Map;

import hr.freskov.fuzzy.IFuzzySet;

/**
 * Defines a MISO fuzzy control system. System is defined by a rule base and a
 * defuzzifier.
 * 
 * @author freskov
 * @version 1.0
 */
public class FuzzySystem {

	private IRuleBase ruleBase;
	private IDefuzzifier defuzzifier;

	/**
	 * Initializes fuzzy control system.
	 * 
	 * @param ruleBase
	 *            rule base
	 * @param defuzzifier
	 *            defuzzifier
	 */
	public FuzzySystem(IRuleBase ruleBase, IDefuzzifier defuzzifier) {
		super();
		this.ruleBase = ruleBase;
		this.defuzzifier = defuzzifier;
	}

	/**
	 * Calculates output of the system for the specified input following rules
	 * from the rule base.
	 * 
	 * @param input
	 *            variable name -> value mapping
	 * @return output of the system for the specified input.
	 */
	public int getOutput(Map<String, Integer> input) {
		IFuzzySet conclusion = ruleBase.conclude(input);
		return defuzzifier.defuzzify(conclusion);
	}

}
