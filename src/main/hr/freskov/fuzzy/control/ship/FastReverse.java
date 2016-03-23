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
public class FastReverse extends FuzzySetProxy {
	
	public FastReverse() {
		super(new CalculatedFuzzySet(
				ShipDomains.getSpeedDomain(),
				StandardFuzzySets.lFunction(50, 80)));
	}

}
