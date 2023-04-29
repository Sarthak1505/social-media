package com.SocialMedia.backend.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

  @GetMapping("/hello")
   public ResponseEntity<String> demoMethod(){
      return ResponseEntity.ok("Hi there");
  }


}
