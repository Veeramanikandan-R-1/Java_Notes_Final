package com.springapp.demo.controller;

import com.springapp.demo.service.EmailService;
import com.springapp.demo.service.OtpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    private final EmailService emailService;
    private final OtpService otpService;

    public EmailController(
            EmailService emailService,
            OtpService otpService) {
        this.emailService = emailService;
        this.otpService = otpService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(
            @RequestParam String to) {

        emailService.sendEmail(
            to,
            "Welcome",
            "Your email integration is working!"
        );

        return ResponseEntity.ok("Email sent successfully");
    }

    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(
            @RequestParam String email) {

        otpService.sendOtp(email);

        return ResponseEntity.ok("OTP email requested");
    }
}
