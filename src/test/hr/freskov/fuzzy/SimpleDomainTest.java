package hr.freskov.fuzzy;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * SimpleDomain class tests.
 * 
 * @author freskov
 */
public class SimpleDomainTest {

	@Test(expected = IllegalArgumentException.class)
	public void IllegalArgumentsInConstruction() {
		new SimpleDomain(5, 4);
	}

	@Test
	public void singleElementDomainCardinality() {
		IDomain domain = new SimpleDomain(3, 3);
		assertEquals(1, domain.getCardinality());
	}

	@Test
	public void domainCardinality() {
		IDomain domain = new SimpleDomain(2, 4);
		assertEquals(3, domain.getCardinality());
	}

	@Test
	public void numberOfComponents() {
		IDomain domain = new SimpleDomain(2, 4);
		assertEquals(1, domain.getNumberOfComponents());
	}

	@Test
	public void getComponent() {
		IDomain domain = new SimpleDomain(2, 4);
		assertEquals(domain, domain.getComponent(0));
	}

	@Test
	public void indexOfElement() {
		IDomain domain = new SimpleDomain(3, 8);
		assertEquals(4, domain.indexOfElement(DomainElement.of(7)));
	}

	@Test
	public void indexOfElementOutOfDomainRange() {
		IDomain domain = new SimpleDomain(3, 8);
		assertEquals(-1, domain.indexOfElement(DomainElement.of(11)));
	}

	@Test
	public void getElement() {
		IDomain domain = new SimpleDomain(3, 8);
		assertEquals(DomainElement.of(7), domain.getElement(4));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void getElementOutOfDomainRange() {
		IDomain domain = new SimpleDomain(3, 8);
		domain.getElement(6);
	}

}
