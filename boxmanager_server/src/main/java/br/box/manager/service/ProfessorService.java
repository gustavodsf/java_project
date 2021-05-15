package br.box.manager.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.box.manager.dao.ProfessorDAO;
import br.box.manager.model.Professor;

@Service("ProfessorService")
public class ProfessorService {

	@Autowired
	ProfessorDAO professorDAO;
	
	public Integer save(Professor professor) {
		return professorDAO.save(professor);
	}
	
	public ArrayList<Professor> retornaTodosProfessores(){
		return (ArrayList<Professor>) professorDAO.returnAll();
	}
	
	public void editaProfessor(Professor professor){
		professorDAO.edit(professor);
	}
	
	public boolean existeProfessor(String nomeProfessor){
		return professorDAO.returnProfessorByNome(nomeProfessor) == null ? false : true;
	}
}
