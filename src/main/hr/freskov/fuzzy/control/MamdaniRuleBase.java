package hr.freskov.fuzzy.control;

import java.util.Map;

import hr.freskov.fuzzy.CalculatedFuzzySet;
import hr.freskov.fuzzy.DomainElement;
import hr.freskov.fuzzy.IDomain;
import hr.freskov.fuzzy.IFuzzySet;
import hr.freskov.fuzzy.Operations;
import javafx.util.Pair;

/**
 * TODO
 * 
 * @author freskov
 * @version 1.0
 */
public class MamdaniRuleBase {
	
	private MamdaniRule[] rules;
	private IDomain consequentDomain;
	
	private MamdaniRuleBase(MamdaniRule[] rules, IDomain consequentDomain) {
		super();
		if (rules == null || consequentDomain == null) {
			throw new IllegalArgumentException("Argument should not be null.");
		}
		this.rules = rules;
		this.consequentDomain = consequentDomain;
	}

	public IFuzzySet conclusion(Map<String, Integer> input) {
		IFuzzySet fs = new CalculatedFuzzySet(consequentDomain, index -> 0.0);
		for (MamdaniRule rule : rules) {
			fs = Operations.binaryOperation(fs, rule.conclusion(input), Operations.zadehOr());
		}
		return fs;
	}
	
	public static MamdaniRuleBase fromFile(String file) {
		// TODO
		return new MamdaniRuleBase(null, null);
	}
	
	
	private static class MamdaniRule {
		
		private Pair<String, IFuzzySet>[] antecedent;
		private IFuzzySet consequent;
		
		private MamdaniRule(Pair<String, IFuzzySet>[] antecedent, IFuzzySet consequent) {
			super();
			if (antecedent == null || consequent == null) {
				throw new IllegalArgumentException("Argument should not be null.");
			}
			this.antecedent = antecedent;
			this.consequent = consequent;
		}
		
		public double strength(Map<String, Integer> input) {		
			double strength = 1.0;
			for (int i = 0; i < antecedent.length; ++i) {
				String name = antecedent[i].getKey();
				IFuzzySet fs = antecedent[i].getValue();
				strength = Math.min(strength, fs.getMembership(DomainElement.of(input.get(name))));
			}
			return strength;
		}
		
		public IFuzzySet conclusion(Map<String, Integer> input) {
			if (input == null) {
				throw new IllegalArgumentException("Argument should not be null.");
			}
			
			double strength = strength(input);
			IFuzzySet fs = new CalculatedFuzzySet(consequent.getDomain(), index -> strength);
			return Operations.binaryOperation(consequent, fs, Operations.zadehAnd());
		}

		public static MamdaniRule fromString(String string) {
			// TODO
			return new MamdaniRule(null, null);
		}		
	}

}
