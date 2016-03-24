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
	
	private MamdaniRuleBase ruleBase;
	private IDefuzzifier defuzzifier;
	
	public FuzzySystem(MamdaniRuleBase ruleBase, IDefuzzifier defuzzifier) {
		super();
		this.ruleBase = ruleBase;
		this.defuzzifier = defuzzifier;
	}
	
	public int getOutput(Map<String, Integer> input) {
		IFuzzySet conclusion = ruleBase.conclusion(input);
		return defuzzifier.defuzzify(conclusion);
	}

}
