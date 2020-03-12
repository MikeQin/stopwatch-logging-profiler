package com.example.hello;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping(path = "/")
	public ResponseEntity<Object> getHello() {
		
		return ResponseEntity.ok("Hello Spring");
	}

}
