/*
 * Copyright (c) 2015.
 * Compare Asia Group
 */
package com.compareglobal.service.creditcard.service;

import com.compareglobal.service.common.domain.Provider;
import com.compareglobal.service.common.repository.ProviderRepository;
import com.compareglobal.service.creditcard.domain.CreditCard;
import com.compareglobal.service.creditcard.repository.CreditCardRepository;
import com.compareglobal.service.creditcard.repository.GeneralInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dennis on 3/9/15.
 */
@Service
public class CreditCardRepoServiceImpl implements CreditCardRepoService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private GeneralInfoRepository generalInfoRepository;

    @Override
    public List<CreditCard> findByActiveAndLocale(String locale) {
        return creditCardRepository.findByActiveAndLocale(locale);
    }

    @Override
    public List<Provider> getProviders(String locale) {
        return providerRepository.findByCountry(locale);
    }

    @Override
    public List<Provider> getProviders() {
        return providerRepository.findAll();
    }

    @Override
    public List<String> getBrands(String key) {
        return generalInfoRepository.getBrands(key);
    }

    @Override
    public CreditCard search(long id) {
        return creditCardRepository.findById(id);
    }
}
