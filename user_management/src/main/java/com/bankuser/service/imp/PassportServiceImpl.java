package com.bankuser.service.imp;

import com.bankuser.model.entity.Passport;
import com.bankuser.repository.PassportRepository;
import com.bankuser.service.PassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassportServiceImpl implements PassportService {
    
    private PassportRepository passportRepository;
    
    @Autowired
    public PassportServiceImpl (PassportRepository passportRepository) {
        this.passportRepository = passportRepository;
    }
    
    @Override
    public void save (Passport passport) {
        passportRepository.save(passport);
    }
    
    @Override
    public void save (List <Passport> passports) {
        for (Passport i : passports) {
            this.save(i);
        }
    }
}