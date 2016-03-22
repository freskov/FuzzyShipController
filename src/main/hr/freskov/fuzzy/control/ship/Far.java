package hr.freskov.fuzzy.control.ship;

import hr.freskov.fuzzy.CalculatedFuzzySet;
import hr.freskov.fuzzy.StandardFuzzySets;

/**
 * TODO
 * 
 * @author freskov
 * @version 1.0
 */
public class Far extends FuzzySetProxy {
	
	public Far() {
		super(new CalculatedFuzzySet(
				Domains.getDistanceDomain(), 
				StandardFuzzySets.gammaFunction(30, 70)));
	}

}
