package br.ufrj.cos.ws;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response;

@Path("/dish/image")
public class sendPhotoDish {
	private final String UPLOADED_FILE_PATH = "/home/fnx/we4fit/photo/forest.jpg";
	
	@GET
	@Path("/get")
	@Produces("image/png")
	public Response getFile() {
		
		File file = new File(UPLOADED_FILE_PATH);
		ResponseBuilder responseBuilder = Response.ok((Object) file);
		responseBuilder.header("Content-Disposition", "attachment; filename=image_from_server.jgp");
		return responseBuilder.build();
	}
}
