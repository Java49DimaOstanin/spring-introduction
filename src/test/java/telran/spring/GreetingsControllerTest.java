package telran.spring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import telran.spring.controller.GreetingsController;
import telran.spring.service.GreetingsService;

@WebMvcTest
public class GreetingsControllerTest {
	@Autowired 
	GreetingsController controller;
	@MockBean
	GreetingsService greetingsService;
	@Autowired
	MockMvc mockMvc;
	Person personNormal = new Person(123, "Vasya", "Milan", "vasya@gmail.com", "0549172111");
	
	Person personWrongPhone = new Person(123, "Vasya", "Rome", "vasya@gmail.com", "549172111");
	@Autowired
	ObjectMapper objectMapper;
	@Test
	void	applicationContext() {
		assertNotNull(controller);
		assertNotNull(greetingsService);
		assertNotNull(mockMvc);
		assertNotNull(objectMapper);
	}
	@Test
	void normalFlowAddPerson() throws Exception {
		mockMvc.perform(post("http://localhost:8080/greetings").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(personNormal))).andDo(print()).andExpect(status().isOk());
	}
	@Test
	void addPersonWrongPnone() throws Exception {
		
		String response = mockMvc.perform(post("http://localhost:8080/greetings").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(personWrongPhone))).andDo(print()).andExpect(status().isBadRequest())
		.andReturn().getResponse().getContentAsString();
		assertEquals("not Israel mobile phone", response);
	}
}
