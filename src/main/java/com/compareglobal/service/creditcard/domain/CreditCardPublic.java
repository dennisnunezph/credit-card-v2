package com.compareglobal.service.creditcard.domain;

import com.compareglobal.service.creditcard.domain.benefits.Benefit;
import java.util.List;
import java.util.Set;

/**
 * Created by dennis on 7/28/15.
 */
public class CreditCardPublic {

    private final CreditCard creditCard;
    private List<Benefit> sortedBenefits;

    public CreditCardPublic(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public List<Benefit> getSortedBenefits() {
        return sortedBenefits;
    }

    public void setSortedBenefits(List<Benefit> sortedBenefits) {
        this.sortedBenefits = sortedBenefits;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }
}
