package hr.freskov.fuzzy;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Domain class tests.
 * 
 * @author freskov
 */
public class DomainTest {

	@Test
	public void intRange() {
		IDomain domain = Domain.intRange(2, 6);
		assertEquals(5, domain.getCardinality());
		assertEquals(1, domain.getNumberOfComponents());
		assertEquals(DomainElement.of(5), domain.getElement(3));
		assertEquals(3, domain.indexOfElement(DomainElement.of(5)));
	}

	@Test
	public void combineSimpleDomains() {
		IDomain domain = Domain.combine(Domain.intRange(2, 6), Domain.intRange(-6, -2));
		assertEquals(5 * 5, domain.getCardinality());
		assertEquals(2, domain.getNumberOfComponents());
		assertEquals(DomainElement.of(4, -3), domain.getElement(13));
		assertEquals(13, domain.indexOfElement(DomainElement.of(4, -3)));
	}

	@Test
	public void combineCompositeDomains() {
		IDomain domain = Domain.combine(Domain.combine(Domain.intRange(2, 6), Domain.intRange(-6, -2)),
				Domain.combine(Domain.intRange(1, 5), Domain.intRange(11, 15)));
		assertEquals(5 * 5 * 5 * 5, domain.getCardinality());
		assertEquals(4, domain.getNumberOfComponents());
		assertEquals(DomainElement.of(2, -6, 3, 14), domain.getElement(13));
		assertEquals(13, domain.indexOfElement(DomainElement.of(2, -6, 3, 14)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void combineWithIllegalArguments() {
		Domain.combine(null, Domain.intRange(0, 5));
	}

}
