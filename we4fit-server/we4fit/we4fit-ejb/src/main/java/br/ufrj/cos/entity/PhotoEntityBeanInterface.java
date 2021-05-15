package br.ufrj.cos.entity;

import java.util.List;

import javax.ejb.Local;

import br.ufrj.cos.pojo.Photo;
import br.ufrj.cos.pojo.UserWe4Fit;

@Local
public interface PhotoEntityBeanInterface {

	public void save(Photo file);

	public List<Photo> retrieveAllPhotoNotVotedByUSer(UserWe4Fit user);

	public Photo findPhotoById(Long id);

	public void setPhotoIsInappropriate(Long id);
	
}
