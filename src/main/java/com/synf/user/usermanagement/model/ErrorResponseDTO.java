package com.synf.user.usermanagement.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.http.HttpStatus;

public class ErrorResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date time;
	private HttpStatus httpStatus;
	private String code;
	private String message;
	private String path;

	public ErrorResponseDTO(HttpStatus httpStatus, String message, String path) {
		super();
		this.time = new Date();
		this.httpStatus = httpStatus;
		this.message = message;
		this.path = path;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "ErrorResponseDTO [time=" + time + ", httpStatus=" + httpStatus + ", code=" + code + ", message="
				+ message + ", path=" + path + "]";
	}
}