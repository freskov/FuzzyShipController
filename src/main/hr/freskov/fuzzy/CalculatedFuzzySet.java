package hr.freskov.fuzzy;

/**
 * Fuzzy set implementation with defined membership function. Calculates element
 * membership on demand.
 * 
 * @author freskov
 * @version 1.0
 */
public class CalculatedFuzzySet implements IFuzzySet {

	private IDomain domain;
	private IIntUnaryFunction membershipFunction;

	/**
	 * Initializes calculated fuzzy set with specified domain and membership
	 * function.
	 * 
	 * @param domain
	 *            domain
	 * @param membershipFunction
	 *            membership function
	 * @throws IllegalArgumentException
	 *             if arguments are null.
	 */
	public CalculatedFuzzySet(IDomain domain, IIntUnaryFunction membershipFunction) {
		super();
		if (domain == null || membershipFunction == null) {
			throw new IllegalArgumentException("Argument should not be null.");
		}
		this.domain = domain;
		this.membershipFunction = membershipFunction;
	}

	@Override
	public IDomain getDomain() {
		return domain;
	}

	@Override
	public double getMembership(DomainElement element) {
		if (element == null) {
			throw new IllegalArgumentException("Argument should not be null.");
		}
		
		int elementIndex = domain.indexOfElement(element);
		if (elementIndex == -1) {
			throw new IllegalArgumentException("Element not in domain.");
		}
		
		return membershipFunction.valueAt(elementIndex);
	}

}
