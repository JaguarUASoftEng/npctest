package org.npc.test;

import org.glassfish.jersey.server.ResourceConfig;

public class TestApplication extends ResourceConfig {
	public TestApplication() {
		this.register(CorsResponseFilter.class);
	}
}
