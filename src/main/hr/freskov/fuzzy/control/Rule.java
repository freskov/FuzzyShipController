package hr.freskov.fuzzy.control;

import java.util.ArrayList;
import java.util.Map;

import hr.freskov.fuzzy.IFuzzySet;
import javafx.util.Pair;

/**
 * TODO
 * @author freskov
 * @version 1.0
 */
public class Rule {
	
	protected ArrayList<Pair<String, IFuzzySet>> antecedent;
	protected IFuzzySet consequent;
	private String rule;
	
	private Rule(String rule) {
		super();
		this.rule = rule;
	}
	
	/**
	 * TODO
	 * @param input
	 * @param conclusion
	 * @return
	 */
	public IFuzzySet conclusion(Map<String, Integer> input, IConclusionStrategy conclusion) {
		if (input == null) {
			throw new IllegalArgumentException("Argument should not be null.");
		}
		
		return conclusion.conclude(input, this);
	}
	
	private static final int ANTECEDENT = 0;
	private static final int CONSEQUENT = 1;
	
	/**
	 * TODO
	 * @param string
	 * @return
	 */
	public static Rule fromString(String string) {
		// IF input_1 IS fuzzy_set_1 AND input_2 IS fuzzy_set_2 THEN output IS fuzzy_set_3
		String tokens[] = string.split(" ");
		assert("IF".equals(tokens[0]));
		
		Rule rule = new Rule(string);
		rule.antecedent = new ArrayList<>();
		int state = ANTECEDENT;
		ClassLoader classLoader = Rule.class.getClassLoader();
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
			
			assert("IS".equals(tokens[index+1]));
			
			IFuzzySet fuzzySet = null;
			try {
				Class<?> aClass = classLoader.loadClass(tokens[index+2]);
				fuzzySet = (IFuzzySet) aClass.newInstance();
			} catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			
			if (state == ANTECEDENT) {
				rule.antecedent.add(new Pair<>(tokens[index], fuzzySet));
			} else {
				rule.consequent = fuzzySet;
			}
			
			index += 3;
		}
		
		rule.rule = string;
		return rule;
	}		
	
	@Override
	public String toString() {
		return rule;
	}
}
