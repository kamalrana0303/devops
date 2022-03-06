package com.devops.developers.otp.entity;

import com.devops.developers.customer.entity.BaseId;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Otp extends BaseId {
    @Column(unique = true, nullable = false)
    private String optId;
    @Column(unique = true, nullable = false)
    private String otpNo;
    @Column(nullable = false)
    private boolean isVerified;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private Date expiry;
    @Column(nullable = false)
    private boolean isValid;

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
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
