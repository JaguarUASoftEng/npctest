package org.npc.test.commands;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.easymock.EasyMockSupport;
import org.junit.Test;
import org.npc.test.commands.builders.ProductBuilder;
import org.npc.testmodel.api.Product;
import org.npc.testmodel.enums.ProductApiRequestStatus;
import org.npc.testmodel.repositories.interfaces.ProductRepositoryInterface;

public class ProductQueryTest extends EasyMockSupport {
	@Test
	public void testExecuteReturnsDefined() {
		org.npc.testmodel.models.Product modelProduct = ProductBuilder.buildModelProduct();
		ProductRepositoryInterface productRepository = this.createMock(ProductRepositoryInterface.class);
		
		expect(productRepository.get(modelProduct.getId())).andReturn(modelProduct);
		replay(productRepository);

		Product apiProduct = (new ProductQuery()).
			setProductId(modelProduct.getId()).
			setProductRepository(productRepository).
			execute();
		verify();

		assertNotNull("Retrieved product is defined", apiProduct);
		assertTrue("Retrieved product has an OK status", (apiProduct.getApiRequestStatus() == ProductApiRequestStatus.OK));
		assertTrue("Retrieved product has correct ID", apiProduct.getId().equals(modelProduct.getId()));
		assertTrue("Retrieved product has data", (apiProduct.getQuantity() == modelProduct.getQuantity()));
	}

	@Test (expected=NullPointerException.class)
	public void testExecuteThrowsExceptionUndefined() {
		UUID nonExistantId = UUID.randomUUID();
		ProductRepositoryInterface productRepository = this.createMock(ProductRepositoryInterface.class);
		
		expect(productRepository.get(nonExistantId)).andReturn((org.npc.testmodel.models.Product)null);
		replay(productRepository);

		(new ProductQuery()).
			setProductId(nonExistantId).
			setProductRepository(productRepository).
			execute();
		verify();
	}
}
