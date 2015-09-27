package org.npc.test.commands.builders;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;

public class ProductBuilder {
	//Model products
	public static org.npc.testmodel.models.Product buildNewModelProduct() {
		return (new org.npc.testmodel.models.Product()).
				setDescription("").setLookupCode(RandomStringUtils.randomAlphabetic(PRODUCT_LOOKUP_CODE_LENGTH)).
				setPrice(-1.0).setItemType(-1).setCost(-1.0).setQuantity(MIN_PRODUCT_COUNT + random.nextInt(MAX_PRODUCT_COUNT - MIN_PRODUCT_COUNT)).setReorderPoint(-1).
				setRestockLevel(-1).setParentItem(-1).setExtendedDescription("").setActive(-1).setMSRP(-1.00).
				setCreatedOn(LocalDateTime.now().minusDays(random.nextInt(DAYS_IN_FIVE_YEARS)));
	}
	
	public static List<org.npc.testmodel.models.Product> buildManyNewModelProducts() {
		List<org.npc.testmodel.models.Product> products = new LinkedList<org.npc.testmodel.models.Product>();
		int listLength = MIN_PRODUCT_LIST_LENGTH + random.nextInt(MAX_PRODUCT_LIST_LENGTH - MIN_PRODUCT_LIST_LENGTH);
		
		for (int i = 0; i < listLength; i++) {
			products.add(buildNewModelProduct());
		}
		
		return products;
	}
	
	public static org.npc.testmodel.models.Product buildModelProduct() {
		return (new org.npc.testmodel.models.Product(UUID.randomUUID())).
				setDescription("").setLookupCode(RandomStringUtils.randomAlphabetic(PRODUCT_LOOKUP_CODE_LENGTH)).
				setPrice(-1.0).setItemType(-1).setCost(-1.0).setQuantity(MIN_PRODUCT_COUNT + random.nextInt(MAX_PRODUCT_COUNT - MIN_PRODUCT_COUNT)).setReorderPoint(-1).
				setRestockLevel(-1).setParentItem(-1).setExtendedDescription("").setActive(-1).setMSRP(-1.00).
				setCreatedOn(LocalDateTime.now().minusDays(random.nextInt(DAYS_IN_FIVE_YEARS)));
	}
	
	public static List<org.npc.testmodel.models.Product> buildManyModelProducts() {
		List<org.npc.testmodel.models.Product> products = new LinkedList<org.npc.testmodel.models.Product>();
		int listLength = MIN_PRODUCT_LIST_LENGTH + random.nextInt(MAX_PRODUCT_LIST_LENGTH - MIN_PRODUCT_LIST_LENGTH);
		
		for (int i = 0; i < listLength; i++) {
			products.add(buildModelProduct());
		}
		
		return products;
	}
	
	public static org.npc.testmodel.api.Product buildNewApiProduct() {
		return (new org.npc.testmodel.api.Product()).
				setDescription("").setLookupCode(RandomStringUtils.randomAlphabetic(PRODUCT_LOOKUP_CODE_LENGTH)).
				setPrice(-1.0).setItemType(-1).setCost(-1.0).setQuantity(MIN_PRODUCT_COUNT + random.nextInt(MAX_PRODUCT_COUNT - MIN_PRODUCT_COUNT)).setReorderPoint(-1).
				setRestockLevel(-1).setParentItem(-1).setExtendedDescription("").setActive(-1).setMSRP(-1.00).
				setCreatedOn(LocalDateTime.now().minusDays(random.nextInt(DAYS_IN_FIVE_YEARS)));
	}
	
	public static List<org.npc.testmodel.api.Product> buildManyNewApiProducts() {
		List<org.npc.testmodel.api.Product> products = new LinkedList<org.npc.testmodel.api.Product>();
		int listLength = MIN_PRODUCT_LIST_LENGTH + random.nextInt(MAX_PRODUCT_LIST_LENGTH - MIN_PRODUCT_LIST_LENGTH);
		
		for (int i = 0; i < listLength; i++) {
			products.add(buildNewApiProduct());
		}
		
		return products;
	}
	
	public static org.npc.testmodel.api.Product buildApiProduct() {
		return (new org.npc.testmodel.api.Product()).
				setDescription("").setLookupCode(RandomStringUtils.randomAlphabetic(PRODUCT_LOOKUP_CODE_LENGTH)).
				setPrice(-1.0).setItemType(-1).setCost(-1.0).setQuantity(MIN_PRODUCT_COUNT + random.nextInt(MAX_PRODUCT_COUNT - MIN_PRODUCT_COUNT)).setReorderPoint(-1).
				setRestockLevel(-1).setParentItem(-1).setExtendedDescription("").setActive(-1).setMSRP(-1.00).
				setCreatedOn(LocalDateTime.now().minusDays(random.nextInt(DAYS_IN_FIVE_YEARS)));
	}
	
	public static List<org.npc.testmodel.api.Product> buildManyApiProducts() {
		List<org.npc.testmodel.api.Product> products = new LinkedList<org.npc.testmodel.api.Product>();
		int listLength = MIN_PRODUCT_LIST_LENGTH + random.nextInt(MAX_PRODUCT_LIST_LENGTH - MIN_PRODUCT_LIST_LENGTH);
		
		for (int i = 0; i < listLength; i++) {
			products.add(buildApiProduct());
		}
		
		return products;
	}
	
	private static Random random;
	
	private static final int MIN_PRODUCT_COUNT = 50;
	private static final int MAX_PRODUCT_COUNT = 500;
	private static final int MIN_PRODUCT_LIST_LENGTH = 2;
	private static final int MAX_PRODUCT_LIST_LENGTH = 10;
	private static final int PRODUCT_LOOKUP_CODE_LENGTH = 20;
	private static final int DAYS_IN_FIVE_YEARS = 1827;
	
	static {
		random = new Random(System.currentTimeMillis());
	}
}
