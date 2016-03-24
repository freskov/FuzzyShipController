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
public class SlightlyLeft extends FuzzySetProxy {
	
	public SlightlyLeft() {
		super(new CalculatedFuzzySet(
				ShipDomains.getHelmDomain(),
				StandardFuzzySets.lambdaFunction(90, 90, 180)));
	}

}
