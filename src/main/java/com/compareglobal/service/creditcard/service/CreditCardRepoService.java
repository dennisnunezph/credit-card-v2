/*
 * Copyright (c) 2015.
 * Compare Asia Group
 */
package com.compareglobal.service.creditcard.service;

import com.compareglobal.service.common.domain.Provider;
import com.compareglobal.service.creditcard.domain.CreditCard;

import java.util.List;

/**
 * Created by dennis on 3/9/15.
 */
public interface CreditCardRepoService {

    List<CreditCard> findByActiveAndLocale(String locale);

    List<Provider> getProviders(String locale);

    List<Provider> getProviders();

    List<String> getBrands(String key);

    CreditCard search(long id);
}
