package com.smsservice.smaapp.configuration;



import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class TwilioConfig {

    @Value("${twilio.phone.number}")
    private String PhoneNumber;
    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Bean
    public void initTwilio(){
        Twilio.init(accountSid,authToken);
    }
    public String getPhoneNumber(){
        return PhoneNumber;
    }
}

