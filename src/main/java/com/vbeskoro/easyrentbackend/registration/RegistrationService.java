package com.vbeskoro.easyrentbackend.registration;

import com.vbeskoro.easyrentbackend.appuser.AppUser;
import com.vbeskoro.easyrentbackend.appuser.AppUserService;
import com.vbeskoro.easyrentbackend.appuser.UserRole;
import com.vbeskoro.easyrentbackend.registration.email.EmailSender;
import com.vbeskoro.easyrentbackend.registration.token.ConfirmationToken;
import com.vbeskoro.easyrentbackend.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {


    private final AppUserService appUserService;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSender emailSender;

    public String register(RegistrationRequest request) {

        String token = appUserService.signUpUser(
                new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        UserRole.USER,
                        request.getPassword()
                )
        );
        //TODO: how to use html file for email template
        //TODO: how to paste variables to template
//        String link = "http://localhost:8080/api/v1/registration/confirm?token=" + token;
//        emailSender.send(request.getEmail(), buildEmail(request.getFirstName(), link));
        return token;
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        appUserService.enableAppUser(
                confirmationToken.getAppUser().getEmail());
        return "confirmed";
    }
}
