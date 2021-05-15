package br.ufrj.cos.ws;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.ufrj.cos.entity.GroupEntityBeanInterface;
import br.ufrj.cos.entity.UserEntityBeanInterface;
import br.ufrj.cos.pojo.GroupWe4Fit;
import br.ufrj.cos.pojo.UserWe4Fit;

@Path("/group")
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)   	
public class GroupWS {
	
	@EJB(name="groupEntityBean")
	private GroupEntityBeanInterface groupEntityBean;
	
	@EJB(name="userEntityBean")
	private UserEntityBeanInterface userEntityBean;
	
	
	@GET
	@Path("/create/{name}/{facebookId}")
	public Response create(@PathParam("name") String name, @PathParam("facebookId") String facebookId ) {
		
 		if(groupEntityBean.findGroupByName(name) == null){
			GroupWe4Fit group = new GroupWe4Fit();
			UserWe4Fit user = userEntityBean.findUserByFacebookId(facebookId);
			group.setOwner(user);
			group.setName(name);		
			groupEntityBean.save(group);
			return Response.status(200).entity("OK").build();
		}else{
			return Response.status(200).entity("DUPLICADO").build();
		}
	}
	
	@GET
	@Path("/add_user/{groupName}/{facebookId}")
	public Response addUser(@PathParam("groupName") String groupName,@PathParam("facebookId") String facebookId ) {
		
		UserWe4Fit user = userEntityBean.findUserByFacebookId(facebookId);
		GroupWe4Fit group = groupEntityBean.findGroupByName(groupName);
		
		if(user != null && group != null){
			user.setGroup(group);
		}
		
		userEntityBean.editUser(user);
		return Response.status(200).entity("OK").build();
	}
	
	@GET
	@Path("/exist/{name}")
	public Response existGroup(@PathParam("name") String name ) {
		
		GroupWe4Fit group = groupEntityBean.findGroupByName(name);
		if(group != null){
			return Response.status(200).entity("EXISTE").build();
		}else{
			return Response.status(200).entity("NAO_EXISTE").build();
		}
		
	}
	
	@GET
	@Path("/get/{groupId}")
	@Produces("application/json")
	public GroupWe4Fit getGroup(@PathParam("groupId") String groupId ) {
		return groupEntityBean.findGroupById(Long.valueOf(groupId));
	}
	
}
