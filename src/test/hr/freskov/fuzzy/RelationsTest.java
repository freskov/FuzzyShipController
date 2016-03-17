package hr.freskov.fuzzy;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Relations class tests.
 * 
 * @author freskov
 */
public class RelationsTest {
	
	private static IDomain u;
	private static IDomain uu;
	private static MutableFuzzySet mfs;
	
	static {
		u = Domain.intRange(1, 5);
		uu = Domain.combine(u, u);
		mfs = new MutableFuzzySet(uu);
		mfs.setMembership(DomainElement.of(1, 1), 1.0);
		mfs.setMembership(DomainElement.of(1, 2), 0.8);
		mfs.setMembership(DomainElement.of(1, 3), 0.0);
		mfs.setMembership(DomainElement.of(1, 4), 0.1);
		mfs.setMembership(DomainElement.of(1, 5), 0.2);
		mfs.setMembership(DomainElement.of(2, 1), 0.8);
		mfs.setMembership(DomainElement.of(2, 2), 1.0);
		mfs.setMembership(DomainElement.of(2, 3), 0.4);
		mfs.setMembership(DomainElement.of(2, 4), 0.0);
		mfs.setMembership(DomainElement.of(2, 5), 0.9);
		mfs.setMembership(DomainElement.of(3, 1), 0.0);
		mfs.setMembership(DomainElement.of(3, 2), 0.4);
		mfs.setMembership(DomainElement.of(3, 3), 1.0);
		mfs.setMembership(DomainElement.of(3, 4), 0.0);
		mfs.setMembership(DomainElement.of(3, 5), 0.0);
		mfs.setMembership(DomainElement.of(4, 1), 0.1);
		mfs.setMembership(DomainElement.of(4, 2), 0.0);
		mfs.setMembership(DomainElement.of(4, 3), 0.0);
		mfs.setMembership(DomainElement.of(4, 4), 1.0);
		mfs.setMembership(DomainElement.of(4, 5), 0.5);
		mfs.setMembership(DomainElement.of(5, 1), 0.2);
		mfs.setMembership(DomainElement.of(5, 2), 0.9);
		mfs.setMembership(DomainElement.of(5, 3), 0.0);
		mfs.setMembership(DomainElement.of(5, 4), 0.5);
		mfs.setMembership(DomainElement.of(5, 5), 1.0);
	}
	
	@Test
	public void UTimesURelation() {
		IDomain u = Domain.intRange(0, 100);
		IDomain v = Domain.intRange(-100, 100);
		assertTrue(Relations.isUTimesURelation(new MutableFuzzySet(Domain.combine(u, u))));
		assertFalse(Relations.isUTimesURelation(new MutableFuzzySet(Domain.combine(u, v))));
		assertFalse(Relations.isUTimesURelation(new MutableFuzzySet(u)));
	}
	
	@Test
	public void IsSymmetric() {
		MutableFuzzySet mfs = new MutableFuzzySet(uu);
		assertTrue(Relations.isSymmetric(mfs));
		mfs.setMembership(DomainElement.of(1, 2), 0.5);
		assertFalse(Relations.isSymmetric(mfs));
	}
	
	@Test
	public void IsReflexive() {
		IDomain u = Domain.intRange(0, 100);
		IDomain uu = Domain.combine(u, u);
		assertFalse(Relations.isReflexive(new MutableFuzzySet(uu)));
		MutableFuzzySet fs = new MutableFuzzySet(uu);
		for (DomainElement x : u) {
			DomainElement xx = DomainElement.of(x.getComponent(0), x.getComponent(0));
			fs.setMembership(xx, 1.0);
		}
		assertTrue(Relations.isReflexive(fs));
	}
	
	@Test
	public void IsMaxMinTransitive() {
		assertFalse(Relations.isMaxMinTransitive(mfs));
		IFuzzySet fs = Relations.compositionOfBinaryRelations(mfs, mfs);
		assertFalse(Relations.isMaxMinTransitive(fs));
		fs = Relations.compositionOfBinaryRelations(fs, mfs);
		assertTrue(Relations.isMaxMinTransitive(fs));
	}
	
	@Test
	public void CompositionOfBinaryRelations() {		
		IFuzzySet fs = Relations.compositionOfBinaryRelations(mfs, mfs);
		assertEquals(1.0, fs.getMembership(DomainElement.of(1, 1)), 1e-9);
		assertEquals(0.8, fs.getMembership(DomainElement.of(1, 2)), 1e-9);
		assertEquals(0.4, fs.getMembership(DomainElement.of(1, 3)), 1e-9);
		assertEquals(0.2, fs.getMembership(DomainElement.of(1, 4)), 1e-9);
		assertEquals(0.8, fs.getMembership(DomainElement.of(1, 5)), 1e-9);
		assertEquals(0.8, fs.getMembership(DomainElement.of(2, 1)), 1e-9);
		assertEquals(1.0, fs.getMembership(DomainElement.of(2, 2)), 1e-9);
		assertEquals(0.4, fs.getMembership(DomainElement.of(2, 3)), 1e-9);
		assertEquals(0.5, fs.getMembership(DomainElement.of(2, 4)), 1e-9);
		assertEquals(0.9, fs.getMembership(DomainElement.of(2, 5)), 1e-9);
		assertEquals(0.4, fs.getMembership(DomainElement.of(3, 1)), 1e-9);
		assertEquals(0.4, fs.getMembership(DomainElement.of(3, 2)), 1e-9);
		assertEquals(1.0, fs.getMembership(DomainElement.of(3, 3)), 1e-9);
		assertEquals(0.0, fs.getMembership(DomainElement.of(3, 4)), 1e-9);
		assertEquals(0.4, fs.getMembership(DomainElement.of(3, 5)), 1e-9);
		assertEquals(0.2, fs.getMembership(DomainElement.of(4, 1)), 1e-9);
		assertEquals(0.5, fs.getMembership(DomainElement.of(4, 2)), 1e-9);
		assertEquals(0.0, fs.getMembership(DomainElement.of(4, 3)), 1e-9);
		assertEquals(1.0, fs.getMembership(DomainElement.of(4, 4)), 1e-9);
		assertEquals(0.5, fs.getMembership(DomainElement.of(4, 5)), 1e-9);
		assertEquals(0.8, fs.getMembership(DomainElement.of(5, 1)), 1e-9);
		assertEquals(0.9, fs.getMembership(DomainElement.of(5, 2)), 1e-9);
		assertEquals(0.4, fs.getMembership(DomainElement.of(5, 3)), 1e-9);
		assertEquals(0.5, fs.getMembership(DomainElement.of(5, 4)), 1e-9);
		assertEquals(1.0, fs.getMembership(DomainElement.of(5, 5)), 1e-9);
	}

}
