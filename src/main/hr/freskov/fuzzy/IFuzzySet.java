package hr.freskov.fuzzy;

/**
 * Interface to a fuzzy set. Fuzzy set is a set containing ordered pairs of
 * elements from a domain <code>U</code> (universal set) and their memberships.
 * Membership of the element is a real value in <code>[0,1]</code>.<br>
 * <code>FuzzySet = {(x, &mu;(x)) | x &isin; U, &mu; : U -> [0,1]}</code>
 * 
 * @author freskov
 * @version 1.0
 */
public interface IFuzzySet {

	/**
	 * Returns the domain of the fuzzy set.
	 * 
	 * @return domain of the fuzzy set.
	 */
	IDomain getDomain();

	/**
	 * Returns membership of the element.
	 * 
	 * @param element
	 *            domain element
	 * @return membership of the element.
	 */
	double getMembership(DomainElement element);
	
	/**
	 * Returns membership of the element.
	 * 
	 * @param index
	 *            domain element index
	 * @return membership of the element.
	 */
	double getMembership(int index);

}
