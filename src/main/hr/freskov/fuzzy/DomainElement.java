package hr.freskov.fuzzy;

import java.util.Arrays;

/**
 * Class represents a domain element. Domain element is a tuple of elements from
 * simple domains larger domain is consisted of.
 * 
 * @author freskov
 * @version 1.0
 */
public class DomainElement {

	private int values[];

	private DomainElement(int[] values) {
		if (values == null) {
			throw new IllegalArgumentException("Values cannot be null.");
		}
		this.values = values;
	}

	/**
	 * Returns number of components this domain element is consisted of.
	 * 
	 * @return number of components
	 */
	public int getNumberOfComponents() {
		return values.length;
	}

	/**
	 * Returns component of this domain element at a specified index.
	 * 
	 * @param index
	 *            index of the component
	 * @return Component of this domain element.
	 * @throws IndexOutOfBoundsException
	 *             if <code>index < 0 || index >= values.length</code>.
	 */
	public int getComponent(int index) {
		if (index < 0 || index >= values.length) {
			throw new IndexOutOfBoundsException("Index: " + index + " out of bounds.");
		}
		return values[index];
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(values);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof DomainElement)) {
			return false;
		}
		DomainElement other = (DomainElement) obj;
		if (!Arrays.equals(values, other.values)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		boolean first = true;
		for (int value : values) {
			if (first) {
				first = false;
			} else {
				sb.append(",");
			}
			sb.append(value);
		}

		sb.append(")");
		return sb.toString();
	}

	/**
	 * Creates a new domain element with specified values.
	 * 
	 * @param values
	 *            domain element values
	 * @return new domain element with specified values.
	 */
	public static DomainElement of(int... values) {
		return new DomainElement(values);
	}

}
