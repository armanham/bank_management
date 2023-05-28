package com.bankuser.service.imp;

import com.bankuser.repository.PassportRepository;
import com.bankuser.service.PassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassportServiceImpl implements PassportService {
    private PassportRepository passportRepository;
    
    @Autowired
    public PassportServiceImpl (PassportRepository passportRepository) {
        this.passportRepository = passportRepository;
    }
}
