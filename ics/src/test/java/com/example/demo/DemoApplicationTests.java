package com.example.demo;

import com.example.demo.controllers.RestController;
import com.example.demo.models.Greeting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;



import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private RestController controller;

	private MockMvc mockMvc;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testEmpty() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(content().string("Welcome to localhost:8080"));
	}

	@Test
	public void testGreeting() throws Exception {
		String name = "petya";
		Greeting expectedGreeting = new Greeting(1, String.format("Hello, %s!", name));
		mockMvc.perform(get("/greeting").param("name", name))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(expectedGreeting.getId()))
				.andExpect(jsonPath("$.content").value(expectedGreeting.getContent()));
	}

}
