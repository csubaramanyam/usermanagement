package com.synf.user.usermanagement.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.synf.user.usermanagement.exception.MethodFailureException;

@Service
public class ImgurService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImgurService.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${imgur.service.url}")
	private String imgurUrl;
	
	@Value("${imgur.clientId}")
	private String clientId;

	/**
	 * This method used to upload image using imgur API
	 * 
	 * @param data holds the encoded image byte data format
	 */
	public String upload(final String data) {
	
		LOGGER.info("Entered into upload method");
		
		String responseBody = null;
		
		try {
		
			restTemplate.getMessageConverters().add(new FormHttpMessageConverter() );
			
			//setting headers
			final HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			headers.add("Authorization", "Client-ID " +clientId);
	
			//setting up form data in key value pair format
			final MultiValueMap<String, String> map =  new LinkedMultiValueMap<>();
			map.add("image",data);
	
			HttpEntity<MultiValueMap<String, String>> entity =  new HttpEntity<>(map, headers);
			
			LOGGER.debug("making call to imgur upload image API :{}", imgurUrl+"/image");
			
			ResponseEntity<String> response = restTemplate.exchange(imgurUrl+"/image", HttpMethod.POST, entity, String.class);
			
			responseBody = response.getBody();
			
			LOGGER.info("Received response from service:{}", responseBody);
		
		} catch(HttpClientErrorException e) {
			LOGGER.error("HttpClientErrorException occured while uploading image to imgur with error message:{}", e.getResponseBodyAsString(), e);
			String errorMsg = e.getResponseBodyAsString();
			throw new MethodFailureException("Exception occured while uploading image to imgur with error message:"+ errorMsg);
		} catch(HttpServerErrorException e) {
			LOGGER.error("HttpServerErrorException occured while uploading image to imgur with error message:{}", e.getResponseBodyAsString(), e);
			String errorMsg = e.getResponseBodyAsString();
			throw new MethodFailureException("Excdption occured while uploading image to imgur with error message:"+ errorMsg);
		} catch(Exception e) {
			LOGGER.error("Exception occured while uploading image to imgur with error message:{}", e.getMessage(), e);
			String errorMsg = e.getMessage();
			throw new MethodFailureException("Excdption occured while uploading image to imgur with error message:"+ errorMsg);
		}
		
		LOGGER.info("Exiting from upload method with response data :{}", responseBody);
		
		return responseBody;
	}

	/**
	 * This method used to delete image using imgur API
	 * 
	 * @param deleteHash holds the deletehash value
	 */
	public String delete(final String deleteHash) {
	
		LOGGER.info("Entered into delete method");
		
		String responseBody = null;
		
		try {
		
			//setting headers
			final HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("Authorization", "Client-ID " +clientId);
	
			HttpEntity<String> entity =  new HttpEntity<>(headers);
			
			LOGGER.debug("making call to imgur delete image API :{}", imgurUrl+"/image/"+deleteHash);
			
			ResponseEntity<String> response = restTemplate.exchange(imgurUrl+"/image/"+deleteHash, HttpMethod.DELETE, entity, String.class);
			
			responseBody = response.getBody();
			
			LOGGER.info("Received response from service:{}", responseBody);
		
		} catch(HttpClientErrorException e) {
			LOGGER.error("HttpClientErrorException occured while deleting image from imgur with error message:{}", e.getResponseBodyAsString(), e);
			String errorMsg = e.getResponseBodyAsString();
			throw new MethodFailureException("Exception occured while deleting image from imgur with error message:"+ errorMsg);
		} catch(HttpServerErrorException e) {
			LOGGER.error("HttpServerErrorException occured while deleting image from imgur with error message:{}", e.getResponseBodyAsString(), e);
			String errorMsg = e.getResponseBodyAsString();
			throw new MethodFailureException("Excdption occured while deleting image from imgur with error message:"+ errorMsg);
		} catch(Exception e) {
			LOGGER.error("Exception occured while deleting image from imgur with error message:{}", e.getMessage(), e);
			String errorMsg = e.getMessage();
			throw new MethodFailureException("Excdption occured while deleting image from imgur with error message:"+ errorMsg);
		}
		
		LOGGER.info("Exiting from delete with response data :{}", responseBody);
		
		return responseBody;
	}
	
	/**
	 * This method used to view image using imgur API
	 * 
	 * @param deleteHash holds the deletehash value
	 */
	public String getImageByImageId(final String imageId) {
	
		LOGGER.info("Entered into view method");
		
		String responseBody = null;
		
		try {
		
			//setting headers
			final HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("Authorization", "Client-ID " +clientId);
	
			HttpEntity<String> entity =  new HttpEntity<>(headers);
			
			LOGGER.debug("making call to imgur view image API :{}", imgurUrl+"/image/"+imageId);
			
			ResponseEntity<String> response = restTemplate.exchange(imgurUrl+"/image/"+imageId, HttpMethod.GET, entity, String.class);
			
			responseBody = response.getBody();
			
			LOGGER.info("Received response from service:{}", responseBody);
		
		} catch(HttpClientErrorException e) {
			LOGGER.error("HttpClientErrorException occured while view image from imgur with error message:{}", e.getResponseBodyAsString(), e);
			String errorMsg = e.getResponseBodyAsString();
			throw new MethodFailureException("Exception occured while view image from imgur with error message:"+ errorMsg);
		} catch(HttpServerErrorException e) {
			LOGGER.error("HttpServerErrorException occured while view image from imgur with error message:{}", e.getResponseBodyAsString(), e);
			String errorMsg = e.getResponseBodyAsString();
			throw new MethodFailureException("Excdption occured while view image from imgur with error message:"+ errorMsg);
		} catch(Exception e) {
			LOGGER.error("Exception occured while view image from imgur with error message:{}", e.getMessage(), e);
			String errorMsg = e.getMessage();
			throw new MethodFailureException("Excdption occured while view image from imgur with error message:"+ errorMsg);
		}
		
		LOGGER.info("Exiting from view with response data :{}", responseBody);
		
		return responseBody;
	}

}
