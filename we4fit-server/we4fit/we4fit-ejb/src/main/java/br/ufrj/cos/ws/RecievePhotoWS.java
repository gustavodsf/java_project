package br.ufrj.cos.ws;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import br.ufrj.cos.entity.PhotoEntityBeanInterface;
import br.ufrj.cos.pojo.Photo;

@Path("/upload")
@Stateless
public class RecievePhotoWS {
	
	@EJB(name="photoMetadaEntityBean")
	PhotoEntityBeanInterface photoMetadaEntityBean;
	
	private final String UPLOADED_FILE_PATH = "C:\\Users\\Gustavo\\We4fit\\photo\\";

	@POST
	@Path("/dish")
	@Consumes("multipart/form-data")
	public Response uploadPhoto(MultipartFormDataInput input) {

		String fileName = "";

		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		List<InputPart> inputParts = uploadForm.get("uploadedFile");

		for (InputPart inputPart : inputParts) {

			try {

				MultivaluedMap<String, String> header = inputPart.getHeaders();
				fileName = getFileName(header);

				// convert the uploaded file to inputstream
				InputStream inputStream = inputPart.getBody(InputStream.class,null);

				// constructs upload file path
				fileName = UPLOADED_FILE_PATH + fileName;
				//Photo photoMetada = new Photo(fileName);
				//photoMetadaEntityBean.save(photoMetada);

				writeFile(inputStream, fileName);

				System.out.println("Done");

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return Response.status(200).entity("uploadFile is called, Uploaded file name : " + fileName).build();
	}

	/**
	 * header sample { Content-Type=[image/png], Content-Disposition=[form-data;
	 * name="file"; filename="filename.extension"] }
	 **/
	// get uploaded filename, is there a easy way in RESTEasy?
	private String getFileName(MultivaluedMap<String, String> header) {

		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");

		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {

				String[] name = filename.split("=");

				String finalFileName = name[1].trim().replaceAll("\"", "");
				return finalFileName;
			}
		}
		return "unknown";
	}

	// save to somewhere
	private void writeFile(InputStream uploadInputStream, String filename) throws IOException {
		try{
			OutputStream outputStream = new FileOutputStream(new File(filename));
			int read = 0;
			byte[] bytes = new byte[1024];
			
			while((read = uploadInputStream.read(bytes)) != -1){
				outputStream.write(bytes, 0, read);
			}
			outputStream.flush();
			outputStream.close();
		}catch(IOException io){
			io.printStackTrace();
		}
	}
}