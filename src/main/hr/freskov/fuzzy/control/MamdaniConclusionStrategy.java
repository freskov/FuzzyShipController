package hr.freskov.fuzzy.control;

import java.util.Map;

import hr.freskov.fuzzy.CalculatedFuzzySet;
import hr.freskov.fuzzy.DomainElement;
import hr.freskov.fuzzy.IFuzzySet;
import hr.freskov.fuzzy.Operations;
import javafx.util.Pair;

/**
 * TODO
 * @author freskov
 * @version 1.0
 */
public class MamdaniConclusionStrategy implements IConclusionStrategy {

	@Override
	public IFuzzySet conclude(Map<String, Integer> input, Rule rule) {
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
	}

}
