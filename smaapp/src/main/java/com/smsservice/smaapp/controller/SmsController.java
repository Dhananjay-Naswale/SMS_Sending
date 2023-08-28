package com.smsservice.smaapp.controller;



import com.smsservice.smaapp.Service.SMSService;
import com.smsservice.smaapp.payload.SmsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmsController {

    private final SMSService smsService;

    @Autowired
    public SmsController(SMSService smsService) {
        this.smsService = smsService;
    }
    //http://localhost:8080/send-sms
    @PostMapping("/send-sms")
    public void sendSms(@RequestBody SmsRequest request) {

        smsService.sendSms(request.getToPhoneNumber(), request.getMessage());
    }
}
