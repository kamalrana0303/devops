package com.indiastarzilla.developers.ui.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;


public class OtpRM {

    public OtpRM() {
    }

    private String otpNo;

    private boolean isVerified;
    @JsonProperty("phoneNumber")
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

    @JsonProperty("phoneNumber")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @JsonProperty("phoneNumber")
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
