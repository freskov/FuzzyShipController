package hr.freskov.fuzzy;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * CompositeDomain class tests.
 * 
 * @author freskov
 */
public class CompositeDomainTest {

	@Test(expected = IllegalArgumentException.class)
	public void IllegalArgumentsInConstruction() {
		new CompositeDomain(null);
	}

	@Test
	public void singleElementDomainCardinality() {
		IDomain domain = new SimpleDomain(3, 3);
		assertEquals(1, domain.getCardinality());
	}

	@Test
	public void domainCardinality() {
		SimpleDomain[] components = { new SimpleDomain(2, 4), new SimpleDomain(-3, 2), new SimpleDomain(100, 150) };
		CompositeDomain domain = new CompositeDomain(components);
		assertEquals(3 * 6 * 51, domain.getCardinality());
	}

	@Test
	public void numberOfComponents() {
		SimpleDomain[] components = { new SimpleDomain(-3, 2), new SimpleDomain(2, 4), new SimpleDomain(100, 150) };
		CompositeDomain domain = new CompositeDomain(components);
		assertEquals(3, domain.getNumberOfComponents());
	}

	@Test
	public void getComponent() {
		SimpleDomain[] components = { new SimpleDomain(-3, 2), new SimpleDomain(2, 4), new SimpleDomain(100, 150) };
		CompositeDomain domain = new CompositeDomain(components);
		assertEquals(new SimpleDomain(2, 4), domain.getComponent(1));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void getComponentIndexOutOfBounds() {
		SimpleDomain[] components = { new SimpleDomain(-3, 2), new SimpleDomain(2, 4), new SimpleDomain(100, 150) };
		CompositeDomain domain = new CompositeDomain(components);
		domain.getComponent(3);
	}

	@Test
	public void indexOfElement() {
		SimpleDomain[] components = { new SimpleDomain(-3, 2), new SimpleDomain(2, 4), new SimpleDomain(100, 150) };
		CompositeDomain domain = new CompositeDomain(components);
		assertEquals(52, domain.indexOfElement(DomainElement.of(-3, 3, 101)));
		assertEquals(205, domain.indexOfElement(DomainElement.of(-2, 3, 101)));
	}

	@Test
	public void indexOfElementOutOfDomainRange() {
		SimpleDomain[] components = { new SimpleDomain(-3, 2), new SimpleDomain(2, 4), new SimpleDomain(100, 150) };
		CompositeDomain domain = new CompositeDomain(components);
		assertEquals(-1, domain.indexOfElement(DomainElement.of(-3, 1, 101)));
	}

	@Test
	public void getElement() {
		SimpleDomain[] components = { new SimpleDomain(-3, 2), new SimpleDomain(2, 4), new SimpleDomain(100, 150) };
		CompositeDomain domain = new CompositeDomain(components);
		assertEquals(DomainElement.of(-3, 3, 101), domain.getElement(52));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void getElementOutOfDomainRange() {
		SimpleDomain[] components = { new SimpleDomain(-3, 2), new SimpleDomain(2, 4), new SimpleDomain(100, 150) };
		CompositeDomain domain = new CompositeDomain(components);
		domain.getElement(domain.getCardinality());
	}

}
