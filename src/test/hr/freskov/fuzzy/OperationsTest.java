package hr.freskov.fuzzy;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Operations class tests.
 * 
 * @author freskov
 */
public class OperationsTest {

	@Test
	public void zadehNot() {
		assertEquals(0.0, Operations.zadehNot().valueAt(1.0), 1e-9);
		assertEquals(0.25, Operations.zadehNot().valueAt(0.75), 1e-9);
		assertEquals(0.5, Operations.zadehNot().valueAt(0.5), 1e-9);
		assertEquals(0.75, Operations.zadehNot().valueAt(0.25), 1e-9);
		assertEquals(1.0, Operations.zadehNot().valueAt(0.0), 1e-9);
	}

	@Test
	public void zadehOr() {
		assertEquals(0.0, Operations.zadehOr().valueAt(0.0, 0.0), 1e-9);
		assertEquals(0.25, Operations.zadehOr().valueAt(0.0, 0.25), 1e-9);
		assertEquals(0.25, Operations.zadehOr().valueAt(0.25, 0.25), 1e-9);
		assertEquals(0.5, Operations.zadehOr().valueAt(0.5, 0.25), 1e-9);
		assertEquals(0.5, Operations.zadehOr().valueAt(0.5, 0.5), 1e-9);
		assertEquals(0.75, Operations.zadehOr().valueAt(0.5, 0.75), 1e-9);
		assertEquals(1.0, Operations.zadehOr().valueAt(1.0, 0.75), 1e-9);
		assertEquals(1.0, Operations.zadehOr().valueAt(1.0, 0.75), 1e-9);
	}

	@Test
	public void zadehAnd() {
		assertEquals(0.0, Operations.zadehAnd().valueAt(0.0, 0.0), 1e-9);
		assertEquals(0.0, Operations.zadehAnd().valueAt(0.0, 0.25), 1e-9);
		assertEquals(0.25, Operations.zadehAnd().valueAt(0.25, 0.25), 1e-9);
		assertEquals(0.25, Operations.zadehAnd().valueAt(0.5, 0.25), 1e-9);
		assertEquals(0.5, Operations.zadehAnd().valueAt(0.5, 0.5), 1e-9);
		assertEquals(0.5, Operations.zadehAnd().valueAt(0.5, 0.75), 1e-9);
		assertEquals(0.75, Operations.zadehAnd().valueAt(1.0, 0.75), 1e-9);
		assertEquals(1.0, Operations.zadehAnd().valueAt(1.0, 1.0), 1e-9);
	}
	
	@Test
	public void unaryOperation() {
		IFuzzySet fs = new CalculatedFuzzySet(Domain.intRange(0, 100), StandardFuzzySets.gammaFunction(25, 75));
		IFuzzySet res = Operations.unaryOperation(fs, Operations.zadehNot());
		for (DomainElement element : fs.getDomain()) {
			assertEquals(1.0-fs.getMembership(element), res.getMembership(element), 1e-9);
		}
	}
	
	@Test
	public void binaryOperation() {
		IFuzzySet fs1 = new CalculatedFuzzySet(Domain.intRange(0, 100), StandardFuzzySets.gammaFunction(25, 75));
		IFuzzySet fs2 = new CalculatedFuzzySet(Domain.intRange(0, 100), StandardFuzzySets.lFunction(25, 75));
		IFuzzySet res = Operations.binaryOperation(fs1, fs2, Operations.zadehOr());
		for (DomainElement element : res.getDomain()) {
			assertEquals(
					Math.max(fs1.getMembership(element), fs2.getMembership(element)), 
					res.getMembership(element), 
					1e-9);
		}
	}

}
