package com.pedrao.ExpenseTracker.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("expense_tracker/oauth")
public class OauthTesteController {

    @GetMapping
    public ResponseEntity<String> oi(){
        return ResponseEntity.ok("OAUTH2 MANO");
    }
}
