package br.ufrj.cos.entity;

import javax.ejb.Local;

import br.ufrj.cos.pojo.UserWe4Fit;

@Local
public interface UserEntityBeanInterface {
	
	public void save(UserWe4Fit user);
	public UserWe4Fit findUserByFacebookId(String idFacebook);
	public void editUser(UserWe4Fit user);
}
