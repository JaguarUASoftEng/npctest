package org.npc.test.commands;

import java.util.stream.Collectors;

import org.npc.test.commands.interfaces.ResultCommandInterface;
import org.npc.testmodel.api.Product;
import org.npc.testmodel.api.ProductListing;
import org.npc.testmodel.repositories.ProductRepository;

public class ProductsQuery implements ResultCommandInterface<ProductListing> {
	@Override
	public ProductListing execute() {
		return (new ProductListing()).
			setProducts(
					(new ProductRepository()).all().stream().map(mp -> (new Product(mp))).collect(Collectors.toList()
			)
		);
	}
}
