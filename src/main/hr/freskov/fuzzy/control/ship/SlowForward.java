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
public class SlowForward extends FuzzySetProxy {
	
	public SlowForward() {
		super(new CalculatedFuzzySet(
				ShipDomains.getSpeedDomain(),
				StandardFuzzySets.piFunction(100, 100, 120, 150)));
	}

}
