package com.baristamatic.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.baristamatic.domain.Drink;
import com.baristamatic.domain.InventoryEntry;

/**
 * <p>
 * Title: IInventoryDao.java
 * </p>
 * <p>
 * Description:
 * 
 * </p>
 * <p>
 * Oct 3, 2010
 * </p>
 * 
 * @author RHOLLAND DRS Technologies Inc.
 * 
 * 
 */
public interface IInventoryDao {

	public boolean ingredientAvailable(String name, int count);

	public List<InventoryEntry> getIngredientsAvailable();

	public void removeIngredients(Map ingredientMap);

	public BigDecimal getIngredientCost(String name);

	public int getIngredientCount(String iname);

	public boolean areIngredientsAvailable(Drink drink);

	public void addNewIngredient(InventoryEntry inventoryEntry);
}
