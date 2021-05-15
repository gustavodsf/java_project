package br.box.manager.util;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Menssagem {
	
	 public static void addMensagemSucesso(String tagMensagem){
		  FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, getMensagemBundle(tagMensagem),  null);
	      FacesContext.getCurrentInstance().addMessage(null, message);
	 }
	 
	 public static void addMensageErro(String tagMensagem){
		  FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, getMensagemBundle(tagMensagem),  null);
	      FacesContext.getCurrentInstance().addMessage(null, message);
	 }
	 
	 private static String getMensagemBundle(String tagMensagem){
		 FacesContext facesContext = FacesContext.getCurrentInstance();
		 String messageBundleName = facesContext.getApplication().getMessageBundle();
		 Locale locale = facesContext.getViewRoot().getLocale();
		 ResourceBundle bundle = ResourceBundle.getBundle(messageBundleName, locale);
		 return bundle.getString(tagMensagem);
	 }
}
