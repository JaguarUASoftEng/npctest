package org.npc.test;

import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.npc.test.api.Product;
import org.npc.test.api.ProductListing;
import org.npc.test.commands.ProductQuery;
import org.npc.test.commands.ProductsQuery;

@Path("/")
public class TestResource {
	@GET
	@Path("apiv0/products")
	@Produces(MediaType.APPLICATION_JSON)
	public ProductListing getProducts() {
		return (new ProductsQuery()).execute();
	}
	
	@GET
	@Path("apiv0/product/{productid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Product getProduct(@PathParam("productid") UUID productId) {
		return (new ProductQuery(productId)).execute();
	}
	
	@GET
	@Path("test")
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {
		return "Successful test (.../test/)";
	}
}
