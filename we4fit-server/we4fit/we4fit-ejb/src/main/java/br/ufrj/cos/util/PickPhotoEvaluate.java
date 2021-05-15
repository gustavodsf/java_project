package br.ufrj.cos.util;

import java.util.List;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufrj.cos.entity.PhotoEntityBeanInterface;
import br.ufrj.cos.pojo.Photo;
import br.ufrj.cos.pojo.UserWe4Fit;

@Stateless(name="pickPhotoEvaluate")
public class PickPhotoEvaluate {
	
	@EJB(name="photoEntityBean")
	PhotoEntityBeanInterface photoEntityBean;
	
	public  Photo pickOne(UserWe4Fit user){
			
		List<Photo> photos = photoEntityBean.retrieveAllPhotoNotVotedByUSer(user);
		if(photos.size() != 0){
			Random r = new Random(); 
			int photoDraw = r.nextInt(photos.size());
			return photos.get(photoDraw);
		}
		return new Photo();
	}
	
}
