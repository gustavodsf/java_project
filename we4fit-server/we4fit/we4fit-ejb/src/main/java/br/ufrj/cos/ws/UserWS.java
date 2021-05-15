package br.ufrj.cos.ws;

import java.util.GregorianCalendar;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.ufrj.cos.entity.UserEntityBeanInterface;
import br.ufrj.cos.pojo.UserWe4Fit;
import br.ufrj.cos.pojo.Weight;


@Path("/user")
@Stateless
public class UserWS {
	
	@EJB(name="userEntityBean")
	UserEntityBeanInterface userEntityBean;
	
	@GET
	@Path("/exist/{id}")
	public Response existUser(@PathParam("id") String id) {
		UserWe4Fit user = userEntityBean.findUserByFacebookId(id);
		
		String result = null;
		if(user == null){
			result =  "NAO_EXISTE_USUARIO";
		}
		else{
			result =  "EXISTE_USUARIO";
		}
		
		return Response.status(200).entity(result).build();
	}
	
	@GET
	@Path("/edit/{id}/{weight}/{height}")
	public Response editUser(@PathParam("id") String id,@PathParam("weight") String weight,@PathParam("height") String height) {
		UserWe4Fit user = userEntityBean.findUserByFacebookId(id);
		if(user != null){
			user.setHeight(height);
			
			Weight newWeight = new Weight();
			newWeight.setInsertionDate(GregorianCalendar.getInstance());
			newWeight.setUser(user);
			newWeight.setWeight(weight);
			
			user.addWeights(newWeight);
			
			userEntityBean.editUser(user);
			return Response.status(200).entity("OK").build();
		}
		return Response.status(200).entity("NAO_EXISTE").build();
	}
	
	@GET
	@Path("/add/{id}")
	public Response addUser(@PathParam("id") String id) {
		if(userEntityBean.findUserByFacebookId(id) == null){
			UserWe4Fit user = new UserWe4Fit ();
			user.setIdFacebook(id);
			user.setAdmissionDate(GregorianCalendar.getInstance());
			userEntityBean.save(user);
			return Response.status(200).entity("OK").build();
		}
		return Response.status(200).entity("DUPLICADO").build();
	}
	
	@GET
	@Path("/get/{id}")
	@Produces("application/json")
	public UserWe4Fit getUser(@PathParam("id") String id){
		return userEntityBean.findUserByFacebookId(id);
	}
	
	
}
