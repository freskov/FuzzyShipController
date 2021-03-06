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
public class SlightlyRight extends FuzzySetProxy {
	
	public SlightlyRight() {
		super(new CalculatedFuzzySet(
				ShipDomains.getHelmDomain(),
				StandardFuzzySets.lambdaFunction(45, 90, 90)));
	}

}
