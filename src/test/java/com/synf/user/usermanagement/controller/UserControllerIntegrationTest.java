
package com.synf.user.usermanagement.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.synf.user.usermanagement.UsermanagementApplication;
import com.synf.user.usermanagement.model.ImageRequestDTO;
import com.synf.user.usermanagement.model.UserDTO;

/**
 * This class have JUnit test cases for  integration test
 * 
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,classes = UsermanagementApplication.class)
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserControllerIntegrationTest.class);
	
	@Autowired
    private MockMvc mvc;
	
	@Test
	public void testSaveUser() {
		try {
			
			mvc.perform( MockMvcRequestBuilders
				      .post("/user/registration")
				      .content(asJsonString(new UserDTO(null, "subbuchinnam", "123", "email4@mail.com")))
				      .contentType(MediaType.APPLICATION_JSON)
				      .accept(MediaType.APPLICATION_JSON))
				      .andExpect(status().is2xxSuccessful())
				      .andExpect(MockMvcResultMatchers.jsonPath("$.userId").exists());
			
		} catch (Exception e) {
			LOGGER.info("Exception occured in testSaveUser method ", e);
		}
	}
	
	@Test
	public void testSaveUserWhenRequestIsInvalid() {
		try {
			
			LOGGER.info("====== VALIDATION FOR USERNAME ============");
			
			mvc.perform( MockMvcRequestBuilders
				      .post("/user/registration")
				      .content(asJsonString(new UserDTO(null, null, "123", "email4@mail.com")))
				      .contentType(MediaType.APPLICATION_JSON)
				      .accept(MediaType.APPLICATION_JSON))
				      .andExpect(status().is4xxClientError());
			
			LOGGER.info("====== VALIDATION FOR PASSWORD ============");
			
			mvc.perform( MockMvcRequestBuilders
				      .post("/user/registration")
				      .content(asJsonString(new UserDTO(null, "subbuchinnam", null, "email4@mail.com")))
				      .contentType(MediaType.APPLICATION_JSON)
				      .accept(MediaType.APPLICATION_JSON))
				      .andExpect(status().is4xxClientError());
			
			LOGGER.info("====== VALIDATION FOR EMAIL ============");
			
			mvc.perform( MockMvcRequestBuilders
				      .post("/user/registration")
				      .content(asJsonString(new UserDTO(null, "subbuchinnam", "123", null)))
				      .contentType(MediaType.APPLICATION_JSON)
				      .accept(MediaType.APPLICATION_JSON))
				      .andExpect(status().is4xxClientError());
			
		} catch (Exception e) {
			LOGGER.info("Exception occured in testSaveUserWhenRequestIsInvalid method ", e);
		}
		
	}
	
	@Test
	public void testViewUser() {
		try {
			
			mvc.perform( MockMvcRequestBuilders
				      .get("/user/subbuchinnam")
				      .contentType(MediaType.APPLICATION_JSON)
				      .accept(MediaType.APPLICATION_JSON))
				      .andExpect(status().is2xxSuccessful())
				      .andExpect(MockMvcResultMatchers.jsonPath("$.userId").exists());
			
		} catch (Exception e) {
			LOGGER.info("Exception occured in testViewUser method ", e);
		}
		
	}
	
	@Test
	public void testDeleteImage() {
		try {
			
			mvc.perform( MockMvcRequestBuilders
				      .delete("/user/image")
				      .content(asJsonString(new ImageRequestDTO("gLFMAGvQCPMFWw0", "subbuchinnam","123")))
				      .contentType(MediaType.APPLICATION_JSON)
				      .accept(MediaType.APPLICATION_JSON))
				      .andExpect(status().is4xxClientError());
			
		} catch (Exception e) {
			LOGGER.info("Exception occured in testViewUser method ", e);
		}
		
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
