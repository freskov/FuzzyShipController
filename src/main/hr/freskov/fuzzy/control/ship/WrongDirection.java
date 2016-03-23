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
public class WrongDirection extends FuzzySetProxy {
	
	public WrongDirection() {
		super(new CalculatedFuzzySet(
				ShipDomains.getDirectionDomain(),
				StandardFuzzySets.lFunction(0, 1)));
	}

}
