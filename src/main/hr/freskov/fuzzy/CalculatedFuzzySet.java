package hr.freskov.fuzzy;

/**
 * Fuzzy set implementation with defined membership function. Calculates element
 * membership on demand.
 * 
 * @author freskov
 * @version 1.1
 */
public class CalculatedFuzzySet extends AbstractFuzzySet {

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
		super(domain);
		if (domain == null || membershipFunction == null) {
			throw new IllegalArgumentException("Argument should not be null.");
		}
		this.membershipFunction = membershipFunction;
	}

	@Override
	public IDomain getDomain() {
		return domain;
	}
	
	@Override
	public double getMembership(int index) {	
		return membershipFunction.valueAt(index);
	}

}
