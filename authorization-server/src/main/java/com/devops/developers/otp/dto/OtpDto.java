package com.devops.developers.otp.dto;

import javax.persistence.Column;
import java.util.Date;

public class OtpDto {
    private Long id;
    private String optId;
    private String otpNo;
    private boolean isVerified;
    private String phoneNumber;
    private Date expiry;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getExpiry() {
        return expiry;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public String getOptId() {
        return optId;
    }

    public void setOptId(String optId) {
        this.optId = optId;
    }

    public String getOtpNo() {
        return otpNo;
    }

    public void setOtpNo(String otpNo) {
        this.otpNo = otpNo;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
