package hr.freskov.fuzzy.control.ship;

import hr.freskov.fuzzy.Domain;
import hr.freskov.fuzzy.IDomain;

/**
 * TODO
 * 
 * @author freskov
 * @version 1.0
 */
public class Domains {
	
	private Domains() {
	}
	
	/**
	 * TODO
	 * @return
	 */
	public static IDomain getDistanceDomain() {
		return Domain.intRange(0, 1500);
	}
	
	/**
	 * TODO
	 * @return
	 */
	public static IDomain getSpeedDomain() {
		return Domain.intRange(0, 50);
	}
	
	/**
	 * TODO
	 * @return
	 */
	public static IDomain getDirectionDomain() {
		return Domain.intRange(0, 1);
	}

}
