package com.synf.user.usermanagement.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(Include.NON_NULL)
public class UserResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer userId;

	private String userName;

	private String password;

	private String email;

	private List<UserImageDTO> userImages;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<UserImageDTO> getUserImages() {
		return userImages;
	}

	public void setUserImages(List<UserImageDTO> userImages) {
		this.userImages = userImages;
	}

	@Override
	public String toString() {
		return "UserResponseDTO [userId=" + userId + ", userName=" + userName + ", password=" + password + ", email="
				+ email + ", userImages=" + userImages + "]";
	}

}
