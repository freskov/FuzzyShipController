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
public class FastForward extends FuzzySetProxy {
	
	public FastForward() {
		super(new CalculatedFuzzySet(
				ShipDomains.getSpeedDomain(),
				StandardFuzzySets.gammaFunction(120, 150)));
	}

}
