package com.example.hello;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SpringHelloApplicationTests {
	
	@LocalServerPort
	private int port;
	
	private String endpoint;
	
	@Autowired
	TestRestTemplate restTemplate;
	
	@Before
	public void setUp() {
		
		System.out.println("---- Setup ----");
		endpoint = "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testHello() {
		
		String res = restTemplate.getForObject(endpoint + "/", String.class);
		System.out.println(res);
		
		assertThat(res).isEqualTo("Hello Spring");
	}

}
