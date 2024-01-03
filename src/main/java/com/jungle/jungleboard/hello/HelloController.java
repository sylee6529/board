package com.jungle.jungleboard.hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/hello")
@RestController
public class HelloController {
    @Value("${rich.server.name}")
    private String serverName;

    @GetMapping
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello from " + serverName);
    }
}
