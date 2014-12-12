package com.baristamatic.dao

import java.math.BigDecimal;

import com.baristamatic.domain.Drink;



import java.util.List;



/**
 * <p> Title: MemoryDrinkDao.java </p>
 * <p> Description:   
 *
 * </p>
 * <p> Oct 3, 2010</p>
 * @author RHOLLAND
 * DRS Technologies Inc.
 *
 *
 */
class MemoryDrinkDao implements IDrinkDao {
	def drinks
	
	public MemoryDrinkDao() {
		super();
		drinks = [
			new Drink(number: 1, name: "coffee", displayName: "Coffee", recipe: ["coffee": 3, "sugar" : 1, "cream": 1]),
			new Drink(number: 2, name: "decafCoffee", displayName: "DecafCoffee", recipe: ["decafCoffee": 3, "sugar" : 1, "cream": 1]),
			new Drink(number: 3, name: "caffeLatte", displayName: "Caffe Latte", recipe: ["espresso": 2, "steamedMilk":1]),
			new Drink(number: 4, name: "caffeAmericano", displayName: "Caffe Americano", recipe: ["espresso": 3]),
			new Drink(number: 5, name: "caffeMocha", displayName: "Caffe Mocha", recipe: ["espresso": 1, "cocoa" : 1, "steamedMilk": 1, "whippedCream":1]),
			new Drink(number: 6, name: "cappuccino", displayName: "Cappuccino", recipe: ["espresso": 2, "steamedMilk": 1, "foamedMilk": 1])
		]
	}
	
	List<Drink> getDrinks(IInventoryDao inventoryDao) {
		def drks = drinks.sort{it.number};
		drks.each {it.cost = getCost(it.name, inventoryDao) }
		drks.each {it.inStock = inventoryDao.areIngredientsAvailable(it) }
		return drks
	}
	
	BigDecimal getCost(String iname, IInventoryDao inventoryDao) {
		Drink drink = drinks.find {it.name.equals(iname)}
		Map recipe = drink.recipe
		def cost = 0.0
		for (each in recipe) {
			cost += inventoryDao.getIngredientCost(each.key)
		}
		return cost
	}
	
//	boolean areIngredientsAvailable(String iname, IInventoryDao inventoryDao) {
//		Drink drink = drinks.find {it.name.equals(iname)}
//		Map recipe = drink.recipe
//		for (each in recipe) {
//			cost += inventoryDao.getIngredientCost(each.key)
//		}
//		return true
//	}
	
	Drink getDrink(String inumber,IInventoryDao inventoryDao) {
		def dr = drinks.find {it.number == inumber as Integer}
		dr.cost = getCost(dr.name, inventoryDao)
		dr.inStock = inventoryDao.areIngredientsAvailable(dr)
		return dr;
	}
}

