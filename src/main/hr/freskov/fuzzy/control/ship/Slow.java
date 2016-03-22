package hr.freskov.fuzzy.control.ship;

import hr.freskov.fuzzy.CalculatedFuzzySet;
import hr.freskov.fuzzy.StandardFuzzySets;

public class Slow extends FuzzySetProxy {
	
	public Slow() {
		super(new CalculatedFuzzySet(
				Domains.getSpeedDomain(),
				StandardFuzzySets.lFunction(10, 30)));
	}

}
