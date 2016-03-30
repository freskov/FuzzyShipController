package hr.freskov.fuzzy.control;

import java.util.ArrayList;
import java.util.List;

import hr.freskov.fuzzy.IFuzzySet;
import javafx.util.Pair;

/**
 * Implements a super constructor for rule bases implementing {@link IRuleBase}
 * interface and defines a fuzzy IF-THEN rule.
 * 
 * @author freskov
 * @version 1.0
 */
public abstract class AbstractRuleBase implements IRuleBase {

	protected ArrayList<Rule> rules;

	/**
	 * Initializes rule base.
	 * 
	 * @param lines
	 *            list of strings containing rules
	 */
	public AbstractRuleBase(List<String> lines) {
		if (lines == null) {
			throw new IllegalArgumentException("Argument should not be null.");
		}
		
		rules = new ArrayList<>();
		for (String line : lines) {
			if (line.isEmpty() || line.startsWith("#")) {
				continue;
			}
			rules.add(new Rule(line));
		}
	}

	/**
	 * Implements a fuzzy IF-THEN rule. Antecedent of the rule is a list of
	 * (variable, fuzzy value) pairs connected by a logical operator AND.
	 * Consequent of the rule is a fuzzy value of a variable to which the rule
	 * applies.
	 * 
	 * @author freskov
	 * @version 1.0
	 */
	public static class Rule {

		protected ArrayList<Pair<String, IFuzzySet>> antecedent;
		protected IFuzzySet consequent;
		private String ruleString;

		private Rule(String ruleString) {
			super();
			if (ruleString == null) {
				throw new IllegalArgumentException("Argument should not be null.");
			}
			
			initRule(ruleString);
		}

		private static final int ANTECEDENT = 0;
		private static final int CONSEQUENT = 1;

		/**
		 * Returns a newly created fuzzy IF-THEN rule object from string.
		 * 
		 * @param string
		 *            string representing a fuzzy IF-THEN rule.
		 * @return new fuzzy IF-THEN rule object.
		 */
		private void initRule(String string) {
			this.ruleString = string;
			this.antecedent = new ArrayList<>();

			String tokens[] = string.split(" ");
			assert ("IF".equals(tokens[0]));

			int state = ANTECEDENT;
			ClassLoader classLoader = Rule.class.getClassLoader();
			for (int index = 1; index < tokens.length;) {

				if ("AND".equals(tokens[index])) {
					++index;
					continue;
				}

				if ("THEN".equals(tokens[index])) {
					state = CONSEQUENT;
					++index;
					assert (index + 3 == tokens.length);
					continue;
				}

				assert ("IS".equals(tokens[index + 1]));

				IFuzzySet fuzzySet = null;
				try {
					Class<?> aClass = classLoader.loadClass(tokens[index + 2]);
					fuzzySet = (IFuzzySet) aClass.newInstance();
				} catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}

				if (state == ANTECEDENT) {
					this.antecedent.add(new Pair<>(tokens[index], fuzzySet));
				} else {
					this.consequent = fuzzySet;
				}

				index += 3;
			}

		}

		@Override
		public String toString() {
			return ruleString;
		}

	}

}