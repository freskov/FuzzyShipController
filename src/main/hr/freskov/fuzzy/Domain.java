package hr.freskov.fuzzy;

/**
 * Utility class containing methods for creating simple and composite domains
 * through {@link #intRange(int, int)} and {@link #combine(IDomain, IDomain)}.
 * 
 * @author freskov
 * @version 1.1
 */
public class Domain {

	private Domain() {
	}

	/**
	 * Constructs a simple integer domain <code>[first, last]</code>
	 * 
	 * @param first
	 *            first element
	 * @param last
	 *            last element
	 * @return Simple integer domain.
	 */
	public static IDomain intRange(int first, int last) {
		return new SimpleDomain(first, last);
	}

	/**
	 * Constructs a domain that is a Cartesian product from specified domains.
	 * 
	 * @param a
	 *            first domain
	 * @param b
	 *            second domain
	 * @return Cartesian product of the domains.
	 */
	public static IDomain combine(IDomain a, IDomain b) {
		if (a == null || b == null) {
			throw new IllegalArgumentException("Argument should not be null.");
		}
		int numberOfComponentsA = a.getNumberOfComponents();
		int numberOfComponentsB = b.getNumberOfComponents();
		int numberOfComponents = numberOfComponentsA + numberOfComponentsB;
		SimpleDomain[] components = new SimpleDomain[numberOfComponents];
		for (int i = 0; i < numberOfComponentsA; ++i) {
			components[i] = (SimpleDomain) a.getComponent(i);
		}
		for (int i = 0; i < numberOfComponentsB; ++i) {
			components[numberOfComponentsA + i] = (SimpleDomain) b.getComponent(i);
		}
		return new CompositeDomain(components);
	}

}
