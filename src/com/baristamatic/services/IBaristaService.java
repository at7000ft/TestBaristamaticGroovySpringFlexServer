package com.baristamatic.services;

import java.math.BigDecimal;
import java.util.List;

import com.baristamatic.domain.Drink;
import com.baristamatic.domain.InventoryEntry;

/**
 * <p>
 * Title: IBaristaService.java
 * </p>
 * <p>
 * Description:
 * 
 * </p>
 * <p>
 * Oct 14, 2010
 * </p>
 * 
 * @author RHOLLAND DRS Technologies Inc.
 * 
 * 
 */
public interface IBaristaService {

	public BigDecimal getDrinkCost(String name);

	public Drink getDrink(String drinkNumber);

	public List<Drink> getDrinks();

	public List<InventoryEntry> getInventoryAvailable();

	public boolean areIngredientsAvailable(String drinkNumber);

	public void orderDrink(String drinkNumber);

	public void reStockInventory();

	public void addNewIngredient(InventoryEntry inventoryEntry);
}
