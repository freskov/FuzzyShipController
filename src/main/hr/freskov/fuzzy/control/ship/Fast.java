package hr.freskov.fuzzy.control.ship;

import hr.freskov.fuzzy.CalculatedFuzzySet;
import hr.freskov.fuzzy.StandardFuzzySets;

/**
 * TODO
 * 
 * @author freskov
 * @version 1.0
 */
public class Fast extends FuzzySetProxy {
	
	public Fast() {
		super(new CalculatedFuzzySet(
				Domains.getSpeedDomain(),
				StandardFuzzySets.gammaFunction(10, 30)));
	}

}
