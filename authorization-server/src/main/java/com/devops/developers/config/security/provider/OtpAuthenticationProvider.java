package com.devops.developers.config.security.provider;

import com.devops.developers.config.security.authentication.OtpAuthentication;
import com.devops.developers.otp.dto.OtpDto;
import com.devops.developers.otp.entity.Otp;
import com.devops.developers.otp.service.OtpService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class OtpAuthenticationProvider  implements AuthenticationProvider {
    @Autowired
    private OtpService otpService;
    @Autowired
    ModelMapper mapper;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String phoneNumber=authentication.getName();
        String otpNo=(String) authentication.getCredentials();
        Otp otp = this.otpService.verifyOtp(otpNo, phoneNumber);
        return new OtpAuthentication(otp.getPhoneNumber(), otp.getOtpNo());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OtpAuthentication.class.equals(authentication);
    }
}
