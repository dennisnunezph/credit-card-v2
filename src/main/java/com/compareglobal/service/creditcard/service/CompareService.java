/*
 * Copyright (c) 2015.
 * Compare Asia Group
 */
package com.compareglobal.service.creditcard.service;

import com.compareglobal.service.common.domain.Provider;
import com.compareglobal.service.creditcard.domain.Compare;
import com.compareglobal.service.creditcard.domain.CreditCard;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class CompareService {

    private final CreditCardRepoService repoService;

    @Inject
    public CompareService(CreditCardRepoService repoService) {
        this.repoService = repoService;
    }

    public List<CreditCard> compare(Compare compare) {
        return compare(compare.getLocale());
    }

    public List<CreditCard> compare(final String locale) {
        return repoService.findByActiveAndLocale(locale);
    }

    public List<Provider> getProviders(String locale) {
        List<Provider> providers;

        if (Strings.isNullOrEmpty(locale)) {
            providers = repoService.getProviders();
        } else {
            providers = repoService.getProviders(locale);
            if (CollectionUtils.isEmpty(providers)) {
                providers = repoService.getProviders();
            }
        }

        return providers;
    }

    public List<String> getBrands(String brandType) {
        List<String> storedBrands = repoService.getBrands(brandType);
        List<String> brands = Lists.newArrayList();

        for (String brand : storedBrands) {
            if (StringUtils.isNotBlank(brand)){
                brands.add(brand.trim());
            }
        }

        return brands;
    }

    public CreditCard search(long id) {
        return repoService.search(id);
    }
}
