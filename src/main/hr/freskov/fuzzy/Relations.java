package hr.freskov.fuzzy;

/**
 * Utility class offering methods for checking fuzzy relation properties. User
 * can check if a relation is <code>U x U</code> relation
 * {@link #isUTimesURelation(IFuzzySet)}, symmetric
 * {@link #isSymmetric(IFuzzySet)}, reflexive {@link #isReflexive(IFuzzySet)},
 * max-min transitive {@link #isMaxMinTransitive(IFuzzySet)} and equivalence
 * {@link #isFuzzyEquivalence(IFuzzySet)}. User can also calculate a composition
 * of binary relations
 * {@link #compositionOfBinaryRelations(IFuzzySet, IFuzzySet)}.
 * 
 * 
 * @author freskov
 * @version 1.0
 */
public class Relations {

	private Relations() {
	}

	/**
	 * Checks if the domain of specified relation is a Cartesian product
	 * <code>U x U</code> of universal set <code>U</code> with itself.
	 * 
	 * @param relation
	 *            fuzzy relation
	 * @return <code>true</code> if the domain is a Cartesian product
	 *         <code>U x U</code> of universal set <code>U</code> with itself,
	 *         <code>false</code> otherwise.
	 */
	public static boolean isUTimesURelation(IFuzzySet relation) {
		if (relation == null) {
			throw new IllegalArgumentException("Argument should not be null.");
		}
		IDomain domain = relation.getDomain();
		if (domain.getNumberOfComponents() != 2) {
			return false;
		}
		
		IDomain u = domain.getComponent(0);
		IDomain v = domain.getComponent(1);
		
		return u.equals(v);
	}

	/**
	 * Checks if the relation is symmetric. Relation is symmetric if
	 * <code>&mu;(x,y) = &mu;(y,x) &forall;x,y &isin; U</code>
	 * where <code>U x U</code> is the domain of the relation.
	 * 
	 * @param relation
	 *            fuzzy relation
	 * @return <code>true</code> if the relation is symmetric,
	 *         <code>false</code> otherwise.
	 */
	public static boolean isSymmetric(IFuzzySet relation) {
		if (relation == null) {
			throw new IllegalArgumentException("Argument should not be null.");
		}
		if (!isUTimesURelation(relation)) {
			return false;
		}
		
		IDomain u = relation.getDomain().getComponent(0);
		
		for (DomainElement x : u) {
			for (DomainElement y : u) {
				DomainElement xy = DomainElement.of(x.getComponent(0), y.getComponent(0));
				DomainElement yx = DomainElement.of(y.getComponent(0), x.getComponent(0));
				if (Double.compare(relation.getMembership(xy), relation.getMembership(yx)) != 0) {
					return false;
				}
			}
		}
		
		return true;
	}

	/**
	 * Checks if the relation is reflexive. Relation is reflexive if
	 * <code>&mu;(x,x) = 1 &forall;x &isin; U</code> where
	 * <code>U x U</code> is the domain of the relation.
	 * 
	 * @param relation
	 *            fuzzy relation
	 * @return <code>true</code> if the relation is reflexive,
	 *         <code>false</code> otherwise.
	 */
	public static boolean isReflexive(IFuzzySet relation) {
		if (relation == null) {
			throw new IllegalArgumentException("Argument should not be null.");
		}
		if (!isUTimesURelation(relation)) {
			return false;
		}
		
		IDomain u = relation.getDomain().getComponent(0);
		
		for (DomainElement x : u) {
			DomainElement xx = DomainElement.of(x.getComponent(0), x.getComponent(0));
			if (Double.compare(relation.getMembership(xx), 1.0) != 0) {
				return false;
			}
		}
		
		return true;
	}

	/**
	 * Checks if the relation is max-min transitive. Relation is max-min transitive if
	 * <code>&mu;(x,z) &ge; max<sub>&forall;y&isin;U</sub>(min(&mu;(x,y), &mu;(y,z)) &forall;x,z &isin; U</code>
	 * where <code>U x U</code> is the domain of the relation.
	 * 
	 * @param relation
	 *            fuzzy relation
	 * @return <code>true</code> if the relation is max-min transitive,
	 *         <code>false</code> otherwise.
	 */
	public static boolean isMaxMinTransitive(IFuzzySet relation) {
		if (relation == null) {
			throw new IllegalArgumentException("Argument should not be null.");
		}
		if (!isUTimesURelation(relation)) {
			return false;
		}
		
		IDomain u = relation.getDomain().getComponent(0);
		
		for (DomainElement x : u) {
			for (DomainElement z : u) {
				DomainElement xz = DomainElement.of(x.getComponent(0), z.getComponent(0));
				for (DomainElement y : u) {
					DomainElement xy = DomainElement.of(x.getComponent(0), y.getComponent(0));
					DomainElement yz = DomainElement.of(y.getComponent(0), z.getComponent(0));
					if (Double.compare(relation.getMembership(xz), 
							Math.min(relation.getMembership(xy), relation.getMembership(yz))) < 0) {
						return false;
					}
				}
			}
		}
		
		return true;
	}

	/**
	 * Checks if the relation is fuzzy equivalence. Relations is fuzzy equvalence if it is:
	 * <ul>
	 * 	<li>reflexive</li>
	 * 	<li>symmetric</li>
	 * 	<li>max-min transitive</li>
	 * </ul>
	 * 
	 * @param relation
	 *            fuzzy relation
	 * @return <code>true</code> if the relation is fuzzy equivalence,
	 *         <code>false</code> otherwise.
	 */
	public static boolean isFuzzyEquivalence(IFuzzySet relation) {
		return isSymmetric(relation) && isReflexive(relation) && isMaxMinTransitive(relation);
	}

	/**
	 * Returns a composition of binary relations where domain of the first
	 * binary relation is <code>U x V</code> and the domain of the second
	 * relation is <code>V x W</code>. Membership function of the composition is
	 * <code>&mu;<sub>result</sub>(x,z) = max<sub>&forall;y&isin;V</sub>(min(&mu;<sub>a</sub>(x,y), &mu;<sub>b</sub>(y,z))
	 * &forall;x &isin; U, &forall;z &isin; W</code>.
	 * 
	 * @param a
	 *            first fuzzy relation
	 * @param b
	 *            second fuzzy relation
	 * @return composition of binary relations.
	 */
	public static IFuzzySet compositionOfBinaryRelations(IFuzzySet a, IFuzzySet b) {
		if (a == null || b == null) {
			throw new IllegalArgumentException("Argument should not be null.");
		}
		if (a.getDomain().getNumberOfComponents() != 2 || b.getDomain().getNumberOfComponents() != 2) {
			throw new IllegalArgumentException("Expected binary relations.");
		}

		IDomain u = a.getDomain().getComponent(0);
		IDomain v = a.getDomain().getComponent(1);
		IDomain v2 = b.getDomain().getComponent(0);
		IDomain w = b.getDomain().getComponent(1);
		if (!v.equals(v2)) {
			throw new IllegalArgumentException("Domains do not match.");
		}
		
		IDomain uw = Domain.combine(u, w);		
		return new CalculatedFuzzySet(uw, elementIndex -> {
				double membership = 0.0;
				DomainElement xz = uw.getElement(elementIndex);
				for (DomainElement y : v) {
					DomainElement xy = DomainElement.of(xz.getComponent(0), y.getComponent(0));
					DomainElement yz = DomainElement.of(y.getComponent(0), xz.getComponent(1));
					membership = Math.max(membership, Math.min(a.getMembership(xy), b.getMembership(yz)));
				}
				return membership;
		});
	}

}
