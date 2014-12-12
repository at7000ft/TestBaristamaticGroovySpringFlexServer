package com.baristamatic.services;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Service;

import com.baristamatic.dao.IDrinkDao;
import com.baristamatic.dao.IInventoryDao;
import com.baristamatic.dao.MemoryDrinkDao;
import com.baristamatic.dao.MemoryInventoryDao;
import com.baristamatic.domain.Drink;
import com.baristamatic.domain.InventoryEntry;

/**
 * <p>
 * Title: BaristaService.java
 * </p>
 * <p>
 * Description: Run from command line with Main or access via blazeds from TestFlex4Baristamatic
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
/*
 * Tell Spring to expose this as a remotely accessed service through BlazeDS If no name is specified after @Service the
 * service is named using the classname - "helloWorld"
 */
@Service
@RemotingDestination
public class BaristaService implements IBaristaService {

	private Logger log = Logger.getLogger(BaristaService.class);
	private IDrinkDao drinkDao = new MemoryDrinkDao();
	private IInventoryDao inventoryDao = new MemoryInventoryDao();

	/**
	 * 
	 */
	public BaristaService() {
		super();
		log.debug("const: called ");
	}

	// Define this method as remotely accessible, can use @RemotingExclude to exclude
	@RemotingInclude
	public BigDecimal getDrinkCost(String name) {
		log.debug("getDrinkCost: called with " + name);
		return drinkDao.getCost(name, inventoryDao);
	}

	// Define this method as remotely accessible, can use @RemotingExclude to exclude
	@RemotingInclude
	public Drink getDrink(String drinkNumber) {
		log.debug("getDrink: called with " + drinkNumber);
		return drinkDao.getDrink(drinkNumber, inventoryDao);
	}

	// Define this method as remotely accessible, can use @RemotingExclude to exclude
	@RemotingInclude
	public List<Drink> getDrinks() {
		log.debug("getDrinks: called ");
		return drinkDao.getDrinks(inventoryDao);
	}

	// Define this method as remotely accessible, can use @RemotingExclude to exclude
	@RemotingInclude
	public List<InventoryEntry> getInventoryAvailable() {
		log.debug("getInventoryAvailable: called ");
		return inventoryDao.getIngredientsAvailable();
	}

	// Define this method as remotely accessible, can use @RemotingExclude to exclude
	@RemotingInclude
	public boolean areIngredientsAvailable(String drinkNumber) {
		log.debug("areIngredientsAvailable: called with " + drinkNumber);
		return inventoryDao.areIngredientsAvailable(getDrink(drinkNumber));
	}

	// Define this method as remotely accessible, can use @RemotingExclude to exclude
	@RemotingInclude
	public void orderDrink(String drinkNumber) {
		log.debug("orderDrink: called with " + drinkNumber);
		inventoryDao.removeIngredients(getDrink(drinkNumber).getRecipe());
	}

	// Define this method as remotely accessible, can use @RemotingExclude to exclude
	@RemotingInclude
	public void reStockInventory() {
		log.debug("reStockInventory: called ");
		inventoryDao = new MemoryInventoryDao();
	}

	/*
	 * @see com.baristamatic.services.IBaristaService#addNewIngredient(com.baristamatic.domain.InventoryEntry)
	 */
	@RemotingInclude
	public void addNewIngredient(InventoryEntry inventoryEntry) {
		log.debug("addNewIngredient: called with " + inventoryEntry);
		inventoryDao.addNewIngredient(inventoryEntry);
	}
}
