package com.nagarro.ProductSearchApi.serviceIMPL;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nagarro.ProductSearchApi.model.OtpModel;
import com.nagarro.ProductSearchApi.repository.OtpRepository;
import com.nagarro.ProductSearchApi.service.OtpService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

@Service
public class OtpServiceImpl implements OtpService {
	

    @Autowired
    private OtpRepository otpRepository;

    @Value("${twilio.accountSid}")
    private String twilioAccountSid;

    @Value("${twilio.authToken}")
    private String twilioAuthToken;

    @Value("${twilio.phoneNumber}")
    private String twilioPhoneNumber;

    @Override
    public OtpModel generateOtp(String mobileNumber) {
        
        OtpModel existingOtp = otpRepository.findByMobileNumber(mobileNumber);

        if (existingOtp != null) {
            // Update the existing OTP
            String newOtpCode = String.format("%06d", new Random().nextInt(1000000));
            existingOtp.setOtp(newOtpCode);
            otpRepository.save(existingOtp);
            sendOtpViaTwilio(mobileNumber, newOtpCode);

            return existingOtp;
        } else {
            // Generate a new OTP
            String otpCode = String.format("%06d", new Random().nextInt(1000000));
            OtpModel otpModel = new OtpModel();
            otpModel.setMobileNumber(mobileNumber);
            otpModel.setOtp(otpCode);
            otpRepository.save(otpModel);
            sendOtpViaTwilio(mobileNumber, otpCode);

            return otpModel;
        }
    }

    
//    @Scheduled(fixedDelay = 120000) 
//    public void cleanUpOldOtps() {
//        LocalDateTime twoMinutesAgo = LocalDateTime.now().minusMinutes(2);
//        otpRepository.deleteByCreatedAtBefore(twoMinutesAgo);
//    }

    @Override
    public boolean verifyOtp(String mobileNumber, String otp) {
        OtpModel savedOtp = otpRepository.findByMobileNumber(mobileNumber);
        boolean isOtpValid = savedOtp != null && savedOtp.getOtp().equals(otp);

        if (isOtpValid) {
            otpRepository.delete(savedOtp); 
        }

        return isOtpValid;
    }

    private void sendOtpViaTwilio(String toPhoneNumber, String otpCode) {
        Twilio.init(twilioAccountSid, twilioAuthToken);

        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(toPhoneNumber),
                new com.twilio.type.PhoneNumber(twilioPhoneNumber),
                "Your OTP is: " + otpCode)
                .create();

        System.out.println("Twilio Message SID: " + message.getSid());
    }
}
