package br.ufrj.cos.entity;

import javax.ejb.Local;

import br.ufrj.cos.pojo.Vote;

@Local
public interface VoteEntityBeanInterface {
	public void save(Vote vote);
}
