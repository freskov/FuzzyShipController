package hr.freskov.fuzzy;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * StandardFuzzySets class test.
 * 
 * @author freskov
 */
public class StandardFuzzySetsTest {

	@Test
	public void GammaFunction() {
		IFuzzySet fs = new CalculatedFuzzySet(
				Domain.intRange(0, 100), 
				StandardFuzzySets.gammaFunction(25, 75));
		
		assertEquals(0.0, fs.getMembership(DomainElement.of(20)), 1e-9);
		assertEquals(0.0, fs.getMembership(DomainElement.of(25)), 1e-9);
		assertEquals(0.5, fs.getMembership(DomainElement.of(50)), 1e-9);
		assertEquals(1.0, fs.getMembership(DomainElement.of(75)), 1e-9);
		assertEquals(1.0, fs.getMembership(DomainElement.of(80)), 1e-9);
		
	}
	
	@Test
	public void LambdaFunction() {
		IFuzzySet fs = new CalculatedFuzzySet(
				Domain.intRange(0, 100), 
				StandardFuzzySets.lambdaFunction(30, 50, 70));
		
		assertEquals(0.0, fs.getMembership(DomainElement.of(20)), 1e-9);
		assertEquals(0.0, fs.getMembership(DomainElement.of(30)), 1e-9);
		assertEquals(0.25, fs.getMembership(DomainElement.of(35)), 1e-9);
		assertEquals(0.5, fs.getMembership(DomainElement.of(40)), 1e-9);
		assertEquals(0.75, fs.getMembership(DomainElement.of(45)), 1e-9);
		assertEquals(1.0, fs.getMembership(DomainElement.of(50)), 1e-9);
		assertEquals(0.75, fs.getMembership(DomainElement.of(55)), 1e-9);
		assertEquals(0.5, fs.getMembership(DomainElement.of(60)), 1e-9);
		assertEquals(0.25, fs.getMembership(DomainElement.of(65)), 1e-9);
		assertEquals(0.0, fs.getMembership(DomainElement.of(70)), 1e-9);
		assertEquals(0.0, fs.getMembership(DomainElement.of(80)), 1e-9);
		
	}
	
	@Test
	public void LFunction() {
		IFuzzySet fs = new CalculatedFuzzySet(
				Domain.intRange(0, 100), 
				StandardFuzzySets.lFunction(25, 75));
		
		assertEquals(1.0, fs.getMembership(DomainElement.of(20)), 1e-9);
		assertEquals(1.0, fs.getMembership(DomainElement.of(25)), 1e-9);
		assertEquals(0.5, fs.getMembership(DomainElement.of(50)), 1e-9);
		assertEquals(0.0, fs.getMembership(DomainElement.of(75)), 1e-9);
		assertEquals(0.0, fs.getMembership(DomainElement.of(80)), 1e-9);
		
	}
	
	@Test
	public void PiFunction() {
		IFuzzySet fs = new CalculatedFuzzySet(
				Domain.intRange(0, 100), 
				StandardFuzzySets.piFunction(30, 40, 50, 60));
		
		assertEquals(0.0, fs.getMembership(DomainElement.of(20)), 1e-9);
		assertEquals(0.0, fs.getMembership(DomainElement.of(30)), 1e-9);
		assertEquals(0.5, fs.getMembership(DomainElement.of(35)), 1e-9);
		assertEquals(1.0, fs.getMembership(DomainElement.of(40)), 1e-9);
		assertEquals(1.0, fs.getMembership(DomainElement.of(45)), 1e-9);
		assertEquals(1.0, fs.getMembership(DomainElement.of(50)), 1e-9);
		assertEquals(0.5, fs.getMembership(DomainElement.of(55)), 1e-9);
		assertEquals(0.0, fs.getMembership(DomainElement.of(60)), 1e-9);
		assertEquals(0.0, fs.getMembership(DomainElement.of(70)), 1e-9);
		
	}

}
