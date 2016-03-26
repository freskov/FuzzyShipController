package hr.freskov.fuzzy.control.ship;

import hr.freskov.fuzzy.CalculatedFuzzySet;
import hr.freskov.fuzzy.FuzzySetProxy;
import hr.freskov.fuzzy.StandardFuzzySets;

/**
 * TODO
 * 
 * @author freskov
 * @version 1.0
 */
public class Far extends FuzzySetProxy {
	
	public Far() {
		super(new CalculatedFuzzySet(
				ShipDomains.getDistanceDomain(), 
				StandardFuzzySets.gammaFunction(20, 100)));
	}

}
