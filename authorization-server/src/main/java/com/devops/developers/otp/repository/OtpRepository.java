package com.devops.developers.otp.repository;

import com.devops.developers.otp.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp,Long> {
    public Otp save(Otp otp);
    public Optional<Otp> findOtpByOtpNo(String otpNo);
    public Optional<Otp> findOtpByPhoneNumber(String phoneNumber);
}
