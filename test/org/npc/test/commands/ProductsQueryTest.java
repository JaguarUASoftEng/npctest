package org.npc.test.commands;

import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.npc.test.TestConstants;
import org.npc.testmodel.api.Product;
import org.npc.testmodel.api.ProductListing;

public class ProductsQueryTest {
	@Test
	public void testExecute() {
		ProductListing productListing = (new ProductsQuery()).execute();
		
		assertTrue("Listing provides a result with count greater than zero", (productListing.getProducts().size() > 0));
	}

	@Test
	public void testExecuteUpdatesCache() {
		(new ProductsQuery()).execute();
		
		assertTrue("Cache is populated", (TestConstants.getCurrentProductLookup().size() > 0));
	}

	@Test
	public void testExecuteProvidesOnlyIdentification() {
		ProductListing productListing = (new ProductsQuery()).execute();

		for (Product product : productListing.getProducts()) {
			assertTrue("ID is populated", !product.getId().equals(new UUID(0, 0)));
			assertTrue("LookupCode is populated", !StringUtils.isBlank(product.getLookupCode()));
			assertTrue("Count is not populated", (product.getCount() < 0));
		}
	}
}
