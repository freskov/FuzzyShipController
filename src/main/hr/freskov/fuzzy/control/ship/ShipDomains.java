package hr.freskov.fuzzy.control.ship;

import hr.freskov.fuzzy.Domain;
import hr.freskov.fuzzy.IDomain;

/**
 * TODO
 * 
 * @author freskov
 * @version 1.0
 */
public class ShipDomains {
	
	private ShipDomains() {
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
		return Domain.intRange(-100, 100);
	}
	
	/**
	 * TODO
	 * @return
	 */
	public static IDomain getDirectionDomain() {
		return Domain.intRange(0, 1);
	}
	
	/**
	 * TODO
	 * @return
	 */
	public static IDomain getAccelerationDomain() {
		return Domain.intRange(-30, 30);
	}
	
	/**
	 * TODO
	 * @return
	 */
	public static IDomain getHelmDomain() {
		return Domain.intRange(-90, 90);
	}

}
