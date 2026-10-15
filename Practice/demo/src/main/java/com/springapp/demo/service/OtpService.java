package com.springapp.demo.service;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;

@Service
public class OtpService {

    private final EmailService emailService;
    private final SecureRandom random = new SecureRandom();

    public OtpService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void sendOtp(String email) {
        String otp = String.format(
            "%06d", random.nextInt(1_000_000)
        );

        emailService.sendEmail(
            email,
            "Your verification OTP",
            "Your OTP is: " + otp
                + "\nIt is valid for 5 minutes."
        );

        // Demo only: store the OTP securely with expiry
        // before implementing OTP verification.
    }
}
