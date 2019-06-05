package com.synf.user.usermanagement.service;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.synf.user.usermanagement.exception.MethodFailureException;

/**
 * This class contains Mockito Junit test cases for ImgurService.java file
 * 
 * @author Subbu
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ImgurServiceTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ImgurServiceTest.class);

	@InjectMocks
	private ImgurService imgurService;
	
	@Mock
	private RestTemplate restTemplate;

	@Test
	public void testUpload() {
		
		final String responseBody = "{\"data\":{\"id\":\"7GyB3Wo\",\"title\":null,\"description\":null,\"datetime\":1559536419,\"type\":\"image\\/png\",\"animated\":false,\"width\":542,\"height\":310,\"size\":8754,\"views\":0,\"bandwidth\":0,\"vote\":null,\"favorite\":false,\"nsfw\":null,\"section\":null,\"account_url\":null,\"account_id\":0,\"is_ad\":false,\"in_most_viral\":false,\"has_sound\":false,\"tags\":[],\"ad_type\":0,\"ad_url\":\"\",\"edited\":\"0\",\"in_gallery\":false,\"deletehash\":\"vKfACtjntqiRmsR\",\"name\":\"\",\"link\":\"https:\\/\\/i.imgur.com\\/7GyB3Wo.png\"},\"success\":true,\"status\":200}";
		
		ResponseEntity<String> responseEntity = new ResponseEntity<>(responseBody, HttpStatus.ACCEPTED);

		when(restTemplate.exchange(anyString(), Mockito.any(HttpMethod.class),
				Matchers.<HttpEntity<?>>any(), Matchers.<Class<String>>any())).thenReturn(responseEntity);
		
		imgurService.upload("ahbkssknsvnlsjkvldkfvldfvldk");
		
	}
	
	@Test(expected = MethodFailureException.class)
	public void testUploadWhenServiceThrowsHttpClientError() {
		
		when(restTemplate.exchange(anyString(), Mockito.any(HttpMethod.class),
				Matchers.<HttpEntity<?>>any(), Matchers.<Class<String>>any())).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));
		
		imgurService.upload("ahbkssknsvnlsjkvldkfvldfvldk");
		
	}

	@Test(expected = MethodFailureException.class)
	public void testUploadWhenServiceThrowsHttpServerError() {
		
		when(restTemplate.exchange(anyString(), Mockito.any(HttpMethod.class),
				Matchers.<HttpEntity<?>>any(), Matchers.<Class<String>>any())).thenThrow(new HttpServerErrorException(HttpStatus.BAD_GATEWAY));
		
		imgurService.upload("ahbkssknsvnlsjkvldkfvldfvldk");
		
	}
	
	@Test(expected = MethodFailureException.class)
	public void testUploadWhenServiceThrowsException() {
		
		when(restTemplate.exchange(anyString(), Mockito.any(HttpMethod.class),
				Matchers.<HttpEntity<?>>any(), Matchers.<Class<String>>any())).thenThrow(new RuntimeException());
		
		imgurService.upload("ahbkssknsvnlsjkvldkfvldfvldk");
		
	}
	
	@Test
	public void testDelete() {
		
		final String responseBody = "{\"data\":true,\"success\":true,\"status\":200}";
		
		ResponseEntity<String> responseEntity = new ResponseEntity<>(responseBody, HttpStatus.ACCEPTED);

		when(restTemplate.exchange(anyString(), Mockito.any(HttpMethod.class),
				Matchers.<HttpEntity<?>>any(), Matchers.<Class<String>>any())).thenReturn(responseEntity);
		
		imgurService.delete("gLFMAGvQCPMFWw0");
		
	}

	@Test(expected = MethodFailureException.class)
	public void testDeleteWhenServiceThrowsHttpClientError() {
		
		when(restTemplate.exchange(anyString(), Mockito.any(HttpMethod.class),
				Matchers.<HttpEntity<?>>any(), Matchers.<Class<String>>any())).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));
		
		imgurService.delete("gLFMAGvQCPMFWw0");
		
	}

	@Test(expected = MethodFailureException.class)
	public void testDeleteWhenServiceThrowsHttpServerError() {
		
		when(restTemplate.exchange(anyString(), Mockito.any(HttpMethod.class),
				Matchers.<HttpEntity<?>>any(), Matchers.<Class<String>>any())).thenThrow(new HttpServerErrorException(HttpStatus.BAD_GATEWAY));
		
		imgurService.delete("gLFMAGvQCPMFWw0");
		
	}
	
	@Test(expected = MethodFailureException.class)
	public void testDeleteWhenServiceThrowsException() {
		
		when(restTemplate.exchange(anyString(), Mockito.any(HttpMethod.class),
				Matchers.<HttpEntity<?>>any(), Matchers.<Class<String>>any())).thenThrow(new RuntimeException());
		
		imgurService.delete("gLFMAGvQCPMFWw0");
		
	}

	@Test
	public void testView() {
		
		final String responseBody = "{\"data\":{\"id\":\"7GyB3Wo\",\"title\":null,\"description\":null,\"datetime\":1559536419,\"type\":\"image\\/png\",\"animated\":false,\"width\":542,\"height\":310,\"size\":8754,\"views\":0,\"bandwidth\":0,\"vote\":null,\"favorite\":false,\"nsfw\":null,\"section\":null,\"account_url\":null,\"account_id\":0,\"is_ad\":false,\"in_most_viral\":false,\"has_sound\":false,\"tags\":[],\"ad_type\":0,\"ad_url\":\"\",\"edited\":\"0\",\"in_gallery\":false,\"deletehash\":\"vKfACtjntqiRmsR\",\"name\":\"\",\"link\":\"https:\\/\\/i.imgur.com\\/7GyB3Wo.png\"},\"success\":true,\"status\":200}";
		
		ResponseEntity<String> responseEntity = new ResponseEntity<>(responseBody, HttpStatus.ACCEPTED);

		when(restTemplate.exchange(anyString(), Mockito.any(HttpMethod.class),
				Matchers.<HttpEntity<?>>any(), Matchers.<Class<String>>any())).thenReturn(responseEntity);
		
		imgurService.getImageByImageId("JKrCLbT");
		
	}

	@Test(expected = MethodFailureException.class)
	public void testViewWhenServiceThrowsHttpClientError() {
		
		when(restTemplate.exchange(anyString(), Mockito.any(HttpMethod.class),
				Matchers.<HttpEntity<?>>any(), Matchers.<Class<String>>any())).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));
		
		imgurService.getImageByImageId("JKrCLbT");
		
	}

	@Test(expected = MethodFailureException.class)
	public void testViewWhenServiceThrowsHttpServerError() {
		
		when(restTemplate.exchange(anyString(), Mockito.any(HttpMethod.class),
				Matchers.<HttpEntity<?>>any(), Matchers.<Class<String>>any())).thenThrow(new HttpServerErrorException(HttpStatus.BAD_GATEWAY));
		
		imgurService.getImageByImageId("JKrCLbT");
		
	}
	
	@Test(expected = MethodFailureException.class)
	public void testViewWhenServiceThrowsException() {
		
		when(restTemplate.exchange(anyString(), Mockito.any(HttpMethod.class),
				Matchers.<HttpEntity<?>>any(), Matchers.<Class<String>>any())).thenThrow(new RuntimeException());
		
		imgurService.getImageByImageId("JKrCLbT");
		
	}

}
