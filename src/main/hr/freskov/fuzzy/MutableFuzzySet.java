package hr.freskov.fuzzy;

import java.util.Arrays;

/**
 * Fuzzy set implementation with defined memberships. Memberships of elements
 * can be set via {@link #setMembership(DomainElement, double)}.
 * 
 * @author freskov
 * @version 1.1
 */
public class MutableFuzzySet extends AbstractFuzzySet {

	private double[] memberships;

	/**
	 * Initializes mutable fuzzy set with specified domain. Membership of all
	 * elements of the domain is 0.
	 * 
	 * @param domain
	 *            domain
	 */
	public MutableFuzzySet(IDomain domain) {
		super(domain);
		if (domain == null) {
			throw new IllegalArgumentException("Argument should not be null.");
		}
		memberships = new double[domain.getCardinality()];
		Arrays.fill(memberships, 0.0);
	}

	@Override
	public IDomain getDomain() {
		return domain;
	}
	
	@Override
	public double getMembership(int index) {
		return memberships[index];
	}

	/**
	 * Sets membership of the specified element to a given value.
	 * 
	 * @param element
	 *            domain element
	 * @param membership
	 *            membership value
	 */
	public MutableFuzzySet setMembership(DomainElement element, double membership) {
		if (element == null) {
			throw new IllegalArgumentException("Argument should not be null.");
		}
		if (Double.compare(membership, 0) < 0 || Double.compare(membership, 1) > 0) {
			throw new IllegalArgumentException("Membership: " + membership + " has to be [0, 1].");
		}

		int elementIndex = domain.indexOfElement(element);
		if (elementIndex == -1) {
			throw new IllegalArgumentException("Element " + element + " not in domain: " + domain + ".");
		}

		memberships[elementIndex] = membership;
		return this;
	}

}
