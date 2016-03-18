package hr.freskov.fuzzy;

/**
 * Utility class offering standard fuzzy logic operations.
 * 
 * @author freskov
 * @version 1.0
 */
public class Operations {

	private Operations() {
	}

	private static final IUnaryFunction ZADEH_NOT = a -> 1 - a;

	private static final IBinaryFunction ZADEH_OR = (a, b) -> Math.max(a, b);

	private static final IBinaryFunction ZADEH_AND = (a, b) -> Math.min(a, b);

	/**
	 * Returns Zadeh not function <code>f(a) = 1 - a</code>.
	 * 
	 * @return Zadeh not function.
	 */
	public static IUnaryFunction zadehNot() {
		return ZADEH_NOT;
	}

	/**
	 * Returns Zadeh or function <code>f(a, b) = max(a, b)</code>.
	 * 
	 * @return Zadeh or function.
	 */
	public static IBinaryFunction zadehOr() {
		return ZADEH_OR;
	}

	/**
	 * Returns Zadeh and function <code>f(a, b) = min(a, b)</code>.
	 * 
	 * @return Zadeh and function.
	 */
	public static IBinaryFunction zadehAnd() {
		return ZADEH_AND;
	}

	/**
	 * Returns a fuzzy set with membership function
	 * <code>&mu;<sub>result</sub>(x) = f(&mu;<sub>a</sub>(x))</code>.
	 * 
	 * @param a
	 *            fuzzy set
	 * @param f
	 *            unary function
	 * @return result of the function.
	 */
	public static IFuzzySet unaryOperation(IFuzzySet a, IUnaryFunction f) {
		if (a == null || f == null) {
			throw new IllegalArgumentException("Argument should not be null.");
		}

		IDomain domain = a.getDomain();

		return new CalculatedFuzzySet(domain, index -> f.valueAt(a.getMembership(index)));
	}

	/**
	 * Returns a fuzzy set with membership function
	 * <code>&mu;<sub>result</sub>(x) = f(&mu;<sub>a</sub>(x), &mu;<sub>b</sub>(x))</code>.
	 * 
	 * @param a
	 *            first fuzzy set
	 * @param b
	 *            second fuzzy set
	 * @param f
	 *            binary function
	 * @return result of the function.
	 */
	public static IFuzzySet binaryOperation(IFuzzySet a, IFuzzySet b, IBinaryFunction f) {
		if (a == null || b == null || f == null) {
			throw new IllegalArgumentException("Argument should not be null.");
		}
		if (!a.getDomain().equals(b.getDomain())) {
			throw new IllegalArgumentException("Fuzzy sets have different domains.");
		}

		IDomain domain = a.getDomain();

		return new CalculatedFuzzySet(domain, index -> f.valueAt(a.getMembership(index), b.getMembership(domain.getElement(index))));
	}

}
