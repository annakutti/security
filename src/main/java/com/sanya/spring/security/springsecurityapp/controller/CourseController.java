/**
 * 
 */
package com.sanya.spring.security.springsecurityapp.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sanya_s
 *
 */
@RestController
public class CourseController {
	
	@GetMapping("/rest/user/hello")
	public String sayHello(@AuthenticationPrincipal final UserDetails userDetails){
		userDetails.getAuthorities().forEach(System.out::println);
		return "hello from the other side....";
	}
	
	@GetMapping("/rest/admin/hello")
	public String sayHelloAdmin(){
		return "Hello Admin...welcome";
	}

}
