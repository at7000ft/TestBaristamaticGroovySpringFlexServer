package com.baristamatic.dao;

import java.math.BigDecimal;
import java.util.List;

import com.baristamatic.domain.Drink;


/**
 * <p> Title: IDrinkDao.java </p>
 * <p> Description:   
 *
 * </p>
 * <p> Oct 3, 2010</p>
 * @author RHOLLAND
 * DRS Technologies Inc.
 *
 *
 */
public interface IDrinkDao {
		public List<Drink> getDrinks(IInventoryDao inventoryDao);
		public BigDecimal getCost(String name, IInventoryDao inventoryDao);
		public Drink getDrink(String number,IInventoryDao inventoryDao);
}
