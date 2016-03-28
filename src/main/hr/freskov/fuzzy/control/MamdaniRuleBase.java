package hr.freskov.fuzzy.control;

import java.util.List;
import java.util.Map;

import hr.freskov.fuzzy.IFuzzySet;
import hr.freskov.fuzzy.Operations;

/**
 * TODO
 * 
 * @author freskov
 * @version 1.0
 */
public class MamdaniRuleBase extends AbstractRuleBase {
	
	private static final IConclusionStrategy CONCLUSION_STRATEGY = new MamdaniConclusionStrategy();
	
	/**
	 * TODO
	 * @param rules
	 */
	public MamdaniRuleBase(List<String> rules) {
		super(rules);
	}

	@Override
	public IFuzzySet conclusion(Map<String, Integer> input) {
		if (input == null) {
			throw new IllegalArgumentException("Argument should not be null.");
		}
		
		IFuzzySet fs = rules.get(0).conclusion(input, CONCLUSION_STRATEGY);
		for (int i = 1, length = rules.size(); i < length; ++i) {
			fs = Operations.binaryOperation(fs, rules.get(i).conclusion(input, CONCLUSION_STRATEGY), Operations.zadehOr());
		}
		
		return fs;
	}	

}
