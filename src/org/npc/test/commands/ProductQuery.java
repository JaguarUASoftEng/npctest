package org.npc.test.commands;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

import org.npc.test.TestConstants;
import org.npc.test.api.Product;
import org.npc.test.commands.interfaces.ResultCommandInterface;

public class ProductQuery implements ResultCommandInterface<Product> {
	@Override
	public Product execute() {
		return this.completeProduct(
			TestConstants.getCurrentProductLookup().get(this.productId)
		);
	}
	
	private Product completeProduct(Product product) {
		Random random = new Random();
		
		return product.
			setCreatedOn(LocalDateTime.now().minusDays(random.nextInt(DAYS_IN_FIVE_YEARS))).
			setCount(MIN_PRODUCT_COUNT + random.nextInt(MAX_PRODUCT_COUNT - MIN_PRODUCT_COUNT));
	}

	private static final int MIN_PRODUCT_COUNT = 50;
	private static final int MAX_PRODUCT_COUNT = 300;
	private static final int DAYS_IN_FIVE_YEARS = 1827;

	private UUID productId;
	
	public ProductQuery(UUID productId) {
		this.productId = productId;
	}
}
