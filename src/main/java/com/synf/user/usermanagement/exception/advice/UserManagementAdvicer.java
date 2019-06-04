package com.synf.user.usermanagement.exception.advice;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.synf.user.usermanagement.exception.BadRequestException;
import com.synf.user.usermanagement.exception.DataNotFoundException;
import com.synf.user.usermanagement.exception.MethodFailureException;
import com.synf.user.usermanagement.exception.UnAuthorizedException;
import com.synf.user.usermanagement.model.ErrorResponseDTO;

@ControllerAdvice
public class UserManagementAdvicer {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BindException.class)
	@ResponseBody
	public List<ErrorResponseDTO> handleBindException(HttpServletRequest req, BindException ex){
		
		List<ObjectError> errors = ex.getBindingResult().getAllErrors();
		List<ErrorResponseDTO> errorResponseList = new ArrayList<>();
		for (ObjectError oe : errors) {
			ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(HttpStatus.BAD_REQUEST, oe.getDefaultMessage(), req.getRequestURI());
			if(oe instanceof FieldError) {
				FieldError fe = (FieldError) oe;
				errorResponseDTO.setMessage(fe.getField()+" "+fe.getDefaultMessage());
			}
			errorResponseList.add(errorResponseDTO);
		}
		return errorResponseList;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BadRequestException.class)
	@ResponseBody
	public List<ErrorResponseDTO> handleBadRequestException(HttpServletRequest req, BadRequestException ex){
		
		List<ErrorResponseDTO> errorResponseList = new ArrayList<>();
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(HttpStatus.BAD_REQUEST, ex.getMessage(), req.getRequestURI());
		errorResponseList.add(errorResponseDTO);
		
		return errorResponseList;
	}
	
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(UnAuthorizedException.class)
	@ResponseBody
	public List<ErrorResponseDTO> handleUnAuthorizedException(HttpServletRequest req, UnAuthorizedException ex){
		
		List<ErrorResponseDTO> errorResponseList = new ArrayList<>();
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(HttpStatus.UNAUTHORIZED, ex.getMessage(), req.getRequestURI());
		errorResponseList.add(errorResponseDTO);
		
		return errorResponseList;
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(DataNotFoundException.class)
	@ResponseBody
	public List<ErrorResponseDTO> handleDataNotFoundException(HttpServletRequest req, DataNotFoundException ex){
		
		List<ErrorResponseDTO> errorResponseList = new ArrayList<>();
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(HttpStatus.NOT_FOUND, ex.getMessage(), req.getRequestURI());
		errorResponseList.add(errorResponseDTO);
		
		return errorResponseList;
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(MethodFailureException.class)
	@ResponseBody
	public List<ErrorResponseDTO> handleMethodFailureException(HttpServletRequest req, MethodFailureException ex){
		
		List<ErrorResponseDTO> errorResponseList = new ArrayList<>();
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), req.getRequestURI());
		errorResponseList.add(errorResponseDTO);
		
		return errorResponseList;
	}

}
