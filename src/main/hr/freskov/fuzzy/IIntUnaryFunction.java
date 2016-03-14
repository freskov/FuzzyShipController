package hr.freskov.fuzzy;

/**
 * Interface defines a function <code>f: Z -> R</code>.
 * 
 * @author freskov
 * @version 1.0
 */
public interface IIntUnaryFunction {
	
	/**
	 * Returns function value for specified argument.
	 * @param arg argument
	 * @return function value.
	 */
	double valueAt(int arg);

}
