package com.synf.user.usermanagement.model;

import java.io.Serializable;

public class ImageRequestDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String hash;
	private String userName;
	private String password;
	
	public ImageRequestDTO() {
		super();
	}

	public ImageRequestDTO(String hash, String userName, String password) {
		super();
		this.hash = hash;
		this.userName = userName;
		this.password = password;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "ImageRequestDTO [hash=" + hash + ", userName=" + userName + ", password=" + password + "]";
	}

}
