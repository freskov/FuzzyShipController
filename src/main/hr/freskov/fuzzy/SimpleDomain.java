package hr.freskov.fuzzy;

import java.util.Iterator;

/**
 * Implements a simple integer domain defined by its first and last element.
 * 
 * @author freskov
 * @version 1.0
 */
public class SimpleDomain implements IDomain  {

	private int first;
	private int last;

	/**
	 * Initializes a simple integer domain <code>[first, last]</code>.
	 * 
	 * @param first
	 *            first element of the domain
	 * @param last
	 *            last element of the domain
	 * @throws IllegalArgumentException
	 *             if <code>first > last</code>
	 */
	public SimpleDomain(int first, int last) {
		super();
		if (first > last) {
			throw new IllegalArgumentException("First element should be smaller or equal than last element.");
		}
		this.first = first;
		this.last = last;
	}

	@Override
	public int getCardinality() {
		return last - first + 1;
	}

	@Override
	public int getNumberOfComponents() {
		return 1;
	}

	@Override
	public IDomain getComponent(int index) {
		return this;
	}

	@Override
	public int indexOfElement(DomainElement element) {
		if (element == null) {
			throw new IllegalArgumentException("Argument should not be null");
		}
		if (element.getNumberOfComponents() != 1) {
			return -1;
		}
		int value = element.getComponent(0);
		if (value < first || value > last) {
			return -1;
		}
		return value - first;
	}

	@Override
	public DomainElement getElement(int index) {
		if (index < 0 || first + index > last) {
			throw new IndexOutOfBoundsException(
					"Index: " + index + " out of bounds [" + 0 + ", " + (last - first) + ">.");
		}
		return DomainElement.of(first + index);
	}

	@Override
	public Iterator<DomainElement> iterator() {
		return new Iterator<DomainElement>() {

			private int current = first;

			@Override
			public boolean hasNext() {
				return current <= last;
			}

			@Override
			public DomainElement next() {
				int value = current;
				++current;
				return DomainElement.of(value);
			}
		};
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + first;
		result = prime * result + last;
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
		if (!(obj instanceof SimpleDomain)) {
			return false;
		}
		SimpleDomain other = (SimpleDomain) obj;
		if (first != other.first) {
			return false;
		}
		if (last != other.last) {
			return false;
		}
		return true;
	}

}
