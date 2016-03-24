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
public class SharpLeft extends FuzzySetProxy {
	
	public SharpLeft() {
		super(new CalculatedFuzzySet(
				ShipDomains.getHelmDomain(),
				StandardFuzzySets.lFunction(0, 90)));
	}

}
