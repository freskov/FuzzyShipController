package hr.freskov.fuzzy.control.ship;

import hr.freskov.fuzzy.CalculatedFuzzySet;
import hr.freskov.fuzzy.StandardFuzzySets;

public class WrongDirection extends FuzzySetProxy {
	
	public WrongDirection() {
		super(new CalculatedFuzzySet(
				Domains.getDirectionDomain(),
				StandardFuzzySets.lFunction(0, 1)));
	}

}
