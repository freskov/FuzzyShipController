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

	private static final IUnaryFunction ZADEH_NOT = new IUnaryFunction() {
		@Override
		public double valueAt(double arg) {
			return 1.0 - arg;
		}
	};

	private static final IBinaryFunction ZADEH_OR = new IBinaryFunction() {
		@Override
		public double valueAt(double arg1, double arg2) {
			return Math.max(arg1, arg2);
		}
	};

	private static final IBinaryFunction ZADEH_AND = new IBinaryFunction() {
		@Override
		public double valueAt(double arg1, double arg2) {
			return Math.min(arg1, arg2);
		}
	};

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
	 * Returns a fuzzy set that is result of performing specified function on
	 * the given fuzzy set.
	 * 
	 * @param fuzzySet
	 *            fuzzy set
	 * @param function
	 *            unary function
	 * @return result of the function.
	 */
	public static IFuzzySet unaryOperation(IFuzzySet fuzzySet, IUnaryFunction function) {
		if (fuzzySet == null || function == null) {
			throw new IllegalArgumentException("Argument should not be null.");
		}

		IDomain domain = fuzzySet.getDomain();
		MutableFuzzySet resultFS = new MutableFuzzySet(domain);
		for (DomainElement element : domain) {
			resultFS.setMembership(element, function.valueAt(fuzzySet.getMembership(element)));
		}

		return resultFS;
	}

	/**
	 * Returns a fuzzy set that is result of performing specified function on
	 * the given fuzzy sets.
	 * 
	 * @param fuzzySet1
	 *            first fuzzy set
	 * @param fuzzySet2
	 *            second fuzzy set
	 * @param function
	 *            binary function
	 * @return result of the function.
	 */
	public static IFuzzySet binaryOperation(IFuzzySet fuzzySet1, IFuzzySet fuzzySet2, IBinaryFunction function) {
		if (fuzzySet1 == null || fuzzySet2 == null || function == null) {
			throw new IllegalArgumentException("Argument should not be null.");
		}
		if (!fuzzySet1.getDomain().equals(fuzzySet2.getDomain())) {
			throw new UnsupportedOperationException("Fuzzy sets have different domains.");
		}
		
		IDomain domain = fuzzySet1.getDomain();
		MutableFuzzySet resultFS = new MutableFuzzySet(domain);
		for (DomainElement element : domain) {
			resultFS.setMembership(
					element, 
					function.valueAt(fuzzySet1.getMembership(element), fuzzySet2.getMembership(element)));
		}
		
		return resultFS;
	}

}
