package hr.freskov.fuzzy.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

import hr.freskov.fuzzy.CalculatedFuzzySet;
import hr.freskov.fuzzy.DomainElement;
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
	
	private ArrayList<MamdaniRule> rules;
	
	private MamdaniRuleBase(ArrayList<MamdaniRule> rules) {
		super();
		if (rules == null) {
			throw new IllegalArgumentException("Argument should not be null.");
		}
		this.rules = rules;
	}

	/**
	 * TODO
	 * 
	 * @param input
	 * @return
	 */
	public IFuzzySet conclusion(Map<String, Integer> input) {
		IFuzzySet fs = rules.get(0).conclusion(input);
		for (int i = 1, length = rules.size(); i < length; ++i) {
			fs = Operations.binaryOperation(fs, rules.get(i).conclusion(input), Operations.zadehOr());
		}
		return fs;
	}
	
	/**
	 * TODO
	 * 
	 * @param lines
	 * @return
	 */
	public static MamdaniRuleBase fromString(List<String> lines) {
		ArrayList<MamdaniRule> rules = new ArrayList<>();
		for (String line : lines) {
			rules.add(MamdaniRule.fromString(line));
		}
		return new MamdaniRuleBase(rules);
	}
	
	
	protected static class MamdaniRule {
		
		private ArrayList<Pair<String, IFuzzySet>> antecedent;
		private IFuzzySet consequent;
		
		private MamdaniRule(ArrayList<Pair<String, IFuzzySet>> antecedent, IFuzzySet consequent) {
			super();
			if (antecedent == null || consequent == null) {
				throw new IllegalArgumentException("Argument should not be null.");
			}
			this.antecedent = antecedent;
			this.consequent = consequent;
		}
		
		private double strength(Map<String, Integer> input) {		
			double strength = 1.0;
			for (int i = 0, length = antecedent.size(); i < length; ++i) {
				String name = antecedent.get(i).getKey();
				int inputValue = input.get(name);
				DomainElement element = DomainElement.of(inputValue);
				IFuzzySet fs = antecedent.get(i).getValue();
				strength = Math.min(strength, fs.getMembership(element));
			}
			return strength;
		}
		
		protected IFuzzySet conclusion(Map<String, Integer> input) {
			if (input == null) {
				throw new IllegalArgumentException("Argument should not be null.");
			}
			
			double strength = strength(input);
			IFuzzySet fs = new CalculatedFuzzySet(consequent.getDomain(), index -> strength);
			return Operations.binaryOperation(consequent, fs, Operations.zadehAnd());
		}

		private static final int ANTECEDENT = 0;
		private static final int CONSEQUENT = 1;
		
		protected static MamdaniRule fromString(String string) {
			// IF input_1 = fuzzy_set_1 AND input_2 = fuzzy_set_2 THEN output = fuzzy_set_3
			String tokens[] = string.split(" ");
			assert("IF".equals(tokens[0]));
			
			ArrayList<Pair<String, IFuzzySet>> antecedent = new ArrayList<>();
			IFuzzySet consequent = null;
			int state = ANTECEDENT;
			ClassLoader classLoader = MamdaniRule.class.getClassLoader();
			for (int index = 1; index < tokens.length; ) {
				
				if ("AND".equals(tokens[index])) {
					++index;
					continue;
				}
				
				if ("THEN".equals(tokens[index])) {
					state = CONSEQUENT;
					++index;
					assert(index + 3 == tokens.length);
					continue;
				}
				
				assert("=".equals(tokens[index+1]));
				
				IFuzzySet fuzzySet = null;
				try {
					Class<?> aClass = classLoader.loadClass("hr.freskov.fuzzy.control." + tokens[index+2]);
					fuzzySet = (IFuzzySet) aClass.newInstance();
				} catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
					e.printStackTrace();
					throw new RuntimeErrorException(new Error(e.getMessage()));
				}
				
				if (state == ANTECEDENT) {
					antecedent.add(new Pair<>(tokens[index], fuzzySet));
				} else {
					consequent = fuzzySet;
				}
				
				index += 3;
			}
			
			return new MamdaniRule(antecedent, consequent);
		}		
	}

}
