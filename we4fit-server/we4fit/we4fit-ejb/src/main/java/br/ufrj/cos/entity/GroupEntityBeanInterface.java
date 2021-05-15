package br.ufrj.cos.entity;

import java.util.List;

import javax.ejb.Local;

import br.ufrj.cos.pojo.GroupWe4Fit;

@Local
public interface GroupEntityBeanInterface {
	public void save(GroupWe4Fit group);
	public void edit(GroupWe4Fit group);
	public void delete(GroupWe4Fit group);
	
	public List<GroupWe4Fit> retieveAllByLikeNome(String name);
	public List<GroupWe4Fit> retieveAll();
	public GroupWe4Fit findGroupById(long id);
	public GroupWe4Fit findGroupByName(String name);
}
