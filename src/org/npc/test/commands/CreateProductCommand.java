package org.npc.test.commands;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.npc.test.commands.interfaces.ResultCommandInterface;
import org.npc.testmodel.api.Product;
import org.npc.testmodel.enums.ProductApiRequestStatus;
import org.npc.testmodel.repositories.ProductRepository;

public class CreateProductCommand implements ResultCommandInterface<Product> {
	@Override
	public Product execute() {
		if (StringUtils.isBlank(this.apiProduct.getLookupCode())) {
			return (new Product()).setApiRequestStatus(ProductApiRequestStatus.INVALID_INPUT);
		}
		
		org.npc.testmodel.models.Product modelProduct = (new ProductRepository()).byLookupCode(this.apiProduct.getLookupCode());
		if (modelProduct != null) {
			return (new Product()).setApiRequestStatus(ProductApiRequestStatus.LOOKUP_CODE_ALREADY_EXISTS);
		}
		
		modelProduct = new org.npc.testmodel.models.Product(UUID.randomUUID());
		this.apiProduct = modelProduct.synchronize(this.apiProduct);
		modelProduct.save();

		return this.apiProduct;
	}

	private Product apiProduct;
	
	public CreateProductCommand(Product apiProduct) {
		this.apiProduct = apiProduct;
	}
}
