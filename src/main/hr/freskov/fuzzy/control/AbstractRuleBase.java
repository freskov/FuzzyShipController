package hr.freskov.fuzzy.control;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 * @author freskov
 * @version 1.0
 */
public abstract class AbstractRuleBase implements IRuleBase {
	
	protected ArrayList<Rule> rules;

	public AbstractRuleBase(List<String> lines) {
		rules = new ArrayList<>();
		for (String line : lines) {
			if (line.isEmpty() || line.startsWith("#")) continue;
			rules.add(Rule.fromString(line));
		}
	}

}
