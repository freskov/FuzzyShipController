package hr.freskov.fuzzy;

/**
 * Interface to a discrete domain (universal set). Domain is built using design
 * pattern Composite. User can ask for cardinality of the domain via
 * {@link #getCardinality()}, number of simple domains this domain is consisted
 * of via {@link #getNumberOfComponents()}, access simple domains by their index
 * {@link #getComponent(int)}. User can also get the index of the element of the
 * domain through {@link #indexOfElement(DomainElement)} and fetch the element
 * at the specified index using {@link #getElement(int)}.
 * 
 * @author freskov
 * @version 1.0
 */
public interface IDomain extends Iterable<DomainElement> {

	/**
	 * Returns cardinality of the domain.
	 * 
	 * @return cardinality of the domain.
	 */
	int getCardinality();

	/**
	 * Returns number of components (simple domains) this domain is consisted
	 * of.
	 * 
	 * @return number of components.
	 */
	int getNumberOfComponents();

	/**
	 * Returns component (simple domain) at specified index.
	 * 
	 * @param index
	 *            index of the component
	 * @return component at specified index.
	 * @throws IndexOutOfBoundsException
	 *             if <code>index < 0 || index >= getNumberOfComponents()</code>
	 */
	IDomain getComponent(int index);

	/**
	 * Returns index of specified domain element or -1 if the domain doesn't
	 * contain the specified element.
	 * 
	 * @param element
	 *            domain element
	 * @return index of specified domain element or -1 if the domain doesn't
	 *         contain the specified element.
	 */
	int indexOfElement(DomainElement element);

	/**
	 * Returns domain element at specified index.
	 * 
	 * @param index
	 *            index of the element
	 * @return domain element at specified index.
	 * @throws IndexOutOfBoundsException
	 *             if <code>index < 0 || index >= getCardinality()</code>
	 */
	DomainElement getElement(int index);

}
