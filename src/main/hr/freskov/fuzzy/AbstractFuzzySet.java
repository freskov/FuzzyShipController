package hr.freskov.fuzzy;

/**
 * Abstract fuzzy set. 
 * 
 * @author freskov
 * @version 1.0
 */
public abstract class AbstractFuzzySet implements IFuzzySet {
	
	protected IDomain domain;
	
	public AbstractFuzzySet(IDomain domain) {
		this.domain = domain;
	}
	
	@Override
	public double getMembership(DomainElement element) {
		if (element == null) {
			throw new IllegalArgumentException("Argument should not be null.");
		}
		int index = domain.indexOfElement(element);
		if (index == -1) {
			throw new IllegalArgumentException("Element not in domain.");
		}
		return getMembership(index);
	}
	
	

}
