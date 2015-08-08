package org.npc.test.commands;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.npc.testmodel.api.Product;
import org.npc.testmodel.api.ProductListing;

public class ProductQueryTest {
	@Before
	public void setUp() throws Exception {
		ProductListing productListing = (new ProductsQuery()).execute();
		this.products = productListing.getProducts();
	}

	@Test
	public void testExecuteReturnsDefined() {
		Product product = this.products.get(0);
		product = (new ProductQuery(product.getId())).execute();

		assertNotNull("Retrieved product is defined", product);
	}

	@Test (expected=NullPointerException.class)
	public void testExecuteThrowsExceptionUndefined() {
		(new ProductQuery(UUID.randomUUID())).execute();
	}

	@Test
	public void testExecuteReturnsDataOfDefined() {
		Product product = this.products.get(0);
		product = (new ProductQuery(product.getId())).execute();

		assertTrue("Retrieved product contains additional data", (product.getCount() > 0));
	}

	private List<Product> products;
}
