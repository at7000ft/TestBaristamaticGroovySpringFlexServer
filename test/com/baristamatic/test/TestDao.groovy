package com.baristamatic.test

import groovy.util.GroovyTestCase

import org.junit.Before
import org.junit.Test

import com.baristamatic.dao.MemoryInventoryDao
import com.baristamatic.domain.InventoryEntry


/**
 * <p> Title: TestDao.java </p>
 * <p> Description:   
 *
 * </p>
 * <p> Dec 13, 2010</p>
 * @author RHOLLAND
 * DRS Technologies Inc.
 *
 *
 */
class TestDao extends GroovyTestCase {
	InventoryEntry invEntry;
	MemoryInventoryDao dao;

	@Before
	void setup() {
		invEntry = new InventoryEntry(displayName: "Vanilla", name: "vanilla", cost: 0.75, count: 10)
		dao = new MemoryInventoryDao();
	}

	@Test
	void testAddEntry() {
		invEntry = new InventoryEntry(displayName: "Vanilla", name: "vanilla", cost: 0.75, count: 10)
		dao = new MemoryInventoryDao();
		dao.addNewIngredient invEntry
		assertEquals 10, dao.ingredientsAvailable.size()
		BigDecimal cost = dao.getIngredientCost("vanilla")
		assertEquals 0.75, cost
	}
}
