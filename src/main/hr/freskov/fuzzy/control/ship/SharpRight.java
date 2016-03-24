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
public class SharpRight extends FuzzySetProxy {
	
	public SharpRight() {
		super(new CalculatedFuzzySet(
				ShipDomains.getHelmDomain(),
				StandardFuzzySets.gammaFunction(90, 180)));
	}

}
