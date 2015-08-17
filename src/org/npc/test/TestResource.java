package org.npc.test;

import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;

import org.npc.test.commands.CreateProductCommand;
import org.npc.test.commands.ProductQuery;
import org.npc.test.commands.ProductsQuery;
import org.npc.testmodel.api.Product;
import org.npc.testmodel.api.ProductListing;
import org.npc.testmodel.repositories.ProductRepository;

@Path("/")
public class TestResource {
	@GET
	@Path("apiv0/products")
	@Produces(MediaType.APPLICATION_JSON)
	public ProductListing getProducts() {
		return (new ProductsQuery()).
			setProductRepository(new ProductRepository()).
			execute();
	}
	
	@GET
	@Path("apiv0/product/{productid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Product getProduct(@PathParam("productid") UUID productId) {
		return (new ProductQuery()).
			setProductId(productId).
			setProductRepository(new ProductRepository()).
			execute();
	}
	
	@PUT
	@Path("apiv0")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Product getTrackedChallengeShare(JAXBElement<Product> apiProduct) {
		return (new CreateProductCommand()).
			setApiProduct(apiProduct.getValue()).
			setProductRepository(new ProductRepository()).
			execute();
	}
	
	@GET
	@Path("test")
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {
		return "Successful test (.../test/)";
	}
}
