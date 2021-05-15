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
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import br.ufrj.cos.entity.PhotoEntityBeanInterface;
import br.ufrj.cos.entity.UserEntityBeanInterface;
import br.ufrj.cos.pojo.Photo;
import br.ufrj.cos.util.PickPhotoEvaluate;

@Path("/dish/image")
@Stateless
public class PhotoWS {
	
	@EJB(name="photoEntityBean")
	PhotoEntityBeanInterface photoEntityBean;
	
	@EJB(name="userEntityBean")
	UserEntityBeanInterface userEntityBean;
	
	@EJB(name="pickPhotoEvaluate")
	PickPhotoEvaluate pickPhoto;
	
	Photo photo;
	
	private final String UPLOADED_FILE_PATH = "C:\\Users\\Gustavo\\We4fit\\photo\\";
	
	@GET
	@Path("/inappropriate/{photoId}")
	public Response setInappropriate(@PathParam("photoId") String photoId ) {
		photoEntityBean.setPhotoIsInappropriate(Long.valueOf(photoId));
		return Response.status(200).entity("OK").build();
	}
	
	
	@GET
	@Path("/get/{userFaceId}")
	@Produces("image/png")
	public Response getFile(@PathParam("userFaceId") String facebookId ) {
		Photo photo = pickPhoto.pickOne(userEntityBean.findUserByFacebookId(facebookId));
		File file = new File(UPLOADED_FILE_PATH+photo.getPath());
		ResponseBuilder responseBuilder = Response.ok((Object) file);
		responseBuilder.header("Content-Disposition", "attachment; filename="+photo.getId());
		responseBuilder.header("teste", "teste");
		return responseBuilder.build();
	}
	
	@GET
	@Path("/exist/{userFaceId}")
	public Response existPhoto(@PathParam("userFaceId") String facebookId ) {
		Photo photo = pickPhoto.pickOne(userEntityBean.findUserByFacebookId(facebookId));
		if(photo.getPath() != null){
			return Response.status(200).entity(photo.getId()).build();
		}else{
			return Response.status(200).entity("NAO_TEM").build();
		}
	}

	@POST
	@Path("/send")
	@Consumes("multipart/form-data")
	public Response uploadPhoto(MultipartFormDataInput input) {
		
		photo = new Photo();
		String fileName = "";

		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		List<InputPart> inputParts = uploadForm.get("uploadedFile");

		for (InputPart inputPart : inputParts) {

			try {

				MultivaluedMap<String, String> header = inputPart.getHeaders();
				fileName = getFileName(header);
				photo.setPath(fileName);
				InputStream inputStream = inputPart.getBody(InputStream.class,null);
				fileName = UPLOADED_FILE_PATH + fileName;
				writeFile(inputStream, fileName);
				
				

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		try {
			String usrId    = uploadForm.get("userId").get(0).getBodyAsString(); 
			photo.setUser(userEntityBean.findUserByFacebookId(usrId));
			photo.setUserGrade(uploadForm.get("userGrade").get(0).getBodyAsString());
			photo.setInThPoll(true);
			photo.setInappropriate(false);
			photoEntityBean.save(photo);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Response.status(200).entity("uploadFile is called, Uploaded file name : " + fileName).build();
	}

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
