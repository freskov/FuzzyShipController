package hr.freskov.fuzzy.control;

import hr.freskov.fuzzy.DomainElement;
import hr.freskov.fuzzy.IDomain;
import hr.freskov.fuzzy.IFuzzySet;

/**
 * Center of area defuzzifier. <code>x<sub>COA</sub> = &sum;<sub>x&isin;U</sub>
 * x*&mu;(x) / &sum;<sub>x&isin;U</sub>&mu;(x).
 * 
 * @author freskov
 * @version 1.0
 */
public class COADefuzzifier implements IDefuzzifier {

	@Override
	public int defuzzify(IFuzzySet fuzzySet) {
		if (fuzzySet == null) {
			throw new IllegalArgumentException("Argument should not be null.");
		}
		double numerator = 0;
		double denominator = 0;

		IDomain domain = fuzzySet.getDomain();
		for (DomainElement element : domain) {
			double membership = fuzzySet.getMembership(element);
			numerator += membership * element.getComponent(0);
			denominator += membership;
		}

		return (int) Math.round(numerator / denominator);
	}

}
