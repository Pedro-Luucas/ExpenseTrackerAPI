package com.pedrao.ExpenseTracker.controller;


import com.pedrao.ExpenseTracker.dto.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pedrao.ExpenseTracker.service.RegistrationService;

@RestController
@AllArgsConstructor
@RequestMapping(path="expense_tracker/registration")
public class RegistrationController {

    private RegistrationService registrationService;

    public String register(@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }

}
