package hr.freskov.fuzzy.control;

import java.util.Map;

import hr.freskov.fuzzy.IFuzzySet;

/**
 * TODO
 * 
 * @author freskov
 * @version 1.0
 */
public class FuzzySystem {
	
	private IRuleBase ruleBase;
	private IDefuzzifier defuzzifier;
	
	/**
	 * TODO
	 * @param ruleBase
	 * @param defuzzifier
	 */
	public FuzzySystem(IRuleBase ruleBase, IDefuzzifier defuzzifier) {
		super();
		this.ruleBase = ruleBase;
		this.defuzzifier = defuzzifier;
	}
	
	/**
	 * TODO
	 * @param input
	 * @return
	 */
	public int getOutput(Map<String, Integer> input) {
		IFuzzySet conclusion = ruleBase.conclusion(input);
		return defuzzifier.defuzzify(conclusion);
	}

}
