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
public class SlowReverse extends FuzzySetProxy {
	
	public SlowReverse() {
		super(new CalculatedFuzzySet(
				ShipDomains.getSpeedDomain(),
				StandardFuzzySets.piFunction(50, 80, 100, 100)));
	}

}
