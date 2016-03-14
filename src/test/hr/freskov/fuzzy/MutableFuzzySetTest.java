package hr.freskov.fuzzy;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * MutableFuzzySet class test.
 * 
 * @author freskov
 */
public class MutableFuzzySetTest {

	@Test(expected = IllegalArgumentException.class)
	public void IllegalArgumentsInConstruction() {
		new MutableFuzzySet(null);
	}
	
	@Test
	public void membershipFunction() {
		MutableFuzzySet fs = new MutableFuzzySet(Domain.intRange(0, 100));
		assertEquals(0.0, fs.getMembership(DomainElement.of(5)), 1e-9);
		assertEquals(0.0, fs.getMembership(DomainElement.of(10)), 1e-9);
		assertEquals(0.0, fs.getMembership(DomainElement.of(15)), 1e-9);
		fs.setMembership(DomainElement.of(5), 0.25);
		fs.setMembership(DomainElement.of(10), 0.5);
		fs.setMembership(DomainElement.of(15), 0.75);
		assertEquals(0.25, fs.getMembership(DomainElement.of(5)), 1e-9);
		assertEquals(0.5, fs.getMembership(DomainElement.of(10)), 1e-9);
		assertEquals(0.75, fs.getMembership(DomainElement.of(15)), 1e-9);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void IllegalDomainElement() {
		MutableFuzzySet fs = new MutableFuzzySet(Domain.intRange(0, 100));
		fs.getMembership(DomainElement.of(-5));
	}

}
