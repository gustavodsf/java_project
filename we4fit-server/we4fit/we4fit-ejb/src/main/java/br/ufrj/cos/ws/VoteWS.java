package br.ufrj.cos.ws;

import java.util.GregorianCalendar;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import br.ufrj.cos.entity.PhotoEntityBeanInterface;
import br.ufrj.cos.entity.UserEntityBeanInterface;
import br.ufrj.cos.entity.VoteEntityBeanInterface;
import br.ufrj.cos.pojo.Photo;
import br.ufrj.cos.pojo.UserWe4Fit;
import br.ufrj.cos.pojo.Vote;

@Path("/vote")
@Stateless
public class VoteWS {
	
	@EJB(name="userEntityBean")
	UserEntityBeanInterface userEntityBean;
	
	@EJB(name="photoEntityBean")
	PhotoEntityBeanInterface photoEntityBean;
	
	@EJB(name="voteEntityBean")
	VoteEntityBeanInterface voteEntityBean;
	
	@GET
	@Path("/grade/{facebookId}/{photoId}/{grade}")
	public Response sendVote(@PathParam("facebookId") String facebookId, @PathParam("photoId") String photoId,@PathParam("grade") String grade) {
		
		UserWe4Fit user = userEntityBean.findUserByFacebookId(facebookId);
		Photo photo = photoEntityBean.findPhotoById(Long.valueOf(photoId));
		
		Vote vote = new Vote();
		vote.setDate(GregorianCalendar.getInstance());
		vote.setGrade(grade);
		vote.setUserWe4Fit(user);
		vote.setPhoto(photo);
		
		voteEntityBean.save(vote);
		return Response.status(200).entity("OK").build();
	}
}
