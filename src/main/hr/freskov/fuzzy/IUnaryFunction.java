package hr.freskov.fuzzy;

/**
 * Defines a unary function <code>f : R -> R</code>.
 * 
 * @author freskov
 * @version 1.0
 */
public interface IUnaryFunction {

	/**
	 * Return function value with specified arguments.
	 * 
	 * @param arg
	 *            argument
	 * @return function value.
	 */
	double valueAt(double arg);

}
