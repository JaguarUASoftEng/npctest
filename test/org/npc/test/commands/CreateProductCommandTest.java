package org.npc.test.commands;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.easymock.EasyMockSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.npc.test.commands.builders.ProductBuilder;
import org.npc.testmodel.api.Product;
import org.npc.testmodel.enums.ProductApiRequestStatus;
import org.npc.testmodel.repositories.ProductRepository;
import org.npc.testmodel.repositories.interfaces.ProductRepositoryInterface;

public class CreateProductCommandTest extends EasyMockSupport {
	@Before
	public void setUp() {
		this.successfullySavedProductId = EMPTY_UUID;
	}

	@Test
	public void testExecuteLookupCodeEmptyNotOK() {
		Product apiProduct = (new CreateProductCommand()).
			setApiProduct(
				ProductBuilder.buildNewApiProduct().
					setLookupCode(StringUtils.EMPTY)
			).
			execute();
		
		assertNotNull("Retrieved product is defined", apiProduct);
		assertTrue("Retrieved product has empty ID", EMPTY_UUID.equals(apiProduct.getId()));
		assertTrue("Retrieved product has status: INVALID INPUT", (apiProduct.getApiRequestStatus() == ProductApiRequestStatus.INVALID_INPUT));
	}

	@Test
	public void testExecuteLookupCodeAlreadyExistsNotOK() {
		Product duplicateLookupCodeProduct = ProductBuilder.buildNewApiProduct();
		ProductRepositoryInterface productRepository = this.createMock(ProductRepositoryInterface.class);
		
		expect(productRepository.byLookupCode(duplicateLookupCodeProduct.getLookupCode())).
			andReturn(new org.npc.testmodel.models.Product(duplicateLookupCodeProduct));
		replay(productRepository);

		Product apiProduct = (new CreateProductCommand()).
			setApiProduct(duplicateLookupCodeProduct).
			setProductRepository(productRepository).
			execute();
		verify();

		assertNotNull("Retrieved product is defined", apiProduct);
		assertTrue("Retrieved product has empty ID", EMPTY_UUID.equals(apiProduct.getId()));
		assertTrue("Retrieved product has status: LOOKUP CODE ALREADY EXISTS", (apiProduct.getApiRequestStatus() == ProductApiRequestStatus.LOOKUP_CODE_ALREADY_EXISTS));
	}
	
	@Test
	public void testExecuteSuccess() {
		Product toSaveApiProduct = ProductBuilder.buildApiProduct();
		UUID toSaveOriginalId = toSaveApiProduct.getId();
		ProductRepositoryInterface productRepository = this.createMock(ProductRepositoryInterface.class);
		
		expect(productRepository.byLookupCode(toSaveApiProduct.getLookupCode())).
			andReturn((org.npc.testmodel.models.Product)null);
		replay(productRepository);

		toSaveApiProduct = (new CreateProductCommand()).
			setApiProduct(toSaveApiProduct).
			setProductRepository(productRepository).
			execute();
		verify();
		
		this.successfullySavedProductId = toSaveApiProduct.getId();

		assertNotNull("Retrieved product is defined", toSaveApiProduct);
		assertTrue("Retrieved product has overwritten ID", !toSaveApiProduct.getId().equals(toSaveOriginalId));
		assertTrue("Retrieved product has status: OK", (toSaveApiProduct.getApiRequestStatus() == ProductApiRequestStatus.OK));
	}

	@After
	public void tearDown() {
		if (!EMPTY_UUID.equals(this.successfullySavedProductId)) {
			org.npc.testmodel.models.Product savedProduct = (new ProductRepository()).get(this.successfullySavedProductId);
			if (savedProduct != null) {
				savedProduct.delete();
			}
		}
	}

	private static final UUID EMPTY_UUID = new UUID(0, 0);
	
	private UUID successfullySavedProductId;
}
