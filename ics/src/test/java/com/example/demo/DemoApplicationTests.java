package com.example.demo;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private HelloController controller;

	private MockMvc mockMvc;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testEmpty() throws Exception {
		this.mockMvc.perform(get("/"))
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
