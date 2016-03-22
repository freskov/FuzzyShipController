package hr.freskov.fuzzy.control.ship;

import hr.freskov.fuzzy.CalculatedFuzzySet;
import hr.freskov.fuzzy.StandardFuzzySets;

public class RightDirection extends FuzzySetProxy {
	
	public RightDirection() {
		super(new CalculatedFuzzySet(
				Domains.getDirectionDomain(),
				StandardFuzzySets.gammaFunction(0, 1)));
	}

}
