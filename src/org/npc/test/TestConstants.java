package org.npc.test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.npc.testmodel.api.Product;

public class TestConstants {
	private static Map<UUID, Product> currentProductLookup = null;
	public static Map<UUID, Product> getCurrentProductLookup() {
		if (currentProductLookup == null) {
			currentProductLookup = new HashMap<UUID, Product>();
		}
		
		return currentProductLookup;
	}
	public synchronized static void setCurrentProductLookup(Map<UUID, Product> productLookup) {
		currentProductLookup = productLookup;
	}
}
