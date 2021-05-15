package br.box.manager.view;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.box.manager.model.Professor;
import br.box.manager.service.ProfessorService;
import br.box.manager.util.Menssagem;


@ManagedBean(name = "professorManagedBean", eager = true)
@ViewScoped
public class ProfessorManagedBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{ProfessorService}")
	ProfessorService professorService;
	
	private Professor professor;
	
	private String nomeProfessor;
	private String valorProfessor;
	private ArrayList<Professor> professorList;
	private boolean edicao = false;
	
	@PostConstruct
	public void popula(){
		professorList = professorService.retornaTodosProfessores();
		professor = new Professor();
	}
	
	public void salvaProfessor(){
		professor.setNome(nomeProfessor);
		professor.setHoraAula(valorProfessor);
		boolean erro = valida(professor);
		if(!erro){
			if(!edicao){
				professorService.save(professor);
				professorList.add(professor);
				Menssagem.addMensagemSucesso("professorCadastradoSucesso");
			}else{
				professorService.editaProfessor(professor);
				professorList.clear();
				professorList = professorService.retornaTodosProfessores();
				Menssagem.addMensagemSucesso("professorEditadoSucesso");
			}
		}
		nomeProfessor = "";
		valorProfessor="";
		edicao = false;
	}
	
	public void selecionado(Professor professorSelecionado){
		nomeProfessor = professorSelecionado.getNome();
		valorProfessor =  professorSelecionado.getHoraAula();
		professor = professorSelecionado;
		edicao = true;
	}
	
	private boolean valida(Professor professor){
		boolean erro = false;
		if(nomeProfessor == null || nomeProfessor.equals("")){
			Menssagem.addMensageErro("professorErroFaltaNome");
			erro = true;
		}
		if(valorProfessor == null || valorProfessor.equals("")){
			Menssagem.addMensageErro("professorErroFaltaValor");
			erro = true;
		}
		if(nomeProfessor != null && !nomeProfessor.equals("") && professorService.existeProfessor(nomeProfessor)){
			Menssagem.addMensageErro("professorJaCadastrado");
			erro = true;
		}
		
		return erro;
	}
	
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	public String getNomeProfessor() {
		return nomeProfessor;
	}
	public void setNomeProfessor(String nomeProfessor) {
		this.nomeProfessor = nomeProfessor;
	}
	public String getValorProfessor() {
		return valorProfessor;
	}
	public void setValorProfessor(String valorProfessor) {
		this.valorProfessor = valorProfessor;
	}

	public ArrayList<Professor> getProfessorList() {
		return professorList;
	}

	public void setProfessorList(ArrayList<Professor> professorList) {
		this.professorList = professorList;
	}

	public ProfessorService getProfessorService() {
		return professorService;
	}

	public void setProfessorService(ProfessorService professorService) {
		this.professorService = professorService;
	}
}
