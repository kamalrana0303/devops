package com.devops.developers.otp.service;

import com.devops.developers.otp.dto.OtpDto;
import com.devops.developers.otp.entity.Otp;

public interface OtpService {
    public OtpDto createOtp(OtpDto otpDto);

    Otp verifyOtp(String otpNo, String phoneNumber);

    OtpDto findOtpByOtpNo(String otpNo);
}
