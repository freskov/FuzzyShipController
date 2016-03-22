package hr.freskov.fuzzy.control.ship;

import hr.freskov.fuzzy.DomainElement;
import hr.freskov.fuzzy.IDomain;
import hr.freskov.fuzzy.IFuzzySet;

/**
 * Proxy class. Redirects method calls to original fuzzy set which has to be given in the constructor.
 * 
 * @author freskov
 * @version 1.0
 */
public class FuzzySetProxy implements IFuzzySet {
	
	private IFuzzySet original;

	public FuzzySetProxy(IFuzzySet original) {
		super();
		if (original == null) {
			throw new IllegalArgumentException("Argument should not be null.");
		}
		this.original = original;
	}

	@Override
	public IDomain getDomain() {
		return original.getDomain();
	}

	@Override
	public double getMembership(DomainElement element) {
		return original.getMembership(element);
	}

	@Override
	public double getMembership(int index) {
		return original.getMembership(index);
	}

}
