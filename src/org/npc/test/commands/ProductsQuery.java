package org.npc.test.commands;

import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import org.npc.test.TestConstants;
import org.npc.test.commands.interfaces.ResultCommandInterface;
import org.npc.testmodel.api.Product;
import org.npc.testmodel.api.ProductListing;

public class ProductsQuery implements ResultCommandInterface<ProductListing> {
	@Override
	public ProductListing execute() {
		Random random = new Random();
		ProductListing products = new ProductListing();
		int listSize = (MIN_LIST_SIZE + random.nextInt(MAX_LIST_SIZE - MIN_LIST_SIZE));
		
		for (int i = 0; i < listSize; i++) {
			products.addProduct(this.buildProduct(random));
		}
		
		TestConstants.setCurrentProductLookup(
			products.getProducts().stream().collect(Collectors.toMap((product -> product.getId()), (product -> product)))
		);
		
		return products;
	}
	
	private Product buildProduct(Random random) {
		String lookupCode = RandomStringUtils.randomAlphabetic(LOOKUP_CODE_LENGTH);

		return (new Product()).
			setId(UUID.randomUUID()).
			setLookupCode(lookupCode);
	}
	
	private static final int MIN_LIST_SIZE = 5;
	private static final int MAX_LIST_SIZE = 15;
	private static final int LOOKUP_CODE_LENGTH = 20;
	
	public ProductsQuery() {}
}
