package hr.freskov.fuzzy;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * CalculatedFuzzySet class test.
 * 
 * @author freskov
 */
public class CalculatedFuzzySetTest {

	@Test(expected = IllegalArgumentException.class)
	public void IllegalArgumentsInConstruction() {
		new CalculatedFuzzySet(null, null);
	}
	
	@Test
	public void membershipFunction() {
		IFuzzySet fs = new CalculatedFuzzySet(
				Domain.intRange(0, 100), 
				new IIntUnaryFunction() {
					@Override
					public double valueAt(int arg) {
						return 1.0;
					}
				});
		assertEquals(1.0, fs.getMembership(DomainElement.of(5)), 1e-9);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void IllegalDomainElement() {
		IFuzzySet fs = new CalculatedFuzzySet(
				Domain.intRange(0, 100), 
				new IIntUnaryFunction() {
					@Override
					public double valueAt(int arg) {
						return 1.0;
					}
				});
		fs.getMembership(DomainElement.of(-5));
	}

}
