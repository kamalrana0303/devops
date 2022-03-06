package com.devops.developers.otp.service.impl;

import com.devops.developers.otp.dto.OtpDto;
import com.devops.developers.otp.entity.Otp;
import com.devops.developers.otp.repository.OtpRepository;
import com.devops.developers.otp.service.OtpService;
import com.devops.developers.utils.Util;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

@Service
public class OtpServiceImpl implements OtpService {
    @Autowired
    OtpRepository otpRepository;
    @Autowired
    ModelMapper mapper;

    @Override
    public OtpDto createOtp(OtpDto otpDto) {
        String otp = Util.generateOTP();
        otpDto.setOtpNo(otp);
        otpDto.setExpiry(Util.getOneMinuteExpiry());
        otpDto.setOptId(Util.generateSmallLengthId());
        otpDto.setVerified(false);
        Otp savedOtp = otpRepository.save(mapper.map(otpDto, Otp.class));
        return mapper.map(savedOtp, OtpDto.class);
    }


    @Override
    public Otp verifyOtp(String otpNo, String phoneNumber) {
        OtpDto otp = findOtpByOtpNo(otpNo);
        if (!otp.getPhoneNumber().equals(phoneNumber)) {
            throw new BadCredentialsException("bad credentials");
        }
        if (otp.getExpiry().before(Calendar.getInstance().getTime())) {
            throw new BadCredentialsException("otp is expired");
        }
        return mapper.map(otp, Otp.class);
    }

    @Override
    public OtpDto findOtpByOtpNo(String otpNo) {
        Optional<Otp> otp = this.otpRepository.findOtpByOtpNo(otpNo);
        return otp.map((x -> mapper.map(x, OtpDto.class))).orElseThrow(() -> {
            return new RuntimeException("bad credentials");
        });
    }

}
