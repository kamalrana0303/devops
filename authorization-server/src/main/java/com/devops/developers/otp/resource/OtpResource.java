package com.devops.developers.otp.resource;

import com.devops.developers.otp.dto.OtpDto;
import com.devops.developers.otp.model.request.OtpRM;
import com.devops.developers.otp.service.OtpService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("otp")
public class OtpResource {

    @Autowired
    ModelMapper mapper;
    @Autowired
    OtpService otpService;

    @PostMapping("request")
    public ResponseEntity<?> createOtp(@RequestBody OtpRM otpRM) {
        OtpDto otpDto = mapper.map(otpRM, OtpDto.class);
        OtpDto otpDto1 = this.otpService.createOtp(otpDto);
        return ResponseEntity.ok(otpDto1);
    }
}
