package com.devops.developers.otp.model.response;

import javax.persistence.Column;

public class OtpRest {
    private String otpNo;
    @Column(nullable = false)
    private boolean isVerified;
    @Column(nullable = false)
    private String phoneNumber;

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
