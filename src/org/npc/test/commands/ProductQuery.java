package org.npc.test.commands;

import java.util.UUID;

import org.npc.test.commands.interfaces.ResultCommandInterface;
import org.npc.testmodel.api.Product;
import org.npc.testmodel.repositories.ProductRepository;

public class ProductQuery implements ResultCommandInterface<Product> {
	@Override
	public Product execute() {
		return new Product(
			(new ProductRepository()).get(this.productId)
		);
	}

	private UUID productId;
	
	public ProductQuery(UUID productId) {
		this.productId = productId;
	}
}
