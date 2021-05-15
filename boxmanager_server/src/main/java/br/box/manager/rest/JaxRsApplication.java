package br.box.manager.rest;


import org.glassfish.jersey.server.ResourceConfig;

public class JaxRsApplication extends ResourceConfig {

	/**
	 * Register JAX-RS application components.
	 */
	public JaxRsApplication() {
		
        packages("br.box.manager.rest");

		// register features
		//EncodingFilter.enableFor(this, GZipEncoder.class);			
	}
}