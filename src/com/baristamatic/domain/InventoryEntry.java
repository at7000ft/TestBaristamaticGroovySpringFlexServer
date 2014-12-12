package com.baristamatic.domain;

import java.math.BigDecimal;

/**
 * <p> Title: InventoryEntry.java </p>
 * <p> Description:   
 *
 * </p>
 * <p> Oct 14, 2010</p>
 * @author RHOLLAND
 * DRS Technologies Inc.
 *
 *
 */
public class InventoryEntry {
	private String displayName;
	private String name;
	private BigDecimal cost;
	private int count;
	
	
	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}


	/**
	 * @param Set displayName  
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param Set name  
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the cost
	 */
	public BigDecimal getCost() {
		return cost;
	}


	/**
	 * @param Set cost  
	 */
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}


	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}


	/**
	 * @param Set count  
	 */
	public void setCount(int count) {
		this.count = count;
	}


	public void consumeIngredient(int decrCount) {
		if (count > decrCount) {
			count -= decrCount;
		} else {
			count = 0;
		}
	}
}
