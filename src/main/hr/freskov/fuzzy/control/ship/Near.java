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
public class Near extends FuzzySetProxy {
	
	public Near() {
		super(new CalculatedFuzzySet(
				ShipDomains.getDistanceDomain(),
				StandardFuzzySets.lFunction(20, 100)));
	}

}
