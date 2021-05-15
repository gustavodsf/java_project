package br.ufrj.cos.entity;

import javax.ejb.Local;

import br.ufrj.cos.pojo.Photo;

@Local
public interface PhotoMetadaEntityBeanInterface {

	public void save(Photo file);

}
