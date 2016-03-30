package hr.freskov.fuzzy.control;

import java.util.List;
import java.util.Map;

import hr.freskov.fuzzy.CalculatedFuzzySet;
import hr.freskov.fuzzy.DomainElement;
import hr.freskov.fuzzy.IFuzzySet;
import hr.freskov.fuzzy.Operations;
import javafx.util.Pair;

/**
 * Defines a rule base that uses Mamdani conclusion.
 * 
 * @author freskov
 * @version 1.1
 */
public class MamdaniRuleBase extends AbstractRuleBase {

	private static final IConclusionStrategy CONCLUSION_STRATEGY = (input, rule) -> {
		double strength = 1.0;
		for (Pair<String, IFuzzySet> variable : rule.antecedent) {
			String name = variable.getKey();
			int inputValue = input.get(name);
			DomainElement element = DomainElement.of(inputValue);
			IFuzzySet fs = variable.getValue();
			strength = Math.min(strength, fs.getMembership(element));
		}

		final double value = strength;
		IFuzzySet fs = new CalculatedFuzzySet(rule.consequent.getDomain(), index -> value);
		return Operations.binaryOperation(rule.consequent, fs, Operations.zadehAnd());
	};

	/**
	 * Initializes rule base.
	 * 
	 * @param rules
	 *            list of strings containing rules
	 */
	public MamdaniRuleBase(List<String> rules) {
		super(rules);
	}

	@Override
	public IFuzzySet conclude(Map<String, Integer> input) {
		if (input == null) {
			throw new IllegalArgumentException("Argument should not be null.");
		}

		IFuzzySet fs = CONCLUSION_STRATEGY.conclude(input, rules.get(0));
		for (int i = 1, length = rules.size(); i < length; ++i) {
			fs = Operations.binaryOperation(fs, CONCLUSION_STRATEGY.conclude(input, rules.get(i)),
					Operations.zadehOr());
		}

		return fs;
	}

}
