package com.bankuser.service;

import com.bankuser.model.entity.Passport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PassportService {
    void save(final Passport passport);
    void save(final List <Passport> passports);
}
