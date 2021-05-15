package br.ufrj.cos.pojo;

import android.graphics.Bitmap;

public class User {
	private static User user = null;
	
	private String id;
	private String name;
	private String birthday;
	private Bitmap facebookPhoto;
	private String email;
	private String firstName;
	private String locale;
	private String lastName;
	private String gender;
	private String weight;
	private String height;
	
	private User(){}
	
	public static User getInstace(){
		if(user == null){
			user = new User();
		}
		return user;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Bitmap getFacebookPhoto() {
		return facebookPhoto;
	}

	public void setFacebookPhoto(Bitmap facebookPhoto) {
		this.facebookPhoto = facebookPhoto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}
}
