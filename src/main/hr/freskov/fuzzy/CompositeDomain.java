package hr.freskov.fuzzy;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Implements a domain composed of simple domains following a design pattern
 * Composite.
 * 
 * @author freskov
 * @version 1.0
 */
public class CompositeDomain implements IDomain {

	private SimpleDomain[] components;

	/**
	 * Initializes new composite domain with specified components.
	 * 
	 * @param components
	 *            components array
	 */
	public CompositeDomain(SimpleDomain[] components) {
		super();
		if (components == null) {
			throw new IllegalArgumentException("Argument should not be null.");
		}
		this.components = components;
	}

	@Override
	public int getCardinality() {
		int cardinality = 1;
		for (IDomain component : components) {
			cardinality *= component.getCardinality();
		}
		return cardinality;
	}

	@Override
	public int getNumberOfComponents() {
		return components.length;
	}

	@Override
	public IDomain getComponent(int index) {
		if (index < 0 || index >= components.length) {
			throw new IndexOutOfBoundsException(
					"Index: " + index + " out of bounds [" + 0 + ", " + components.length + ">.");
		}
		return components[index];
	}

	@Override
	public int indexOfElement(DomainElement element) {
		if (element == null) {
			throw new IllegalArgumentException("Argument should not be null");
		}
		int elementIndex = 0;
		int componentIndex = 0;
		for (IDomain component : components) {
			elementIndex *= component.getCardinality();
			int elementComponent = element.getComponent(componentIndex);
			int elementComponentIndex = component.indexOfElement(DomainElement.of(elementComponent));
			if (elementComponentIndex == -1) {
				return -1;
			}
			elementIndex += elementComponentIndex;
			++componentIndex;
		}
		return elementIndex;
	}

	@Override
	public DomainElement getElement(int index) {
		if (index < 0 || index >= getCardinality()) {
			throw new IndexOutOfBoundsException("Index: " + index + " out of bounds.");
		}
		int[] values = new int[components.length];
		for (int i = components.length - 1; i >= 0; --i) {
			IDomain component = components[i];
			int cardinality = component.getCardinality();
			values[i] = component.getElement(index % cardinality).getComponent(0);
			index /= cardinality;
		}
		return DomainElement.of(values);
	}

	@Override
	public Iterator<DomainElement> iterator() {
		return new Iterator<DomainElement>() {

			private boolean hasNext = true;
			private int[] current;

			{
				current = new int[components.length];
				Arrays.fill(current, 0);
			}

			@Override
			public boolean hasNext() {
				return hasNext;
			}

			@Override
			public DomainElement next() {
				int[] values = new int[components.length];
				boolean updateIndex = true;
				for (int i = components.length - 1; i >= 0; --i) {
					values[i] = components[i].getElement(current[i]).getComponent(0);
					if (updateIndex) {
						++current[i];
						if (current[i] == components[i].getCardinality()) {
							current[i] = 0;
							if (i == 0) {
								hasNext = false;
							}
						} else {
							updateIndex = false;
						}
					}
				}
				return DomainElement.of(values);
			}

		};
	}

}
