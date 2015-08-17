package org.npc.test.commands;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.easymock.EasyMockSupport;
import org.junit.Test;
import org.npc.test.commands.builders.ProductBuilder;
import org.npc.testmodel.api.ProductListing;
import org.npc.testmodel.repositories.interfaces.ProductRepositoryInterface;

public class ProductsQueryTest extends EasyMockSupport {
	@Test
	public void testExecute() {
		List<org.npc.testmodel.models.Product> modelProducts = ProductBuilder.buildManyModelProducts();
		ProductRepositoryInterface productRepository = this.createMock(ProductRepositoryInterface.class);
		
		expect(productRepository.all()).andReturn(modelProducts);
		replay(productRepository);

		ProductListing apiProductListing = (new ProductsQuery()).
			setProductRepository(productRepository).
			execute();
		verify();

		assertNotNull("Retrieved product is defined", apiProductListing);
		assertTrue("Retrieved product has correct number of products", (apiProductListing.getProducts().size() == modelProducts.size()));
	}
}
