package hr.freskov.fuzzy;

/**
 * Defines a binary function <code>f : R x R -> R</code>.
 * 
 * @author freskov
 * @version 1.0
 */
public interface IBinaryFunction {

	/**
	 * Return function value with specified arguments.
	 * 
	 * @param arg1
	 *            first argument
	 * @param arg2
	 *            second argument
	 * @return function value.
	 */
	double valueAt(double arg1, double arg2);

}
