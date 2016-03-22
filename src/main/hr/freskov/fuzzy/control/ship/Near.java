package hr.freskov.fuzzy.control.ship;

import hr.freskov.fuzzy.CalculatedFuzzySet;
import hr.freskov.fuzzy.StandardFuzzySets;

public class Near extends FuzzySetProxy {
	
	public Near() {
		super(new CalculatedFuzzySet(
				Domains.getDistanceDomain(),
				StandardFuzzySets.lFunction(30, 70)));
	}

}
